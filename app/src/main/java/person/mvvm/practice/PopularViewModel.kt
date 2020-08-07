package person.mvvm.practice

import androidx.lifecycle.MutableLiveData
import person.mvvm.practice.base.BaseViewModel
import person.mvvm.practice.bean.Article
import person.mvvm.practice.bus.Bus
import person.mvvm.practice.bus.USER_COLLECT_UPDATED
import person.mvvm.practice.loadmore.LoadMoreStatus

/**
 * Discription:
 * Created by guokun on 2020/8/5.
 */
class PopularViewModel : BaseViewModel() {
    companion object {
        const val INITIAL_PAGE = 0
    }

    private val popularRepository by lazy { PopularRepository() }

    private val collectRepository by lazy { CollectRepository() }

    val articleList: MutableLiveData<MutableList<Article>> = MutableLiveData()
    val loadMoreStatus = MutableLiveData<LoadMoreStatus>()
    val refreshStatus = MutableLiveData<Boolean>()
    val reloadStatus = MutableLiveData<Boolean>()

    private var page = INITIAL_PAGE

    fun refreshArticleList() {
        refreshStatus.value = true
        reloadStatus.value = false
        launch(
            block = {
                val topArticleListDeferred = async {
                    popularRepository.getTopArticleList()
                }
                val paginationDeferred = async {
                    popularRepository.getArticleList(INITIAL_PAGE)
                }
                val topArticleList = topArticleListDeferred.await()
                    .apply { forEach { it.top = true } }

                val pagination = paginationDeferred.await()
                page = pagination.curPage
                articleList.value = mutableListOf<Article>().apply {
                    addAll(topArticleList)
                    addAll(pagination.datas)
                }
                refreshStatus.value = false
            },
            error = {
                refreshStatus.value = false
                reloadStatus.value = page == INITIAL_PAGE
            }
        )
    }

    fun loadMoreArticleList() {
        loadMoreStatus.value = LoadMoreStatus.LOADING
        launch(
            block = {
                val pagination = popularRepository.getArticleList(page)
                page = pagination.curPage
                val currentList = articleList.value ?: mutableListOf()
                currentList.addAll(pagination.datas)
                articleList.value = currentList
                loadMoreStatus.value = if (pagination.offset >= pagination.total) {
                    LoadMoreStatus.END
                } else {
                    LoadMoreStatus.COMPLETED
                }

            }, error = {
                loadMoreStatus.value = LoadMoreStatus.ERROR
            }
        )
    }

    fun collect(id:Int) {
        launch(
            block = {
                collectRepository.collect(id)
                updateItemCollectState(id to true)
                Bus.post(USER_COLLECT_UPDATED, id to false)
            }
        ,error = {
                updateItemCollectState(id to false)
            }
        )
    }

    fun uncollect(id:Int){
        launch(
            block = {
                collectRepository.uncollect(id)
                updateItemCollectState(id to false)
                Bus.post(USER_COLLECT_UPDATED, id to false)
            },
            error = {
                updateItemCollectState(id to true)
            }
        )
    }

    fun updateItemCollectState(target: Pair<Int, Boolean>) {
        val list = articleList.value
        val item = list?.find { it.id == target.first } ?: return
        item.collect = target.second
        articleList.value = list
    }
}
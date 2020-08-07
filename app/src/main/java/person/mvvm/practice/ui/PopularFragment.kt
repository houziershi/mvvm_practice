package person.mvvm.practice.ui

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_popular.*
import person.mvvm.practice.*
import person.mvvm.practice.bus.Bus
import person.mvvm.practice.bus.USER_COLLECT_UPDATED
import person.mvvm.practice.loadmore.LoadMoreStatus

class PopularFragment : BaseVmFragment<PopularViewModel>() {
    companion object {
        fun newInstance() = PopularFragment()
    }

    private lateinit var mAdapter: ArticleAdapter

    override fun layoutRes() = R.layout.fragment_popular

    override fun viewModelClass() = PopularViewModel::class.java

    override fun initView() {
        swipeRefreshLayout.run {
            setColorSchemeResources(R.color.textColorPrimary)
            setProgressBackgroundColorSchemeResource(R.color.bgColorPrimary)
            setOnRefreshListener { mViewModel.refreshArticleList() }
        }
        mAdapter = ArticleAdapter(R.layout.item_article).apply {
            setLoadMoreView(CommonLoadMoreView())
            bindToRecyclerView(recyclerView)
            setOnLoadMoreListener({
                mViewModel.loadMoreArticleList()
            }, recyclerView)
            setOnItemClickListener { _, _, position ->
                val article = mAdapter.data[position]
                Toast.makeText(App.instance, "未实现",Toast.LENGTH_SHORT).show()
            }
            setOnItemChildClickListener { _, view, position ->
                val article = mAdapter.data[position]
                if (view.id == R.id.iv_collect) {
                    view.isSelected = !view.isSelected
                    if (article.collect) {
                        mViewModel.uncollect(article.id)
                    } else {
                        mViewModel.collect(article.id)
                    }
                }
            }
        }

    }

    override fun observe() {
        super.observe()
        mViewModel.run {
            articleList.observe(viewLifecycleOwner, Observer {
                mAdapter.setNewData(it)
            })
            refreshStatus.observe(viewLifecycleOwner, Observer {
                swipeRefreshLayout.isRefreshing = it
            })
            loadMoreStatus.observe(viewLifecycleOwner, Observer {
                when (it) {
                    LoadMoreStatus.COMPLETED -> mAdapter.loadMoreComplete()
                    LoadMoreStatus.ERROR -> mAdapter.loadMoreFail()
                    LoadMoreStatus.END -> mAdapter.loadMoreEnd()
                    else -> return@Observer
                }
            })
            reloadStatus.observe(viewLifecycleOwner, Observer {
                reloadView.isVisible = it
            })
        }
//        Bus.observe<Boolean>(USER_LOGIN_STATE_CHANGED, viewLifecycleOwner, Observer {
//            mViewModel.updateListCollectState()
//        })
        Bus.observe<Pair<Int, Boolean>>(USER_COLLECT_UPDATED, viewLifecycleOwner, Observer {
            mViewModel.updateItemCollectState(it)
        })
    }

    override fun lazyLoadData() {
        mViewModel.refreshArticleList()
    }

}
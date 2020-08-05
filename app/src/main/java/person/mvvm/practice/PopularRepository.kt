package person.mvvm.practice

import person.mvvm.practice.api.RetrofitClient

/**
 * Discription:
 * Created by guokun on 2020/8/5.
 */
class PopularRepository {
    suspend fun getTopArticleList() = RetrofitClient.apiService.getTopArticleList().apiData()

    suspend fun getArticleList(page:Int) = RetrofitClient.apiService.getArticleList(page).apiData()
}
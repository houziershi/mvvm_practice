package person.mvvm.practice.api

import person.mvvm.practice.bean.Article
import person.mvvm.practice.bean.Pagination
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Discription:
 * Created by guokun on 2020/8/5.
 */
interface ApiService {
    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @GET("/article/top/json")
    suspend fun getTopArticleList(): ApiResult<List<Article>>

    @GET("/article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): ApiResult<Pagination<Article>>

    @POST("lg/collect/{id}/json")
    suspend fun collect(@Path("id") id: Int) : ApiResult<Any?>

    @POST("lg/uncollect_originId/{id}/json")
    suspend fun unCollect(@Path("id") id: Int): ApiResult<Any?>

}
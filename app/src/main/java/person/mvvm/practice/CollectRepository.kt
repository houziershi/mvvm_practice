package person.mvvm.practice

import person.mvvm.practice.api.RetrofitClient

/**
 * Discription:
 * Created by guokun on 2020/8/6.
 */
class CollectRepository {
    suspend fun collect(id: Int) = RetrofitClient.apiService.collect(id).apiData()
    suspend fun uncollect(id: Int) = RetrofitClient.apiService.unCollect(id).apiData()
}
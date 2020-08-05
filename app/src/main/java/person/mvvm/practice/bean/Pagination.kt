package person.mvvm.practice.bean

/**
 * Discription:
 * Created by guokun on 2020/8/5.
 */
class Pagination<T>(
    val offset: Int,
    val size: Int,
    val total: Int,
    val pageCount: Int,
    val curPage: Int,
    val over: Boolean,
    val datas: List<T>
)
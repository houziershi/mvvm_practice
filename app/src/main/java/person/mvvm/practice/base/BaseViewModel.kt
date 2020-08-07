package person.mvvm.practice.base

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParseException
import kotlinx.coroutines.*
import person.mvvm.practice.App
import person.mvvm.practice.api.ApiException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


typealias Block<T> = suspend () -> T
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit

/**
 * Discription:
 * Created by guokun on 2020/8/5.
 */
open class BaseViewModel : ViewModel() {

    /**
     * 创建并执行协程
     * @param block 协程中执行
     * @param error 错误时执行
     * @param cancel 取消时只需
     * @param showErrorToast 是否弹出错误吐司
     * @return Job
     */
    protected fun launch(
        block: Block<Unit>,
        error: Error? = null,
        cancel: Cancel? = null,
        showErrorToast: Boolean = true
    ): Job {
        return viewModelScope.launch {
            try {
                block.invoke()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    else -> {
                        //可加异常处理
                        onError(e, showErrorToast)
                        error?.invoke(e)
                    }
                }
            }
        }
    }

    private fun onError(e: Exception, showErrorToast: Boolean) {
        when(e) {
            is ApiException -> Toast.makeText(App.instance, e.message, Toast.LENGTH_SHORT).show()
            is ConnectException, is SocketTimeoutException, is UnknownHostException, is HttpException ->
                Toast.makeText(App.instance, "网络请求失败", Toast.LENGTH_SHORT).show()
            // 数据解析错误
            is JsonParseException ->
                Toast.makeText(App.instance, "数据解析错误", Toast.LENGTH_SHORT).show()
            // 其他错误
            else ->
                Toast.makeText(App.instance, "其它错误", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     ** 创建并执行协程
     * @param block 协程中执行
     * @return Deferred<T>
     */
    protected fun <T> async(block: Block<T>): Deferred<T> {
        return viewModelScope.async { block.invoke() }
    }

}
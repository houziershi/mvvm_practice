package person.mvvm.practice.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import person.mvvm.practice.base.BaseViewModel

abstract class BaseVmFragment<VM : BaseViewModel> : BaseFragment() {
    protected lateinit var mViewModel: VM
    private var lazyLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        observe()
        // 因为Fragment恢复后savedInstanceState不为null，
        // 重新恢复后会自动从ViewModel中的LiveData恢复数据，
        // 不需要重新初始化数据。
        if (savedInstanceState == null) {
            initData()
        }
    }

    override fun onResume() {
        super.onResume()
        // 实现懒加载
        if (!lazyLoaded) {
            lazyLoadData()
            lazyLoaded = true
        }
    }

    open fun lazyLoadData() {
        // Override if need
    }

    open fun initData() {
        // Override if need
    }

    open fun observe() {
        // Override if need
    }

    private fun initViewModel() {
        mViewModel = ViewModelProvider(this).get(viewModelClass())
    }

    abstract fun viewModelClass(): Class<VM>


    open fun initView() {
        // Override if need
    }

}
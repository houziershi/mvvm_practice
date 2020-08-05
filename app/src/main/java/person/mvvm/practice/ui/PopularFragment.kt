package person.mvvm.practice.ui

import person.mvvm.practice.PopularViewModel

class PopularFragment : BaseVmFragment<PopularViewModel>(){
    companion object{
        fun newInstance() = PopularFragment()
    }


    override fun viewModelClass() = PopularViewModel::class.java

}
package person.mvvm.practice.api

import java.lang.RuntimeException

/**
 * Discription:
 * Created by guokun on 2020/8/5.
 */
class ApiException(var code:Int, override var message:String) :RuntimeException()
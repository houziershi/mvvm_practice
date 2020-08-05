package person.mvvm.practice

import android.app.Application

/**
 * Discription:
 * Created by guokun on 2020/8/5.
 */
class App : Application() {
    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
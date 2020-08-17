package Variables

import kotlin.concurrent.thread

//There are two type of variable declarations in kotlin, mutable and immutable.
//use var declaration for mutable variables and val for immutable variables
fun main(args : Array<String>) {
    var message = "var means a mutable variable so you can change it. "
    println("'message' variable has this value '$message'")
    message = "wallah, modified"
    println("now 'message' has this value '$message'")

    println("")
    val forever = "val means that the variable is immutable. If you try to modify this 'forever' variable, the compiler will complain."
    println(forever)
    //immutable = "oi" -- bzz, wrong!

    var arg1: (x:Int) -> Int = {
        5
    }
    println("main 方法 ${Thread.currentThread()}")

    for (i in 1..5) {
        thread (start = true){
            println("inside a synchronized method:${Thread.currentThread()}")
        }
    }

    Util.myMethod()

}
object Util{
    fun myMethod() {
        println("Kotlin 静态方法 ${Thread.currentThread()}")
    }
}

package HelloWorld

object A {
    val number: Int = 1
    fun method() {
        println("A.method()")
    }
}

fun main(args : Array<String>) {
    val text = "Hello World"
    println(text)
    A.method()
}

/* what's next? try Strings\Strings.kt. It's short, fun and will be used a lot in subsequent samples */
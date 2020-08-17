package Objects.generic

class Generic<out T>(val arg: T) {
    fun foo(): T {
        return arg
    }
}

class GenericB<in T>(argB: T) {
    fun foo(arg: T) {
        println(arg)
    }
}

fun main(args: Array<String>) {
    var genericA = Generic("guokun")
    val genericB = GenericB(10)

    println(genericA.foo())
    genericB.foo(11)
}
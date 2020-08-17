package Objects.Inheritance

fun main(args: Array<String>) {
    //simple final class
    var final = FinalConstruct("Andrew Sullivan")
    println("His name is ${final.name}")

    //derived class with two traits
    var less = LessFlexible("Karl Rove")
    println("His name is ${less.name}")
    less.dance()
    less.sing()
    less.method()
}

class FinalConstruct(var name: String) {
    var one: Int = 1
    lateinit var lazyOne:String
}

open class FlexibleConstruct(var name: String) {
    open var two: Int = 2
    open fun method() {

    }
}

interface Singing {
    fun sing() {
        println("I can sing")
    }
}

interface Dancing {
    fun dance() {
        println("I can dance")
    }
}

//You can only have one supertype but multiple interfaces
class LessFlexible(name: String) : FlexibleConstruct(name), Singing, Dancing {
    override var two: Int
        get() = 4
        set(value) {}

    override fun sing() {
        super.sing()
        println("LessFlexible")
    }

    override fun dance() {
        super.dance()
        println("LessFlexible")
    }

    override fun method() {
        super.method()
        println(two)
        println("LessFlexible")
    }
}


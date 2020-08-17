package Objects.Constructors

open class Primary(initialName: String, age: Int = 30) {
    var firstName = initialName
    val age = age

    init {
        //this is an anonymous constructor
        //there is no other type of constructor
        firstName += ".jr"
    }

    public fun sayName() {
        println("My name is $firstName and I am $age years old.")
    }
}

//@JvmOverloads constructor
class InitOrder(var initOneParameter: String = "hgk", var initTwoParameter: Int = 20) {
    constructor(a: String) : this(a, 20){
        println("second constructor")
    }
    constructor(b: Int) : this("hgc", b)

    init {
        println("init one")
        initOneParameter += "hgc"
    }

    init {
        println("init two")
        initTwoParameter += 10
    }

    fun sayInfo() {
        println("My name is $initOneParameter and I am $initTwoParameter years old.")
    }
}

fun main(args: Array<String>) {
    var p = Primary("John Adams", 56)
    p.sayName()

    var n = Primary("Bon Jovi")
    n.sayName()

    val initOrder = InitOrder("来了")
    initOrder.sayInfo()
}

package Objects.Constructors

open class Primary(initialName : String, age : Int = 30) {
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

class InitOrder(initOne : String = "hgk", initTwo: Int = 20 ) {
    var initOneParameter = initOne
    var initTwoParameter = initTwo


    init {
        initOneParameter += "hgc"
    }
    init {
        initTwoParameter += 10
    }

    fun sayInfo() {
        println("My name is $initOneParameter and I am $initTwoParameter years old.")
    }
}

fun main(args : Array<String>) {
//    var p = Primary("John Adams", 56)
//    p.sayName()
//
//    var n = Primary("Bon Jovi")
//    n.sayName()
//
//    var my=MyPrimary("guokun", 21)
//    my.myInfo()
//    var myInfo = InitOrder()
//    myInfo.sayInfo()

}

class MyPrimary(address:String, height: Int = 170, initialName : String, age : Int = 30):Primary(initialName, age)  {


    constructor(address:String, height: Int = 170, initialName : String, age : Int = 30, discription: String):this(address, height, initialName, age) {

    }

    fun myInfo() {

    }
}

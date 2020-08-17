package Objects.ObjectClass

class StaticDemo {
    companion object {
        val constantOne = 1
    }
}

class StaticDemoTwo {
    companion object InnerStatic {
        val constantTwo = 2
    }
}

class StaticDemoThree {
    companion object {
        const val constantThree = 3
    }
}



fun main(args: Array<String>) {

    val espana = Matador("Emilio")

    Matador.show(espana)

    //you can also assign class object to a variable and use it later
    val m = Matador
    println("Typeof " + m)
    m.show(espana)


    //how do you pass it to a parameter

    //guokun
    println(StaticDemo.constantOne)
    println(StaticDemoTwo.constantTwo)
    println(StaticDemoThree.constantThree)
}

class Matador(name: String) {
    private val name: String = name

    private fun myPrivateShow() {
        println("This is ${name} private show")
    }

    companion object {
        fun show(mt: Matador) {
            //function inside a class object can access private properties and function of the class
            println("Expose the private secret of ${mt.name}")
            mt.myPrivateShow()
        }
    }
}
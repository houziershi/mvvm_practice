package Objects.ExtensionProperties
/*
类的属性扩展
 */
val Amazing.isEmpty : Boolean
    get() = this.name.length == 0

class Amazing(name : String) {
    var name : String = name
}

fun main(args : Array<String>) {
    val italian = Amazing("Roberto")

    println("${italian.name} is  empty : ${italian.isEmpty}")
}
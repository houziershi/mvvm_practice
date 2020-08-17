package Ranges
/*
for,in关键字
 */
fun main(args : Array<String>) {
    for (i in 1..10)
        print("$i,")

    println("")

    for (i in 'A'..'z')
        print("$i,")

    println("--------")
    myFor(1,3,45,2)
}

private fun myFor(vararg args:Int):Int {
    for (i in args) {
        print("$i,")
    }
    return args.size
}
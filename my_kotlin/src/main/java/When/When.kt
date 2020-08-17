package When
/*
else:单独存在
 */
fun main(args : Array<String>) {
    var x = 101
    val greater = { x : Int -> x > 100 }

    //This is one hell of a flexible switch statement
    when {
        x in 1..50 -> print("In range")
        greater(x) -> print("Great")
        x == 50 -> print("Exact match")
        else -> print("Outside range")
    }

    println()
    var arg:Int
    arg = 10

    when{
        arg == 10 -> print("10")
        arg == 11 -> print("11")
        else -> print("case else")
    }

    println(hasPrefix("prefix idea"))
    println(hasPrefix(15))

}

fun hasPrefix(x:Any) = when(x) {
    is String -> x.startsWith("prefix")
    else -> false
}


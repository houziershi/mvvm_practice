package Jumps
/*
loop@
 */
fun main(args: Array<String>) {

    printAgain@ println("print again")

    loop@ for (i in 1..50) {
        print("$i,")
        if (i == 25) {
            break@loop
        }
    }
    println("")
    println("end of line")
    println("print again")
    for (i in 1..50) {
        print("$i,")
        if (i == 25) {
            break
        }
    }
    println("")
    println("end of line")
    foo()
}

fun foo() {
    listOf(1, 2, 3, 4, 5).forEach hgk@{
        if (it == 3) return@hgk // 局部返回到该 lambda 表达式的调用者，即 forEach 循环
        print(it)
    }
    print(" done with explicit label")
}
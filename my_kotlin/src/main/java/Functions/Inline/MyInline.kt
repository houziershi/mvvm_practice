package Functions.Inline

inline fun calculate(a: Int, b: Int, cal: (Int, Int) -> String) {
    println(cal(a, b))
}

fun main(args: Array<String>) {
    calculate(3, 7) { a, b ->
        "$a + $b = ${a + b}"
    }

    calculate(3, 7) { a, b ->
        "$a * $b = ${a * b}"
    }
}
package Functions.ReadOnlyAnnotation

import java.util.HashMap

// TODO: complete

fun names() : HashMap<String, String> {
    return hashMapOf(
            "John" to "Adam",
            "Bryan" to "Mana"
    )
}

fun main(Args : Array<String>) {
    val names = names()

}
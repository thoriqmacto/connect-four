fun main() {
    // write your code here
    val inp = readln().toInt()
    when(inp){
        2 -> println("Yes!")
        1,3,4 -> println("No!")
        else -> println("Unknown number")
    }
}
fun main() {
    // put your code here
    val a = readln().toInt()
    val b = readln().toInt()
    val c = readln().toInt()

    if (a+b == 20 || a+c == 20 || b+c == 20) print(true) else print(false)
}
fun main() {
    val answer = readln()
    // write your code here
    val can = Regex("I can do my homework on time!")
    val cant = Regex("I can't do my homework on time!")

    print(can.matches(answer) || cant.matches(answer))
}
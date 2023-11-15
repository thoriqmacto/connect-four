fun main() {
    // Do not touch code below
    val inputList: MutableList<MutableList<String>> = mutableListOf()
    val n = readln().toInt()
    for (i in 0 until n) {
        val strings = readln().split(' ').toMutableList()
        inputList.add(strings)
    }
    // write your code here
    val upLeft = inputList[0][0]
    val upRight = inputList[0][n-1]
    val downLeft = inputList[n-1][0]
    val downRight = inputList[n-1][n-1]
    println("$upLeft $upRight")
    println("$downLeft $downRight")


}
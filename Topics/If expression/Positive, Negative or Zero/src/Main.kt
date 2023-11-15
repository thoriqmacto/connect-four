fun main() {
    // write your code here
    val intNum = readln().toInt()
    print(
        if (intNum > 0)
        "positive" else
        if (intNum < 0)
        "negative" else
        "zero"
    )
}
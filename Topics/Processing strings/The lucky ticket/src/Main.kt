fun main() {
    // write your code here
    val tixNum = readln().toString()
    val first3 = tixNum.substring(0,3)
    val last3 = tixNum.substring(3,tixNum.length)

    var sumFirst = 0
    var sumLast = 0

    for (i in first3){
        sumFirst += i.digitToInt()
    }
    for (j in last3){
        sumLast += j.digitToInt()
    }

    if (sumLast == sumFirst) print("Lucky") else print("Regular")
}
fun main() {
    // write your code here
    val strArr = mutableListOf<String>()
    repeat(3) {
        val inp = readln()
        strArr.add(inp)
    }
    val joinStr = strArr.joinToString()
//    println(joinStr)
    val (str1, op,str2) = joinStr.replace(" ","").split(",")
    when(op){
        "equals"    -> println(str1 == str2)
        "plus"      -> println(str1 + str2)
        "endsWith"  -> println(str1.endsWith(str2))
        else        -> println("Unknown operation")
    }
}
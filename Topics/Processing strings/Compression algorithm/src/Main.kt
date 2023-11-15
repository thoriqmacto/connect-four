fun main() {
    // write your code here
    val strInput = readln()

    var dupCount = 1
    var strTemp = ""
    var strConstruct = ""
//    println(strInput.length)
    if (strInput.length == 1){
        strConstruct = strInput + "1"
    }else
        for (index in strInput.indices){
//            println("$index:${strInput[index]}, $strTemp")
            if (strTemp == strInput[index].toString() && index != strInput.length - 1) {
                dupCount++
            }else if (strTemp != strInput[index].toString() && strTemp.isEmpty()) {
                strConstruct += strInput[index].toString()
            }else if (strTemp != strInput[index].toString() && strTemp.isNotEmpty() && index < strInput.length - 1) {
                strConstruct += dupCount.toString() + strInput[index].toString()
                dupCount = 1
            }else if(strTemp == strInput[index].toString() && index == strInput.length - 1){
                dupCount++
                strConstruct += dupCount.toString()
            }else if(strTemp != strInput[index].toString() && index == strInput.length - 1){
                strConstruct += dupCount.toString() + strInput[index].toString()
                dupCount = 1
                strConstruct += dupCount.toString()
            }
            strTemp = strInput[index].toString()
        }
    print(strConstruct)
}
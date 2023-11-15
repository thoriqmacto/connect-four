fun solution(strings: List<String>, str: String): Int {
    // put your code here
    var occ = 0
    for (s in strings){
        if (s == str){
            occ++
        }
    }
    return occ
}
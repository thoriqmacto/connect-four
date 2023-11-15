fun main() {    
    // write your code here
    val hourA = readln().toInt()
    val hourB = readln().toInt()
    val hourH = readln().toInt()

    if (hourH < hourA){
        print("Deficiency")
    }else if (hourH > hourB){
        print("Excess")
    }else print("Normal")
}
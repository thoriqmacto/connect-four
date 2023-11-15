import kotlin.math.pow

fun main() {
    // write your code here
    val aNum = readln().toInt()
    val bNum = readln().toInt()
    val cNum = readln().toInt()

    if (aNum>0 && bNum>0 && cNum>0){
        if(aNum + bNum > cNum && aNum + cNum > bNum && bNum + cNum > aNum){
            print("YES")
        }else print("NO")
    }else print("NO")
}
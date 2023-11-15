import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    // put your code here
    val inp = scanner.nextLine().split(" ").toMutableList()
    var out = mutableListOf<String>()
    for (i in inp.indices){
        if (!inp[i].isEmpty()){
            out.add(inp[i])
        }
    }

    for (i in out.indices){
        println(out[i])
    }
}
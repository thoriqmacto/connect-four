package connectfour

fun printMatrix(row: Int, col: Int, matrix: MutableList<MutableList<String>>){
    for (i in 1..col){
        print(" $i")
    }

    println()

    for (i in 1..row){
        for (j in 1..col+1){
            print("|")

            if (j <= col) {
                print(matrix[i-1][j-1])
            }

            if (j == col+1){
                println()
            }
        }
    }

    for (i in 1..col*2+1){
        print("=")
    }
    println()
}

fun promptCheck(prompt:String, totalCol:Int, totalRow:Int,markSign:String,mainMatrix:MutableList<MutableList<String>>):Boolean{
    val promptRegex = Regex("[0-9]+|(end)")
    val promptCond = promptRegex.matches(prompt)
    if(!promptCond){
        println("Incorrect column number")
        return false
    }else if (prompt != "end"){
        val colRngChk = prompt.toInt() in 1..totalCol
        if (!colRngChk){
            println("The column number is out of range (1 - $totalCol)")
            return false
        }else{
            val validCol = prompt.toInt() - 1

            // sum all column length
            var sumColLength = 0
            for (i in 0 until totalRow){
                val contentStr = mainMatrix[i][validCol]
                val sumCond = contentStr != " "
                if (sumCond) {
                    sumColLength ++
                }
            }
            // check if column full or not
            val colFullCond = sumColLength == totalRow

            if (!colFullCond){
                // iterate over the column to check empty place
                var rowToOccupy = totalRow - 1
                for (i in totalRow-1 downTo 0){
                    val occupied = mainMatrix[i][validCol] != " "
                    if (!occupied){
                        rowToOccupy = i
                        break
                    }
                }

                // add mark to mutable list
                mainMatrix[rowToOccupy][validCol] = markSign

                // print the matrix
                printMatrix(totalRow,totalCol,mainMatrix)

                // return
                return true
            }else{
                val printCol = validCol + 1
                println("Column $printCol is full")
                return false
            }
        }
    }else{
        return false
    }
}

fun matrixWinProcessing(mainMatrix:MutableList<MutableList<String>>,marking:String): String {
    val totalCol = mainMatrix[0].size
    val totalRow = mainMatrix.size
    var returnStr = ""

    // check horizontal win
    for (r in mainMatrix.size - 1 downTo 0){
        for (c in 0..mainMatrix.size-4){
            val curVal = mainMatrix[r][c]     // 4,0
            val n1val = mainMatrix[r][c+1]    // 4,1
            val n2val = mainMatrix[r][c+2]    // 4,2
            val n3val = mainMatrix[r][c+3]    // 4,3

            val winCond =
                (curVal != " ") &&
                        (curVal == marking) &&
                        (curVal == n1val) &&
                        (curVal == n2val) &&
                        (curVal == n3val)

            if (winCond){
                returnStr = curVal
                return returnStr
            }else{
                returnStr = ""
            }
        }
    }

    // check vertical win
    for (r in mainMatrix.size - 1 downTo 3){
        for (c in 0 until mainMatrix.size){
            val curVal = mainMatrix[r][c]     // 4,0
            val n1val = mainMatrix[r-1][c]    // 3,0
            val n2val = mainMatrix[r-2][c]    // 2,0
            val n3val = mainMatrix[r-3][c]    // 1,0

            val winCond =
                (curVal != " ") &&
                        (curVal == marking) &&
                        (curVal == n1val) &&
                        (curVal == n2val) &&
                        (curVal == n3val)

            if (winCond){
                returnStr = curVal
                return returnStr
            }else{
                returnStr = ""
            }
        }
    }

    // check diagonal win from bottom left to top right (LTR)
    for (r in mainMatrix.size - 1 downTo 3){
        for (c in mainMatrix.size - 4 downTo 0){
            val curVal = mainMatrix[r][c]       // 4,1
            val n1val = mainMatrix[r-1][c+1]    // 3,2
            val n2val = mainMatrix[r-2][c+2]    // 2,3
            val n3val = mainMatrix[r-3][c+3]    // 1,4
            val winCond =
                (curVal != " ") &&
                        (curVal == marking) &&
                        (curVal == n1val) &&
                        (curVal == n2val) &&
                        (curVal == n3val)
            if (winCond){
                returnStr = curVal
                return returnStr
            }else{
                returnStr = ""
            }
        }
    }

    // check diagonal win from bottom right to top left (RTL)
    for (r in mainMatrix.size - 1 downTo 3){
        for (c in mainMatrix.size - 1 downTo 3){
            val curVal = mainMatrix[r][c]       // 4,4|4,3
            val n1val = mainMatrix[r-1][c-1]    // 3,3|3,2
            val n2val = mainMatrix[r-2][c-2]    // 2,2|2,1
            val n3val = mainMatrix[r-3][c-3]    // 1,1|1,0
            val winCond =
                (curVal != " ") &&
                        (curVal == marking) &&
                        (curVal == n1val) &&
                        (curVal == n2val) &&
                        (curVal == n3val)
            if (winCond){
                returnStr = curVal
                return returnStr
            }else{
                returnStr = ""
            }
        }
    }

    // sum draw
    var sumColLength = 0
    for (r in totalRow-1 downTo 0){
        for (c in 0 until totalCol){
            val contentStr = mainMatrix[r][c]
            val sumCond = contentStr != " "
            if (sumCond) {
                sumColLength ++
            }
        }
    }

    if (sumColLength == totalCol * totalRow){
        returnStr = "draw"
        return returnStr
    }

    return returnStr
}

fun buildGame(row:Int,
              col:Int,
              firstPlayer:String,
              secondPlayer:String, gameNum:Int = 1):String{

    var gameResult = ""
    var _firstPlayer = firstPlayer
    var _secondPlayer = secondPlayer

    // initialize string of matrix
    val matrix = mutableListOf<MutableList<String>>()
    repeat(row){
        val rowTemp = mutableListOf<String>()
        repeat(col){
            rowTemp += " "
        }
        matrix += rowTemp
    }

    printMatrix(row,col,matrix)

    var firstMark = "o"
    var secondMark = "*"
    var prompt=""
    var promptFirstResult = true
    var promptSecondResult = true
    var winCondStr: String

    // swapping every even turn
    if (gameNum % 2 == 0){
        var temp = firstMark
        firstMark   = secondMark
        secondMark  = temp

        temp = _firstPlayer
        _firstPlayer = _secondPlayer
        _secondPlayer = temp
    }

    do {
        if (promptSecondResult) {
            println("$_firstPlayer's turn:")
            prompt = readln()
            promptFirstResult = promptCheck(prompt, col, row, firstMark, matrix)
            winCondStr = matrixWinProcessing(matrix,firstMark)

            when(winCondStr){
                "o" -> {
                    println("Player $firstPlayer won")
                    gameResult = "o"
                    break
                }
                "*" ->{
                    println("Player $secondPlayer won")
                    gameResult = "*"
                    break
                }
                "draw" -> {
                    println("It is a draw")
                    gameResult = "draw"
                    break
                }
                else -> {}
            }
        }

        if(promptFirstResult) {
            println("$_secondPlayer's turn:")
            prompt = readln()
            promptSecondResult = promptCheck(prompt, col, row, secondMark, matrix)
            winCondStr = matrixWinProcessing(matrix,secondMark)

            when(winCondStr){
                "o" -> {
                    println("Player $firstPlayer won")
                    gameResult = "o"
                    break
                }
                "*" ->{
                    println("Player $secondPlayer won")
                    gameResult = "*"
                    break
                }
                "draw" -> {
                    println("It is a draw")
                    gameResult = "draw"
                    break
                }
                else -> {}
            }
        }
    }while (prompt != "end")

    return gameResult
}

fun main() {
    println("Connect Four")
    println("First player's name:")
    val firstPlayer = readln()
    println("Second player's name:")
    val secondPlayer= readln()

    var boardSizeInput:String
    var row:Int
    var col:Int
    var rowCond:Boolean
    var colCond:Boolean
    do{
        println("Set the board dimensions (Rows x Columns)")
        println("Press Enter for default (6 x 7)")
        val boardSizeRegex = Regex("""^\s*\d+\s*[xX]\s*\d+\s*$""")

        boardSizeInput = readln()
        val defaultCond = boardSizeInput.isEmpty()
        if (defaultCond){
            boardSizeInput = "6 x 7"
        }

        val boardCond = boardSizeRegex.matches(boardSizeInput)
        if(!boardCond){
            println("Invalid input")
            row = 0
            col = 0
            rowCond = false
            colCond = false
        }else {
            val boardNum: List<String> = boardSizeInput.
                replace(" ", "").
                replace("\t", "").
                split("x", ignoreCase = true)

            row = boardNum[0].toInt()
            col = boardNum[1].toInt()
            rowCond = row in 5..9
            colCond = col in 5..9
            if (!rowCond) {
                println("Board rows should be from 5 to 9")
            } else if (!colCond) {
                println("Board columns should be from 5 to 9")
            }
        }
        val whileCond = !boardCond || !rowCond || !colCond
    }while (whileCond)

    // single or multiple games
    var numGamesWrong:Boolean
    var numGames=""
    do {
        println("Do you want to play single or multiple games?")
        println("For a single game, input 1 or press Enter")
        println("Input a number of games:")
        val numGamesInput = readln()
        val defaultCondNumGames = numGamesInput.isEmpty()
        if (defaultCondNumGames) {
            numGames = "1"
            numGamesWrong = false
        } else {
            val numGamesCond = Regex("[1-9]+").matches(numGamesInput)
            if (!numGamesCond) {
                println("Invalid input")
                numGamesWrong = true
            }else{
                numGames = numGamesInput
                numGamesWrong = false
            }
        }
    }while(numGamesWrong)

    println("$firstPlayer VS $secondPlayer")
    println("$row X $col board")

    val numGamesInt = numGames.toInt()
    if(numGamesInt == 1){
        println("Single Game")
        buildGame(row, col, firstPlayer, secondPlayer)
    }else{
        println("Total $numGamesInt games")
        var score1 = 0
        var score2 = 0
        for (i in 1..numGamesInt) {
            println("Game #$i")

            when(buildGame(row, col, firstPlayer, secondPlayer, i)){
                "o"     -> score1 += 2
                "*"     -> score2 += 2
                "draw"  -> {
                    score1++
                    score2++
                }
            }

            println("Score")
            println("$firstPlayer: $score1 $secondPlayer: $score2")
        }
    }

    print("Game over!")
}
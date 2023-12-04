import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val board = Array(3) { CharArray(3) { ' ' } }

    printBoard(board)

    while (true) {
        playerMove(board, scanner)
        printBoard(board)

        if (checkWinner(board, 'X')) {
            println("You win! Congratulations!")
            break
        }

        if (isBoardFull(board)) {
            println("It's a draw! The board is full.")
            break
        }

        computerMove(board)
        printBoard(board)

        if (checkWinner(board, 'O')) {
            println("Computer wins! Better luck next time.")
            break
        }

        if (isBoardFull(board)) {
            println("It's a draw! The board is full.")
            break
        }
    }
}

fun printBoard(board: Array<CharArray>) {
    println("  0 1 2")
    for (i in board.indices) {
        print("$i ")
        for (j in board[i].indices) {
            print("${board[i][j]} ")
        }
        println()
    }
}

fun playerMove(board: Array<CharArray>, scanner: Scanner) {
    while (true) {
        print("Enter your move (row column): ")
        val row = scanner.nextInt()
        val col = scanner.nextInt()

        if (isValidMove(board, row, col)) {
            board[row][col] = 'X'
            break
        } else {
            println("Invalid move. Try again.")
        }
    }
}

fun computerMove(board: Array<CharArray>) {
    val random = Random()
    while (true) {
        val row = random.nextInt(3)
        val col = random.nextInt(3)

        if (isValidMove(board, row, col)) {
            println("Computer chooses: $row $col")
            board[row][col] = 'O'
            break
        }
    }
}

fun isValidMove(board: Array<CharArray>, row: Int, col: Int): Boolean {
    return row in 0..2 && col in 0..2 && board[row][col] == ' '
}

fun checkWinner(board: Array<CharArray>, symbol: Char): Boolean {
    for (i in 0..2) {
        if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
            return true
        }
        if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
            return true
        }
    }
    if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
        return true
    }
    if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
        return true
    }
    return false
}

fun isBoardFull(board: Array<CharArray>): Boolean {
    for (row in board) {
        for (cell in row) {
            if (cell == ' ') {
                return false
            }
        }
    }
    return true
}

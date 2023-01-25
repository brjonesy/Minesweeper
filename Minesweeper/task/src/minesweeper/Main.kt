@file:Suppress("NAME_SHADOWING")

package minesweeper

import kotlin.random.Random


class Minesweeper(private var mineNumber: Int){

    private fun generateMines(): MutableList<Int> {
        val randomList = generateSequence {
            Random.nextInt(0,80)
        }
            .distinct()
            .take(mineNumber)
            .sorted()
            .toMutableList()
        //println(randomList)
        return randomList
    }

    private fun makeTable(list: MutableList<Int>): MutableList<MutableList<String>> {
        val table = mutableListOf(
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),
            mutableListOf(".", ".", ".", ".", ".", ".", ".", ".", "."),)
        for (j in 0..80) {
            if(j in list) {
                table[j/9][j%9] = "X"
            }
        }
        return table
    }

    private fun printTable(table: MutableList<MutableList<String>>){
        for(i in 0 until 9) {
            for (j in 0 until 9) {
                print(table[i][j])
            }
            println()
        }

    }

    fun play(){
        printTable(makeTable(generateMines()))
    }
}

fun main() {

    println("How many mines do you want on the field?")
    val mineNumber = readLine()!!.toInt()

    val game = Minesweeper(mineNumber)
    game.play()


}

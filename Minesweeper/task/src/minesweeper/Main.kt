@file:Suppress("NAME_SHADOWING")

package minesweeper

import kotlin.random.Random


fun main() {

    println("How many mines do you want on the field?")
    val mineNumber = readLine()!!.toInt()
    /*fun generateMines(mineNumber: Int): MutableList<Int> {
        val randomList = mutableListOf<Int>()
        for(i in 0 until mineNumber){
            randomList.add(Random.nextInt(0,80))
        }
        randomList.sort()
        println(randomList)
        return randomList
    }*/

    fun generateMines(mineNumber: Int): MutableList<Int> {
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

    fun makeTable(list: MutableList<Int>): MutableList<MutableList<String>> {
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

    fun printTable(table: MutableList<MutableList<String>>){
        for(i in 0 until 9) {
            for (j in 0 until 9) {
                print(table[i][j])
            }
            println()
        }

    }

    printTable(makeTable(generateMines(mineNumber)))

}

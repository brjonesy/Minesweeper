@file:Suppress("NAME_SHADOWING")

package minesweeper

import kotlin.random.Random

const val SAFE_CELL = "."
const val MINE_CELL = "X"
const val DEFAULT_FIELD_X = 9
const val DEFAULT_FIELD_Y = 9

class Minesweeper(
    private var sizeX: Int = DEFAULT_FIELD_X,
    private var sizeY: Int = DEFAULT_FIELD_Y,
    private var mineNumber: Int,
    ){

    private val mineList = generateMineList()
    private val mineField = makeMineField(mineList)
    private val mineFieldWithNumbers = bombNearCount()


    private fun makeMineField(list: MutableList<Int>): MutableList<MutableList<String>> {
        val mineField = MutableList(sizeX){MutableList(sizeY){ SAFE_CELL} }
        for (j in 0 until sizeX * sizeY) {
            if(j in list) {
                mineField[j/sizeX][j%sizeX] = MINE_CELL
            }
        }
        return mineField
    }

    private fun generateMineList(): MutableList<Int> {
        val randomList = generateSequence {
            Random.nextInt(0,80)
        }
            .distinct()
            .take(mineNumber)
            .sorted()
            .toMutableList()
        return randomList
    }

    fun printMineField(){
        for(i in 0 until sizeX) {
            for (j in 0 until sizeY) {
                print(mineField[i][j])
            }
            println()
        }
        println()
    }

    fun printMineFieldWithNumbers(){
        for(i in 0 until sizeX) {
            for (j in 0 until sizeY) {
                print(mineFieldWithNumbers[i][j])
            }
            println()
        }
        println()
    }


    fun bombNearCount(): MutableList<MutableList<String>>{
        var bombCount = 0
        val mineFieldWithNumbers = mineField
        for(row in 0 until sizeY) {
            for (column in 0 until sizeX) {
                //left-up
                if(row == 0 && column == 0) {

                    if(mineField[0][1] =="X") bombCount++
                    if(mineField[1][1] == "X") bombCount++
                    if(mineField[1][0] == "X") bombCount++
                }
                //right-up
                else if(row == 0 && column == sizeY-1) {
                    if(mineField[0][column-1] == "X") bombCount++
                    if(mineField[1][column-1] == "X") bombCount++
                    if(mineField[1][column] == "X") bombCount++
                }
                //right-down
                else if(row == sizeX-1 && column == sizeY-1) {
                    if(mineField[row][column-1] == "X") bombCount++
                    if(mineField[row-1][column-1] == "X") bombCount++
                    if(mineField[row-1][column] == "X") bombCount++
                }
                //left-down
                else if (row == sizeX-1 && column == 0) {
                    if(mineField[row][1] == "X") bombCount++
                    if(mineField[row-1][1] == "X") bombCount++
                    if(mineField[row-1][0] == "X") bombCount++
                }

                //left
                else if (column == 0) {
                    if(mineField[row-1][0] == "X") bombCount++
                    if(mineField[row-1][1] == "X") bombCount++
                    if(mineField[row][1] == "X") bombCount++
                    if(mineField[row+1][1] == "X") bombCount++
                    if(mineField[row+1][0] == "X") bombCount++
                }
                //bottom
                else if (row == sizeY-1) {
                    if(mineField[row][column-1] == "X") bombCount++
                    if(mineField[row-1][column-1] == "X") bombCount++
                    if(mineField[row-1][column] == "X") bombCount++
                    if(mineField[row-1][column+1] == "X") bombCount++
                    if(mineField[row][column+1] == "X") bombCount++

                }
                //right
                else if (column == sizeX-1) {
                    if(mineField[row-1][column] == "X") bombCount++
                    if(mineField[row-1][column-1] == "X") bombCount++
                    if(mineField[row][column-1] == "X") bombCount++
                    if(mineField[row+1][column-1] == "X") bombCount++
                    if(mineField[row+1][column] == "X") bombCount++

                }
                //top
                else if (row == 0) {
                    if(mineField[0][column-1] == "X") bombCount++
                    if(mineField[1][column-1] == "X") bombCount++
                    if(mineField[1][column] == "X") bombCount++
                    if(mineField[1][column+1] == "X") bombCount++
                    if(mineField[0][column+1] == "X") bombCount++
                }
                //middle
                else {
                    if(mineField[row][column-1] == "X") bombCount++
                    if(mineField[row+1][column-1] == "X") bombCount++
                    if(mineField[row+1][column] == "X") bombCount++
                    if(mineField[row+1][column+1] == "X") bombCount++
                    if(mineField[row][column+1] == "X") bombCount++
                    if(mineField[row-1][column+1] == "X") bombCount++
                    if(mineField[row-1][column] == "X") bombCount++
                    if(mineField[row-1][column-1] == "X") bombCount++

                    }

                if(bombCount != 0 && mineFieldWithNumbers[row][column] != "X") {
                    mineFieldWithNumbers[row][column] = bombCount.toString()
                }
                bombCount = 0
                }

            }
        return mineFieldWithNumbers
        }

    }

fun main() {

    println("How many mines do you want on the field?")
    val mineNumber = readLine()!!.toInt()

    val game = Minesweeper(9, 9, mineNumber)
    game.printMineField()
    game.bombNearCount()
    //game.printMineFieldWithNumbers()
}

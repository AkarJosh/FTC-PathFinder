package com.cheeseBoy.pathFinder

import kotlin.math.pow
import kotlin.math.sqrt

class Grid(fieldSize: Int, nodeSize: Double) {


    val grid = mutableMapOf<Int, Node>()
    val rows: Int = 0
    val cols: Int = rows
    val size: Int = 0

    init {
        val size = ((fieldSize / nodeSize).pow(2.0)).toInt()
        val rows = sqrt(size.toDouble()).toInt()
        val cols = rows
        var id = 0
        for (row in 1..rows)
            for (col in 1..cols) {
                val node =  Node(id, row, col, this)
                this.grid[id] = node
                id += 1
            }
        for(node in this.grid.values){
            node.updateNeighbors()
        }
    }

    fun getNode(id: Int): Node? {
        return this.grid[id]
    }
}

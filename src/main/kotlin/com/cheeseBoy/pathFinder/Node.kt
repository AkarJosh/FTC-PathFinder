package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d
import com.noahbres.meepmeep.core.util.FieldUtil

class Node(val id: Int, row: Int, col: Int, val size: Double, val grid: Grid) {
    enum class NodeType{
        BARRIER,
        PATH
    }
    var type: NodeType
    val row: Int
    val col: Int
    val neighbors = mutableListOf<Node>()
    var vector = Vector2d()
    init {
        type = NodeType.PATH

        this.row = row
        this.col = col

        this.vector = FieldUtil.screenCoordsToFieldCoords(Vector2d(col.toDouble(), row.toDouble()))
    }

    fun updateNeighbors(){
        if(row < grid.rows - 1 && grid.grid[this.id + grid.size]!!.type == NodeType.PATH)
            this.neighbors.add(grid.grid[this.id + grid.size]!!)

        if(row > 0 && grid.grid[this.id - grid.size]!!.type == NodeType.PATH)
            this.neighbors.add(grid.grid[this.id - grid.size]!!)

        try {
            if(col < grid.cols - 1 && grid.grid[this.id + 1]!!.type == NodeType.PATH)
                this.neighbors.add(grid.grid[this.id + 1]!!)
        }catch (e: NullPointerException){

        }


        try {
        if(col > 0 && grid.grid[this.id - 1]!!.type == NodeType.PATH)
            this.neighbors.add(grid.grid[this.id - 1]!!)
        }catch(e: NullPointerException){

        }
    }
}
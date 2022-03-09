package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d

class Node(val id: Int, row: Double, col: Double, val grid: Grid) {
    enum class NodeType{
        BARRIER,
        PATH
    }
    private var type: NodeType = NodeType.PATH
    val row: Double
    val col: Double
    val neighbors = mutableListOf<Node>()
    var vector = Vector2d()
    init {

        this.row = row
        this.col = col

        this.vector = Vector2d(col, row)
    }

    fun updateNeighbors(){
        /*if(row < grid.rows - 1 && grid.grid[this.id + grid.fieldSize]!!.type == NodeType.PATH)
            this.neighbors.add(grid.grid[this.id + grid.fieldSize]!!)

        if(row > 0 && grid.grid[this.id - grid.fieldSize]!!.type == NodeType.PATH)
            this.neighbors.add(grid.grid[this.id - grid.fieldSize]!!)

        try {
            if(col < grid.rows - 1 && grid.grid[this.id + 1]!!.type == NodeType.PATH)
                this.neighbors.add(grid.grid[this.id + 1]!!)
        }catch (_: NullPointerException){

        }
        try {
        if(col > 0 && grid.grid[this.id - 1]!!.type == NodeType.PATH)
            this.neighbors.add(grid.grid[this.id - 1]!!)
        }catch(_: NullPointerException){

        }*/
    }

    fun switchType(){
        when(type){
            NodeType.BARRIER -> this.type = NodeType.PATH
            NodeType.PATH -> this.type = NodeType.BARRIER
        }
    }
}
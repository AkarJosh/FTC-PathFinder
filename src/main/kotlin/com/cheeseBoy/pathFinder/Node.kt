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
    /*val isXBorder: Boolean
    val isYBorder: Boolean*/
    init {

        this.row = row
        this.col = col

        this.vector = Vector2d(col, row)
    }

    fun updateNeighbors(){
        neighbors.add(grid.getNode(id)!!) //Center
        neighbors.add(grid.getNode(id - (grid.fieldSize * 2 + 1))!!) //Bottom
        neighbors.add(grid.getNode(id + (grid.fieldSize * 2 + 1))!!) //Top
        neighbors.add(grid.getNode(id - 1)!!) //Left
        neighbors.add(grid.getNode(id + 1)!!) //Right
        neighbors.add(grid.getNode(id - (grid.fieldSize * 2))!!) //Bottom Right
        neighbors.add(grid.getNode(id - (grid.fieldSize * 2 + 2))!!) //Bottom Left
        neighbors.add(grid.getNode(id + (grid.fieldSize * 2))!!) //Top Left
        neighbors.add(grid.getNode(id + (grid.fieldSize * 2 + 2))!!) //Top Right
    }

    fun switchType(){
        when(type){
            NodeType.BARRIER -> this.type = NodeType.PATH
            NodeType.PATH -> this.type = NodeType.BARRIER
        }
    }
}
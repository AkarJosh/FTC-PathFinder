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
    }

    fun switchType(){
        when(type){
            NodeType.BARRIER -> this.type = NodeType.PATH
            NodeType.PATH -> this.type = NodeType.BARRIER
        }
    }
}
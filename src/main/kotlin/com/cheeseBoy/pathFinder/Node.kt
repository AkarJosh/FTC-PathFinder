package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d

class Node(row: Double, col: Double, private val grid: Grid) {
    enum class NodeType{
        BARRIER,
        PATH
    }
    private var type: NodeType = NodeType.PATH
    private val row: Double
    private val col: Double
    val neighbors = mutableListOf<Node>()
    var vector = Vector2d()
    init {

        this.row = row
        this.col = col

        this.vector = Vector2d(col, row)
    }

    fun updateNeighbors() {

        // Checks if the potential neighbor is a barrier and will through null pointer exception if current node is on the border

        //Bottom
        try {
            if (grid.getNode(Vector2d(this.vector.x, this.vector.y - grid.nodeSize))!!.type != NodeType.BARRIER)
                neighbors.add(grid.getNode(Vector2d(this.vector.x, this.vector.y - grid.nodeSize))!!)
        } catch (_: java.lang.NullPointerException) {}

        //Top
        try {
            if (grid.getNode(Vector2d(this.vector.x, this.vector.y + grid.nodeSize))!!.type != NodeType.BARRIER)
                neighbors.add(grid.getNode(Vector2d(this.vector.x, this.vector.y + grid.nodeSize))!!)
        } catch (_: java.lang.NullPointerException) {}

        //Left
        try {
            if (grid.getNode(Vector2d(this.vector.x - grid.nodeSize, this.vector.y))!!.type != NodeType.BARRIER)
                neighbors.add(grid.getNode(Vector2d(this.vector.x - grid.nodeSize, this.vector.y))!!)
        } catch (_: java.lang.NullPointerException) {}

        //Right
        try {
            if (grid.getNode(Vector2d(this.vector.x + grid.nodeSize, this.vector.y))!!.type != NodeType.BARRIER)
                neighbors.add(grid.getNode(Vector2d(this.vector.x + grid.nodeSize, this.vector.y))!!)
        } catch (_: java.lang.NullPointerException) {}

        //Bottom Left
        try {
            if (grid.getNode(Vector2d(this.vector.x - grid.nodeSize, this.vector.y - grid.nodeSize))!!.type != NodeType.BARRIER)
                neighbors.add(grid.getNode(Vector2d(this.vector.x - grid.nodeSize, this.vector.y - grid.nodeSize))!!)
        } catch (_: java.lang.NullPointerException) {}

        //Bottom Right
        try {
            if (grid.getNode(Vector2d(this.vector.x + grid.nodeSize, this.vector.y - grid.nodeSize))!!.type != NodeType.BARRIER)
                neighbors.add(grid.getNode(Vector2d(this.vector.x + grid.nodeSize, this.vector.y - grid.nodeSize))!!)
        } catch (_: java.lang.NullPointerException) {}

        //Top Left
        try {
            if (grid.getNode(Vector2d(this.vector.x - grid.nodeSize, this.vector.y + grid.nodeSize))!!.type != NodeType.BARRIER)
                neighbors.add(grid.getNode(Vector2d(this.vector.x- grid.nodeSize, this.vector.y + grid.nodeSize))!!)
        } catch (_: java.lang.NullPointerException) {}

        //Bottom Right
        try {
            if (grid.getNode(Vector2d(this.vector.x + grid.nodeSize, this.vector.y + grid.nodeSize))!!.type != NodeType.BARRIER)
                neighbors.add(grid.getNode(Vector2d(this.vector.x + grid.nodeSize, this.vector.y + grid.nodeSize))!!)
        } catch (_: java.lang.NullPointerException) {}
    }

    fun switchType(){
        when(type){
            NodeType.BARRIER -> this.type = NodeType.PATH
            NodeType.PATH -> this.type = NodeType.BARRIER
        }
    }
}
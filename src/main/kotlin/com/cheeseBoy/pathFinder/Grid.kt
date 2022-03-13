package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d

class Grid(fieldSize: Int, val nodeSize: Double) {


    private val grid = mutableMapOf<Vector2d, Node>()

    init {
        var r = -fieldSize.toDouble()
        var c = r
        while(r <= fieldSize) {
            while (c <= fieldSize) {
                val node = Node(r, c, this)
                this.grid[Vector2d(r, c)] = node
                c += nodeSize
            }
            c = -fieldSize .toDouble()
            r += nodeSize
        }

        for(node in this.grid.values){
            node.updateNeighbors()
        }
    }
    fun getNode(vector: Vector2d): Node? {
        return this.grid[vector]
    }

    fun getNodes(): List<Node>{
        return grid.values.toList()
    }
}
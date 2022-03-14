package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d

class Grid(val nodeSize: Double) {


    private val grid = mutableMapOf<Vector2d, Node>()

    fun getNode(vector: Vector2d): Node? {
        if(!this.grid.contains(vector)){
            val node = Node(vector, this)
            this.grid[vector] = node
        }
        return this.grid[vector]
    }

    fun getNodes(): List<Node>{
        return grid.values.toList()
    }
}
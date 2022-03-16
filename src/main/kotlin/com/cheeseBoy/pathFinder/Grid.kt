package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d
import com.noahbres.meepmeep.MeepMeep

class Grid(fieldSize: Double, val nodeSize: Double, meepMeep: MeepMeep) {


    private val grid = mutableMapOf<Vector2d, Node>()

    init {
        var r = -fieldSize
        var c = r
        while(r <= fieldSize) {
            while (c <= fieldSize) {
                val node = Node(Vector2d(r, c), this, meepMeep)
                this.grid[Vector2d(r, c)] = node
                c += nodeSize
            }
            c = -fieldSize
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
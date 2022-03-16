package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d
import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.core.entity.BoxIndicatorEntity
import java.awt.Color

class Node(val vector: Vector2d, private val grid: Grid, val meepMeep: MeepMeep) {
    enum class NodeType{
        BARRIER,
        PATH
    }
    var type: NodeType = NodeType.PATH
    val neighbors = mutableListOf<Node>()
    var currentColor: Color = Color(0, 0, 0, 0)
    val boxEntity: BoxIndicatorEntity

    init {
        boxEntity = BoxIndicatorEntity(meepMeep, this.currentColor, this.vector, grid.nodeSize)
        meepMeep.addBox(boxEntity)
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

    fun setColor(color: Color){
        this.currentColor = color
        meepMeep.switchBoxColor(boxEntity, color)
    }
}
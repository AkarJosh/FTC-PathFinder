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

        val neighborPos = listOf(
            Vector2d(this.vector.x + grid.nodeSize, this.vector.y),
            Vector2d(this.vector.x - grid.nodeSize, this.vector.y),
            Vector2d(this.vector.x, this.vector.y + grid.nodeSize),
            Vector2d(this.vector.x, this.vector.y - grid.nodeSize),
            Vector2d(this.vector.x + grid.nodeSize, this.vector.y + grid.nodeSize),
            Vector2d(this.vector.x + grid.nodeSize, this.vector.y - grid.nodeSize),
            Vector2d(this.vector.x - grid.nodeSize, this.vector.y + grid.nodeSize),
            Vector2d(this.vector.x - grid.nodeSize, this.vector.y - grid.nodeSize),
            Vector2d(this.vector.x + (2 * grid.nodeSize), this.vector.y),
            Vector2d(this.vector.x - (2 * grid.nodeSize), this.vector.y),
            Vector2d(this.vector.x, this.vector.y + (2 * grid.nodeSize)),
            Vector2d(this.vector.x, this.vector.y - (2 * grid.nodeSize)),
            Vector2d(this.vector.x + (2 * grid.nodeSize), this.vector.y + (2 * grid.nodeSize)),
            Vector2d(this.vector.x + (2 * grid.nodeSize), this.vector.y - (2 * grid.nodeSize)),
            Vector2d(this.vector.x - (2 * grid.nodeSize), this.vector.y + (2 * grid.nodeSize)),
            Vector2d(this.vector.x - (2 * grid.nodeSize), this.vector.y - (2 * grid.nodeSize)),
            Vector2d(this.vector.x + grid.nodeSize, this.vector.y + (2 * grid.nodeSize)),
            Vector2d(this.vector.x + grid.nodeSize, this.vector.y - (2 * grid.nodeSize)),
            Vector2d(this.vector.x - grid.nodeSize, this.vector.y + (2 * grid.nodeSize)),
            Vector2d(this.vector.x - grid.nodeSize, this.vector.y - (2 * grid.nodeSize)),
            Vector2d(this.vector.x + (2 * grid.nodeSize), this.vector.y + grid.nodeSize),
            Vector2d(this.vector.x + (2 * grid.nodeSize), this.vector.y - grid.nodeSize),
            Vector2d(this.vector.x - (2 * grid.nodeSize), this.vector.y + grid.nodeSize),
            Vector2d(this.vector.x - (2 * grid.nodeSize), this.vector.y - grid.nodeSize)
        )

        for(pos in neighborPos){
            try {
                if(this.grid.getNode(pos)!!.type != NodeType.BARRIER) {
                    this.neighbors.add(this.grid.getNode(pos)!!)
                }
            } catch (_: NullPointerException) {}
        }
    }

    fun setColor(color: Color){
        this.currentColor = color
        meepMeep.switchBoxColor(boxEntity, color)
    }
}
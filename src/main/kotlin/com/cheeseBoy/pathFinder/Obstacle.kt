package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d

class Obstacle(topLeft: Vector2d, width: Double, length: Double, grid: Grid) {
    val nodes = mutableListOf<Node?>()
    init {
        var w = topLeft.x
        var l = topLeft.y
        while(w <= width) {
            while (l <= length) {
                nodes.add(grid.getNode(Vector2d(w, l)))
                grid.getNode(Vector2d(w, l))!!.type = Node.NodeType.BARRIER
                l += grid.nodeSize
            }
            l = 0.0
            w += grid.nodeSize
        }
        for(node in nodes)
            println(node!!.vector)
    }
}
package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Vector2d

class Obstacle(topLeft: Vector2d, width: Double, length: Double) {
    val nodes: MutableList<Node>
    init {
        nodes = listOf()
    }
}
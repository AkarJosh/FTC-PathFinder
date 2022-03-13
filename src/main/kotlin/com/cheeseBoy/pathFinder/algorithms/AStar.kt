package com.cheeseBoy.pathFinder.algorithms

import com.cheeseBoy.pathFinder.Grid
import com.cheeseBoy.pathFinder.Node
import java.util.*
import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.math.pow
import kotlin.math.sqrt

class AStar {

    fun reconstructPath(cameFrom: Map<Node, Node>, current: Node): Set<Node>{
        var currentNode = current
        val totalPath = mutableSetOf(currentNode)
        while(cameFrom.containsKey(currentNode)){
            currentNode = cameFrom[currentNode]!!
            totalPath.add(currentNode)
        }
        return totalPath.reversed().toSet()
    }

    fun calculatePath(start: Node, end: Node, grid: Grid): Set<Node>? {

        val compare = compareBy<Pair<Double, Node>> {it.first}
        val openSet = PriorityQueue(compare)
        openSet.add(0.0 to start)

        val cameFrom = mutableMapOf<Node, Node>()

        val gScore = mutableMapOf<Node, Double>()
        val fScore = mutableMapOf<Node, Double>()

        for(node in grid.getNodes()){
            gScore[node] = POSITIVE_INFINITY
            fScore[node] = POSITIVE_INFINITY
        }

        gScore[start] = 0.0
        fScore[start] = start.vector distTo end.vector



        while (!openSet.isEmpty()){
            val current = openSet.poll().second

            if (current == end){
                return reconstructPath(cameFrom, current)
            }

            current.neighbors.forEach {
                val tentativeGScore = gScore[current]!! + (current.vector distTo it.vector)
                if(tentativeGScore < gScore[it]!!){
                    cameFrom[it] = current
                    gScore[it] = tentativeGScore
                    fScore[it] = tentativeGScore + (it.vector distTo  end.vector)
                    if(!openSet.contains(Pair(Any(), it))){
                        openSet.add(Pair(fScore[it], it) as Pair<Double, Node>?)
                    }
                }
            }
        }
        return null
    }
}


package com.cheeseBoy.pathFinder.algorithms

import com.cheeseBoy.pathFinder.Grid
import com.cheeseBoy.pathFinder.Node
import java.awt.Color
import java.util.*
import kotlin.Double.Companion.POSITIVE_INFINITY

class AStar {
    private fun reconstructPath(cameFrom: Map<Node, Node>, current: Node): Set<Node>{
        var currentNode = current
        val totalPath = mutableSetOf(currentNode)
        while(cameFrom.containsKey(currentNode)){
            currentNode = cameFrom[currentNode]!!
            totalPath.add(currentNode)
            currentNode.setColor(Color.ORANGE)
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

        while(!openSet.isEmpty()){
            val current = openSet.poll().second

            current.updateNeighbors()

            if (current.vector == end.vector){
                return reconstructPath(cameFrom, current)
            }

            current.neighbors.forEach {neighbor ->
                val tentativeGScore = gScore[current]!! + (current.vector distTo neighbor.vector)
                if(tentativeGScore < gScore[neighbor]!!){
                    cameFrom[neighbor] = current
                    gScore[neighbor] = tentativeGScore
                    fScore[neighbor] = tentativeGScore + (neighbor.vector distTo end.vector)
                    if(!openSet.contains(Pair(Any(), neighbor))){
                        openSet.add(Pair(fScore[neighbor], neighbor) as Pair<Double, Node>?)
                        neighbor.setColor(Color.GREEN)
                    }
                }
            }

            if (current.vector != start.vector){
                current.setColor(Color.RED)
            }
        }

        println("PATH NOT FOUND")
        return null
    }
}


package com.cheeseBoy.pathFinder.algorithms

import com.cheeseBoy.pathFinder.Grid
import com.cheeseBoy.pathFinder.Node
import com.noahbres.meepmeep.MeepMeep
import java.util.PriorityQueue
import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.math.abs

class AStar: Algorithm {

    fun heuristic(node1: Node?, node2: Node?): Double {
        return (abs(node1!!.row - node2!!.row) + abs(node1.col - node2.col))
    }
    override fun calculatePath(start: Int, end: Int, grid: Grid): Map<Int, Int> {
        var count = 0
        val compare = compareBy<Triple<Double, Int, Int>> { it.first }
        val openSet = PriorityQueue(compare)
        openSet.add(Triple(0.0, count, start))
        val cameFrom = mutableMapOf<Int, Int>()
        val gScore = mutableMapOf<Int, Double>()
        for (i in grid.grid.keys) gScore[i] = POSITIVE_INFINITY
        val fScore = mutableMapOf<Int, Double>()
        for (i in grid.grid.keys) fScore[i] = POSITIVE_INFINITY
        fScore[start] = heuristic(grid.getNode(start), grid.getNode(end))


        val openSetHash = mutableSetOf(start)

        while(openSet.isNotEmpty()){
            val current = openSet.peek().third
            openSetHash.remove(current)

            if(current == end) return cameFrom

            for(neighbor in grid.grid[current]!!.neighbors){
                val tempGScore = gScore[current]!! + 1

                if(tempGScore < gScore[neighbor.id]!!){
                    cameFrom[neighbor.id] = current
                    gScore[neighbor.id] = tempGScore
                    fScore[neighbor.id] = tempGScore + heuristic(grid.getNode(neighbor.id), grid.getNode(end))
                    if (!openSetHash.contains(neighbor.id)){
                        count += 1
                        openSet.add(Triple(fScore[neighbor.id], count, neighbor.id) as Triple<Double, Int, Int>?)
                        openSetHash.add(neighbor.id)
                    }
                }
            }
        }


        return cameFrom
    }
}


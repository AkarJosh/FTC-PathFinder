package com.cheeseBoy.pathFinder

import com.noahbres.meepmeep.MeepMeep

class Grid(fieldSize: Int, nodeSize: Double) {


    val grid = mutableMapOf<Int, Node>()

    init {

        var id = 0
        var r = -fieldSize.toDouble()
        var c = r
        while(r <= fieldSize) {
            while (c <= fieldSize) {
                val node = Node(id, r, c, this)
                this.grid[id] = node
                id += 1
                c += nodeSize
            }
            c = -fieldSize .toDouble()
            r += nodeSize
        }

        for(node in this.grid.values){
            node.updateNeighbors()
        }
    }
    fun getNode(id: Int): Node? {
        return this.grid[id]
    }
}
fun main(){
    val meepMeep = MeepMeep(1000, 5)
    for(node in Grid(72, 0.1).grid.values) {
        meepMeep.addPoint(node.vector)
    }


    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.95f)
        .start()
}

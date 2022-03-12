package com.cheeseBoy.pathFinder

class Grid(val fieldSize: Int, nodeSize: Double) {


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
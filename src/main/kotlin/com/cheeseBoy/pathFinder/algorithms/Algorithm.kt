package com.cheeseBoy.pathFinder.algorithms

import com.cheeseBoy.pathFinder.Grid

interface Algorithm {
    fun calculatePath(start: Int, end: Int, grid: Grid): Map<Int, Int>
}
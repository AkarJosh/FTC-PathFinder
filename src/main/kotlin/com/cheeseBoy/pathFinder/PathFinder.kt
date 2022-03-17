package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.cheeseBoy.pathFinder.algorithms.AStar
import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.DriveShim
import java.awt.Color

class PathFinder(windowSize: Int, fps: Int = 30, nodeSize: Double = 0.5) {

    val meepMeep: MeepMeep
    val grid: Grid
    val algo = AStar()

    init {
        System.setProperty("sun.java2d.opengl", "true")
        meepMeep = MeepMeep(windowSize, fps)
        grid = Grid(72.0, nodeSize, meepMeep)
    }

    fun findPath(start: Vector2d, end: Vector2d, vararg obstacle: Obstacle) {

        obstacle.forEach {

        }
        val startNode = Node(start, grid, meepMeep)
        val endNode = Node(end, grid, meepMeep)

        val path = algo.calculatePath(startNode, endNode, grid) as MutableSet<Node>

        startNode.setColor(Color.BLUE)
        endNode.setColor(Color.MAGENTA)

        val myBot =
            DefaultBotBuilder(meepMeep) // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60.0, 60.0, Math.toRadians(180.0), Math.toRadians(180.0), 15.0)
                .followTrajectorySequence { drive: DriveShim ->
                    val builder = drive.trajectorySequenceBuilder(Pose2d(startNode.vector, 0.0))
                    path.remove(startNode)
                    for (it in path) {
                        builder.splineToConstantHeading(it.vector, Math.toRadians(0.0))
                    }
                    builder.build()
                }

        meepMeep
            .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
            .setDarkMode(true)
            .addEntity(myBot)
            .setBackgroundAlpha(0.95f)
            .start()
    }
}
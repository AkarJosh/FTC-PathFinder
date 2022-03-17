package com.cheeseBoy.pathFinder

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.cheeseBoy.pathFinder.algorithms.AStar
import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.DriveShim
import java.awt.Color

class PathFinder {

    val meepMeep = MeepMeep(800, 30)

    val grid = Grid(72.0, 0.5, meepMeep)
    val algo = AStar()

    init {
        System.setProperty("sun.java2d.opengl", "true")
    }

    fun findPath(start: Vector2d, end: Vector2d) {
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
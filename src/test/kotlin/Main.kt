import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.cheeseBoy.pathFinder.Grid
import com.cheeseBoy.pathFinder.Node
import com.cheeseBoy.pathFinder.algorithms.AStar
import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder
import com.noahbres.meepmeep.roadrunner.DriveShim
import java.awt.Color


fun main() {
    System.setProperty("sun.java2d.opengl", "true")
    val meepMeep = MeepMeep(900, 30)

    val grid = Grid(72.0, 0.5, meepMeep)
    val algo = AStar()

    val start = Node(Vector2d(50.0, 50.0), grid, meepMeep)
    val end = Node(Vector2d(-28.0, 32.0), grid, meepMeep)

    val path = algo.calculatePath(start, end, grid) as MutableSet<Node>

    start.setColor(Color.BLUE)
    end.setColor(Color.MAGENTA)

    val myBot =
        DefaultBotBuilder(meepMeep) // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
            .setConstraints(60.0, 60.0, Math.toRadians(180.0), Math.toRadians(180.0), 15.0)
            .followTrajectorySequence { drive: DriveShim ->
                val builder = drive.trajectorySequenceBuilder(Pose2d(start.vector, 0.0))
                path.remove(start)
                for(it in path){
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
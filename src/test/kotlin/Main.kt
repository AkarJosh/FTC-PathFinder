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
    val meepMeep = MeepMeep(700, 30)

    //println("Started Grid Creation")

    val grid = Grid(72.0, 0.2, meepMeep)
    val algo = AStar()

    //println("Created Grid")

    val start = Node(Vector2d(50.0, 50.0), grid, meepMeep)
    val end = Node(Vector2d(-29.0, 32.0), grid, meepMeep)

    /*grid.getNode(Vector2d(55.0, 55.0))!!.switchType()
    println(grid.getNode(Vector2d(55.0, 55.0))!!.type)
    grid.getNode(Vector2d(54.0, 54.0))!!.updateNeighbors()*/

    //println("Started A*")

    val path = algo.calculatePath(start, end, grid) as MutableSet<Node>


    start.setColor(Color.BLUE)
    end.setColor(Color.MAGENTA)

    //println("Finished A*")

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
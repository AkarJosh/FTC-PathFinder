import com.acmerobotics.roadrunner.geometry.Vector2d
import com.cheeseBoy.pathFinder.Grid
import com.cheeseBoy.pathFinder.Node
import com.cheeseBoy.pathFinder.algorithms.AStar
import com.noahbres.meepmeep.MeepMeep
import java.awt.Color

fun main() {
    val meepMeep = MeepMeep(1000, 144)

    //println("Started Grid Creation")

    val grid = Grid(72.0, 1.0, meepMeep)
    val algo = AStar()

    //println("Created Grid")

    val start = Node(Vector2d(50.0, 50.0), grid, meepMeep)
    val end = Node(Vector2d(60.0, 60.0), grid, meepMeep)

    grid.getNode(Vector2d(55.0, 55.0))!!.switchType()
    println(grid.getNode(Vector2d(55.0, 55.0))!!.type)
    grid.getNode(Vector2d(54.0, 54.0))!!.updateNeighbors()

    //println("Started A*")

    val path = algo.calculatePath(start, end, grid)
    path!!.forEach {
        println(it.vector)
    }

    //println("Finished A*")


    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.95f)
        .start()


}

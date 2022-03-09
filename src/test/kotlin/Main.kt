import com.cheeseBoy.pathFinder.Grid
import com.cheeseBoy.pathFinder.algorithms.AStar
import com.noahbres.meepmeep.MeepMeep
import kotlin.concurrent.fixedRateTimer

fun main(){
    val algo = AStar()
    val meepMeep = MeepMeep(700, 5)
    val grid = Grid(72, 1.0)
    meepMeep.addPoint(grid.getNode(500)!!.vector)
    println(grid.getNode(500)!!.vector)
    meepMeep.addPoint(grid.getNode(500 - 72)!!.vector)
    meepMeep.addPoint(grid.getNode(500 + 1)!!.vector)
    meepMeep.addPoint(grid.getNode(500 - 1)!!.vector)
    meepMeep.addPoint(grid.getNode(500 + 72)!!.vector)


    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.95f)
        .start()
}

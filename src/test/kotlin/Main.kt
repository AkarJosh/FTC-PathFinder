import com.acmerobotics.roadrunner.geometry.Vector2d
import com.cheeseBoy.pathFinder.Grid
import com.cheeseBoy.pathFinder.Node
import com.cheeseBoy.pathFinder.algorithms.AStar
import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequenceBuilder

fun main() {
    val meepMeep = MeepMeep(1000, 144)
    val algo = AStar()

    val fieldSize = 72
    val grid = Grid(1.0)

    val start = grid.getNode(Vector2d(50.0, 50.0))
    val end = grid.getNode(Vector2d(67.0, 60.0))
    val path = algo.calculatePath(start!!, end!!, grid)

    path!!.forEach {
        meepMeep.addPoint(it.vector)
    }

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.95f)
        //.addEntity(bot)
        .start()
}

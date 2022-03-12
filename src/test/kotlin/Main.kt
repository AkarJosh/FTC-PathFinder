import com.cheeseBoy.pathFinder.Grid
import com.noahbres.meepmeep.MeepMeep

fun main(){
    val meepMeep = MeepMeep(1000, 144)

    val fieldSize = 72
    val id = 50
    val grid = Grid(fieldSize, 1.0)

    println(grid.getNode(id)!!.vector)
    meepMeep.addPoint(grid.getNode(id)!!.vector) //Center
    meepMeep.addPoint(grid.getNode(id - (fieldSize * 2 + 1))!!.vector) //Bottom
    meepMeep.addPoint(grid.getNode(id + (fieldSize * 2 + 1))!!.vector) //Top
    meepMeep.addPoint(grid.getNode(id - 1)!!.vector) //Left
    meepMeep.addPoint(grid.getNode(id + 1)!!.vector) //Right
    meepMeep.addPoint(grid.getNode(id - (fieldSize * 2))!!.vector) //Bottom Right
    meepMeep.addPoint(grid.getNode(id - (fieldSize * 2 + 2))!!.vector) //Bottom Left
    meepMeep.addPoint(grid.getNode(id + (fieldSize * 2))!!.vector) //Top Left
    meepMeep.addPoint(grid.getNode(id + (fieldSize * 2 + 2))!!.vector) //Top Right

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.95f)
        .start()
}

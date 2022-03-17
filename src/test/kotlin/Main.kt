import com.acmerobotics.roadrunner.geometry.Vector2d
import com.cheeseBoy.pathFinder.PathFinder

fun main() {
    val finder = PathFinder()
    finder.findPath(Vector2d(50.0, 30.0), Vector2d(20.0, 23.0))
}

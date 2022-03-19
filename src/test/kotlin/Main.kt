import com.acmerobotics.roadrunner.geometry.Vector2d
import com.cheeseBoy.pathFinder.Obstacle
import com.cheeseBoy.pathFinder.PathFinder

fun main() {
    val finder = PathFinder(900)
    finder.addObstacle(Obstacle(Vector2d(3.0, 28.0), 10.0, 10.0, finder.grid))
    finder.findPath(Vector2d(50.0, 30.0), Vector2d(-20.0, 23.0))
}

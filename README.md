# FTC-PathFinder

## What is this?
This is a path generator for FTC. It can account for obstacles on the field like shipping hubs, team shipping elements, and walls.

## Installing

To install in a Groovy gradle file.
```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
  
dependencies {
    implementation 'com.github.Cheeseboy8020:FTC-PathFinder:v0.1.0'
}
```

To install in a Kotlin DSL gradle file.
```kotlin
repositories {
    maven { url = uri("https://jitpack.io") }
}
  
dependencies {
    implementation("com.github.Cheeseboy8020:FTC-PathFinder:v0.1.0")
}
```


## Usage

For basic usage: 
```kotlin
fun main() {
    val finder = PathFinder()
    finder.findPath(Vector2d(50.0, 30.0), Vector2d(20.0, 23.0))
}
```
package com.github.samelvhatargh.druid

object Config {
    //camera
    const val cameraScale = 2.5f
    const val windowWidth = 16 * 96
    const val windowHeight = 9 * 96
    const val cameraWidth = 16f * cameraScale
    const val cameraHeight = 9f * cameraScale

    //player input
    const val fullRotateDistance = 9f

    //move
    const val animalSpeed = 2f

    //spawn
    const val spawnRate = 2f
    const val spawnDistance = cameraHeight / 2
    const val spawnDebug = false

    //gameplay
    const val maxMana = 4 * 3 * 3 - 9
}
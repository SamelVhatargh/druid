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
    const val animalSpeed = 1.75f
    const val animalSpeedVariation = .25f

    //spawn
    const val spawnRate = 1.25f
    const val spawnRateVariation = .25f
    const val spawnDistance = cameraHeight / 2
    const val spawnDebug = false

    //gameplay
    const val maxMana = 4 * 3 * 3 - 9
    const val biggerMovingAnimals = true
    const val biggerMovingAnimalsSize = 1.25f
}
package com.github.samelvhatargh.druid.desktop

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.github.samelvhatargh.druid.Config
import com.github.samelvhatargh.druid.DruidGame

fun main() {
    Lwjgl3Application(
        DruidGame(),
        Lwjgl3ApplicationConfiguration().apply {
            setTitle("Druid and His Circle of Animals")
            setMaximized(true)
            setWindowedMode(Config.windowWidth, Config.windowHeight)
        }
    )
}


package com.github.samelvhatargh.druid

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Texture
import com.github.samelvhatargh.druid.components.*
import ktx.ashley.entity
import ktx.ashley.with

/**
 * Contains basic information about world
 */
class World(private val engine: Engine) {

    val collectedAnimals: MutableList<Entity> = mutableListOf()

    fun create() {
        createDruid()
    }

    private fun createDruid() {
        engine.entity {
            with<Druid> {
                angle = 0f
            }
        }
    }

    private fun createAnimal() {
        engine.entity {
            with<Animal>()
            with<CollectedAnimal>()
            with<Position> {

            }
        }
    }
}
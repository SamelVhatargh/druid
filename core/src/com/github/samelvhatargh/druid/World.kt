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
class World(private val engine: Engine, private val img: Texture) {

    val collectedAnimals: MutableList<Entity> = mutableListOf()

    fun create() {
        createDruid()
        createAnimal()
        createAnimal()
        createAnimal()
        createAnimal()
        createAnimal()
        createAnimal()
    }

    private fun createDruid() {
        engine.entity {
            with<Druid> {
                angle = 0f
            }
        }
    }

    private fun createAnimal() {
        val entity = engine.entity {
            with<Animal>()
            with<CollectedAnimal>()
            with<Position> {

            }
        }
        entity.add(Graphics(img))
    }
}
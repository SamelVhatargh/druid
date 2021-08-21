package com.github.samelvhatargh.druid

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.gdx.graphics.Texture
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Graphics
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.entity
import ktx.ashley.with
import ktx.math.vec2

/**
 * Contains basic information about world
 */
class World(private val engine: Engine, private val img: Texture) {

    val collectedAnimals: MutableList<Entity> = mutableListOf()

    fun create() {
        createEntity()
        createEntity()
        createEntity()
        createEntity()
        createEntity()
        createEntity()
    }

    private fun createEntity() {
        val entity = engine.entity {
            with<Animal>()
            with<CollectedAnimal>()
            with<Position> {

            }
        }
        entity.add(Graphics(img))
    }
}
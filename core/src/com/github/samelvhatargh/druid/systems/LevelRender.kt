package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.github.samelvhatargh.druid.Config
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.Animation
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.has
import ktx.graphics.use

class LevelRender(private val batch: SpriteBatch, private val camera: Camera) :
    IteratingSystem(allOf(Animal::class, Position::class).get()) {

    private val shapeRenderer by lazy { ShapeRenderer() }

    override fun update(deltaTime: Float) {
        shapeRenderer.use(ShapeRenderer.ShapeType.Filled, camera.combined) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animal = entity[Animal.mapper]!!
        val animation = entity[Animation.mapper]
        val position = entity[Position.mapper]!!.vec

        shapeRenderer.color = when (animal.level) {
            2 -> Color.BLUE
            3 -> Color.GOLD
            else -> Color.LIGHT_GRAY
        }
        var radius = .6f

        if (animation != null) {
            radius *= animation.size
        }

        if (Config.biggerMovingAnimals && !entity.has(CollectedAnimal.mapper)) {
            radius *= Config.biggerMovingAnimalsSize
        }

        shapeRenderer.circle(position.x, position.y, radius, 20)

    }
}
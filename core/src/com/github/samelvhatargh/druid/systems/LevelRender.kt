package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.github.samelvhatargh.druid.SpriteCache
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.get
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
        val position = entity[Position.mapper]!!.vec

        shapeRenderer.color = when (animal.level) {
            2 -> Color.BLUE
            3 -> Color.GOLD
            else -> Color.LIGHT_GRAY
        }
        shapeRenderer.circle(position.x, position.y, .6f, 20)

    }
}
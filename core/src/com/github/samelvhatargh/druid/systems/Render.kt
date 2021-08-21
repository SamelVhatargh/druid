package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.samelvhatargh.druid.SpriteCache
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.graphics.use

class Render(private val batch: SpriteBatch, private val camera: Camera, private val spriteCache: SpriteCache) :
    IteratingSystem(allOf(Animal::class, Position::class).get()) {

    override fun update(deltaTime: Float) {
        batch.use(camera.combined) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animal = entity[Animal.mapper]!!
        val position = entity[Position.mapper]!!.vec
        val sprite = spriteCache.getSprite(animal.species.sprite)

        sprite.setPosition(position.x - .5f, position.y - .5f)
        sprite.draw(batch)
    }
}
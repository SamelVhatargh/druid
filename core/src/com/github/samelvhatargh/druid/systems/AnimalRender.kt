package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.samelvhatargh.druid.SpriteCache
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.Animation
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.has
import ktx.graphics.use
import ktx.log.logger

class AnimalRender(private val batch: SpriteBatch, private val camera: Camera, private val spriteCache: SpriteCache) :
    IteratingSystem(allOf(Animal::class, Position::class).get()) {

    override fun update(deltaTime: Float) {
        batch.use(camera.combined) {
            super.update(deltaTime)
        }
    }

    companion object {
        val log = logger<AnimalRender>()
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animal = entity[Animal.mapper]!!
        val animation = entity[Animation.mapper]
        val position = entity[Position.mapper]!!.vec
        val sprite = spriteCache.getSprite(animal.species.sprite)

        sprite.setSize(1f, 1f)
        var positionOffset = .5f
        if (animation != null) {
            positionOffset *= animation.size
            sprite.setSize(animation.size, animation.size)
        }

        sprite.setPosition(position.x - positionOffset, position.y - positionOffset)
        sprite.draw(batch)
    }
}
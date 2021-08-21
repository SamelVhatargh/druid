package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.samelvhatargh.druid.components.Graphics
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.graphics.use

class Render(private val batch: SpriteBatch, private val camera: Camera) :
    IteratingSystem(allOf(Graphics::class).get()) {

    override fun update(deltaTime: Float) {
        batch.use(camera.combined) {
            super.update(deltaTime)
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val graphics = entity[Graphics.mapper]!!
        if (graphics.texture !== null) {
            batch.draw(graphics.texture, -.5f, -.5f, 1f, 1f)
        }
    }
}
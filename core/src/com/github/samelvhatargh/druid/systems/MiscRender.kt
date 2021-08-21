package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.samelvhatargh.druid.SpriteCache
import com.github.samelvhatargh.druid.getDruid
import ktx.graphics.use

class MiscRender(private val batch: SpriteBatch, private val camera: Camera, private val spriteCache: SpriteCache) :
    EntitySystem() {

    override fun update(deltaTime: Float) {
        val druid = engine.getDruid()
        batch.use(camera.combined) {
            val sprite = spriteCache.getSprite("dude")

            sprite.setPosition(-.5f, -.5f)
            sprite.setOriginCenter()
            sprite.rotation = druid.angle
            sprite.draw(batch)
        }
    }
}
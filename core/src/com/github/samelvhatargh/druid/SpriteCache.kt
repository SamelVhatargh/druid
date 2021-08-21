package com.github.samelvhatargh.druid

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import ktx.log.logger

/**
 * Ensures that there is only on instance of each [Sprite]
 */
class SpriteCache(private val atlas: TextureAtlas) {

    companion object {
        val log = logger<SpriteCache>()
    }

    private val cache = mutableMapOf<String, Sprite>()

    fun getSprite(name: String, index: Int = -1): Sprite {
        val key = "${name}_$index"
        var sprite = cache[key]
        if (sprite == null) {
            val region = atlas.findRegion(name, index)
            require(region != null) { "Cant load sprite $key" }

            sprite = Sprite(region).apply {
                setSize(1f, 1f)
            }
            cache[key] = sprite

            log.debug { "sprite $name created" }
        }
        return sprite
    }

//    fun getSprite(graphics: Graphics) : Sprite = getSprite(graphics.spriteName)
}
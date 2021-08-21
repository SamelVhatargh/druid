package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.github.samelvhatargh.druid.Config.cameraWidth
import com.github.samelvhatargh.druid.SpriteCache
import com.github.samelvhatargh.druid.getDruid
import ktx.graphics.use

enum class Tile(val sprite: String) {
    BIG_FOREST("big_forest"),
    BIG_ROCK("big_rock"),
    BIG_SPRUCE("big_spruce"),
    DIRT_ROCK("dirt_rock"),
    GRASS("grass"),
    SMALL_FOREST("small_forest"),
    SMALL_ROCK("small_rock"),
    SMALL_SPRUCE("small_spruce"),
}

class MiscRender(private val batch: SpriteBatch, private val camera: Camera, private val spriteCache: SpriteCache) :
    EntitySystem() {

    val width = 10
    val height = 10

    val map: Array<Array<Tile>>

    val runes: Array<Int>

    init {
        map = Array(width) { Array(height) { Tile.SMALL_FOREST } }

        val probabilities = listOf(
            Tile.GRASS,
            Tile.GRASS,
            Tile.GRASS,
            Tile.GRASS,
            Tile.GRASS,
            Tile.GRASS,
            Tile.GRASS,
            Tile.GRASS,
            Tile.SMALL_FOREST,
            Tile.SMALL_FOREST,
            Tile.SMALL_FOREST,
            Tile.SMALL_FOREST,
            Tile.SMALL_FOREST,
            Tile.BIG_FOREST,
            Tile.BIG_FOREST,
            Tile.BIG_FOREST,
            Tile.BIG_FOREST,
            Tile.BIG_FOREST,
            Tile.SMALL_SPRUCE,
            Tile.SMALL_SPRUCE,
            Tile.SMALL_SPRUCE,
            Tile.SMALL_SPRUCE,
            Tile.SMALL_SPRUCE,
            Tile.BIG_SPRUCE,
            Tile.BIG_SPRUCE,
            Tile.BIG_SPRUCE,
            Tile.BIG_SPRUCE,
            Tile.BIG_SPRUCE,
            Tile.SMALL_ROCK,
            Tile.BIG_ROCK,
            Tile.DIRT_ROCK,
        )

        for (i in map.indices) {
            for (j in map[i].indices) {
                map[i][j] = probabilities.random()
            }
        }

        runes = Array(50) { 1 }
        for (i in runes.indices) {
            runes[i] = (1..35).random()
        }
    }

    override fun update(deltaTime: Float) {
        val druid = engine.getDruid()
        batch.use() {
            for (i in map.indices) {
                for (j in map[i].indices) {
                    val tile = spriteCache.getSprite(map[i][j].sprite)
                    val scale = 5f
                    val dx = if (j % 2 == 0) 0f else scale / 2
                    tile.setPosition((i - width / 2) * scale + dx, (j - height / 2) * (scale * 24 / 28f))
                    tile.setSize(1f * scale, 28 / 24f * scale)
                    tile.draw(batch)
                }
            }
        }
        batch.use(camera.combined) {
            var manaDrawn = 0
            for (i in 1..druid.maxMana) {
                val rune = if (manaDrawn < druid.mana) spriteCache.getSprite("runeGrey_slabOutline", runes[i])
                    else spriteCache.getSprite("runeBlack_slabOutline", 35)
                rune.setSize(56/93f, 1f)
                rune.setPosition((i - cameraWidth / 2) * .75f - 5.5f, 10f)
                rune.draw(batch)
                manaDrawn++
            }

            val sprite = spriteCache.getSprite("dude")

            sprite.setPosition(-.5f, -.5f)
            sprite.setOriginCenter()
            sprite.rotation = druid.angle
            sprite.draw(batch)
        }
    }
}
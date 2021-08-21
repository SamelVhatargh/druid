package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils.random
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.Graphics
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.entity
import ktx.ashley.with
import ktx.math.vec2

const val spawnRate = 1f
const val spawnDistance = 5f

class Spawner(private val img: Texture) : EntitySystem() {

    var lastSpawn = 0f

    override fun update(deltaTime: Float) {
        lastSpawn += deltaTime

        if (lastSpawn >= spawnRate) {
            spawn()
            lastSpawn -= spawnRate
        }
    }

    private fun spawn() {
        val animal = engine.entity {
            with<Animal>()
            with<Position> {
                vec = vec2(0f, spawnDistance).apply {
                    rotateDeg(random(0f, 360f))
                }
            }
        }
        animal.add(Graphics(img))
    }
}
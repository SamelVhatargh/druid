package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.math.MathUtils.random
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.Position
import com.github.samelvhatargh.druid.components.Species
import ktx.ashley.entity
import ktx.ashley.with
import ktx.math.vec2

const val spawnRate = 1f
const val spawnDistance = 5f

class Spawner() : EntitySystem() {

    var lastSpawn = 0f

    override fun update(deltaTime: Float) {
        lastSpawn += deltaTime

        if (lastSpawn >= spawnRate) {
            spawn()
            lastSpawn -= spawnRate
        }
    }

    private fun spawn() {
        engine.entity {
            with<Animal> {
                species = Species.values().random()
            }
            with<Position> {
                vec = vec2(0f, spawnDistance).apply {
                    rotateDeg(random(0f, 360f))
                }
            }
        }
    }
}
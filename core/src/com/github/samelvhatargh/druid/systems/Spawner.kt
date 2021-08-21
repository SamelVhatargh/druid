package com.github.samelvhatargh.druid.systems

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.MathUtils.random
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Position
import com.github.samelvhatargh.druid.components.Species
import ktx.ashley.allOf
import ktx.ashley.entity
import ktx.ashley.get
import ktx.ashley.with
import ktx.log.logger
import ktx.math.vec2
import ktx.math.vec3

const val spawnRate = 1f
const val spawnDistance = 5f

class Spawner(
    inputMultiplexer: InputMultiplexer,
    private val camera: Camera,
    private val debug: Boolean = false
) : InputSystem(inputMultiplexer) {

    var lastSpawn = 0f

    override fun update(deltaTime: Float) {
        if (debug) {
            return
        }
        lastSpawn += deltaTime

        if (lastSpawn >= spawnRate) {
            spawn()
            lastSpawn -= spawnRate
        }
    }

    companion object {
        val log = logger<Spawner>()
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (button == Input.Buttons.LEFT) {
            val mouse = camera.unproject(vec3(screenX.toFloat(), screenY.toFloat(), 0f))
            val angle = vec2(mouse.x, mouse.y).sub(vec2(0f, 0f)).angleDeg() - 90f
            spawn(angle)
        } else {
            engine.getEntitiesFor(allOf(Animal::class, CollectedAnimal::class, Position::class).get()).forEach {
                log.debug { "id: ${it[CollectedAnimal.mapper]!!.id}; order: ${it[CollectedAnimal.mapper]!!.order};angle: ${it[Position.mapper]!!.vec.angleDeg()}; type: ${it[Animal.mapper]!!.species.sprite}" }
            }
        }

        return true
    }

    private fun spawn(degrees: Float = random(0f, 360f)) {
        engine.entity {
            with<Animal> {
                species = Species.values().random()
            }
            with<Position> {
                vec = vec2(0f, spawnDistance).apply {
                    rotateDeg(degrees)
                }
            }
        }
    }
}
package com.github.samelvhatargh.druid.systems

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.math.MathUtils.random
import com.github.samelvhatargh.druid.Config
import com.github.samelvhatargh.druid.components.*
import com.github.samelvhatargh.druid.getCollectedAnimals
import com.github.samelvhatargh.druid.getDruid
import ktx.ashley.*
import ktx.log.logger
import ktx.math.random
import ktx.math.vec2
import ktx.math.vec3

class Spawner(
    inputMultiplexer: InputMultiplexer,
    private val camera: Camera,
    private val debug: Boolean = false
) : InputSystem(inputMultiplexer) {

    var lastSpawn = Config.spawnRate
    var nextSpan = Config.spawnRate

    override fun update(deltaTime: Float) {
        if (debug) {
            return
        }
        lastSpawn += deltaTime

        if (lastSpawn >= nextSpan) {
            spawn()
            lastSpawn -= Config.spawnRate
            nextSpan = ((Config.spawnRate - Config.spawnRateVariation)
                    ..(Config.spawnRate + Config.spawnRateVariation)).random()
        }
    }

    companion object {
        val log = logger<Spawner>()
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        if (!debug) {
            return false
        }
        if (button == Input.Buttons.LEFT) {
            val mouse = camera.unproject(vec3(screenX.toFloat(), screenY.toFloat(), 0f))
            val angle = vec2(mouse.x, mouse.y).sub(vec2(0f, 0f)).angleDeg() - 90f
            spawn(angle)
        } else {
            engine.getCollectedAnimals().forEach {
                log.debug { "id: ${it[CollectedAnimal.mapper]!!.id}; order: ${it[CollectedAnimal.mapper]!!.order};angle: ${it[Position.mapper]!!.vec.angleDeg()}; type: ${it[Animal.mapper]!!.species.sprite}" }
            }
        }

        return true
    }

    private fun spawn(degrees: Float = random(0f, 360f)) {
        val isPredator = engine.getDruid().animalsCount > 10  && (1..10).random() == 1
        var speciesType = Species.BEAR
        if (!isPredator) {
            val speciesArr = Species.values().toMutableList()
            speciesArr.remove(Species.BEAR)
            speciesType = speciesArr.random()
        }


        val entity = engine.entity {
            with<Animal> {
                species = speciesType
                speed = ((Config.animalSpeed - Config.animalSpeedVariation)
                        ..(Config.animalSpeed + Config.animalSpeedVariation)).random()
                if (isPredator) {
                    level = 666
                }
            }
            with<Position> {
                vec = vec2(0f, Config.spawnDistance).apply {
                    rotateDeg(degrees)
                }
            }
        }

        if (isPredator) {
            engine.getSystem<SoundEffects>().play(Sound.BEAR)
            entity += Predator()
        }
    }
}
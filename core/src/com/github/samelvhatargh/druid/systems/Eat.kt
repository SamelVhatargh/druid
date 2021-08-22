package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Position
import com.github.samelvhatargh.druid.components.Predator
import com.github.samelvhatargh.druid.getCollectedAnimals
import com.github.samelvhatargh.druid.getDruid
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.ashley.getSystem
import ktx.log.logger

class Eat : IteratingSystem(allOf(Animal::class, Position::class, Predator::class).get()) {

    private val eatDistance = .5f

    companion object {
        val log = logger<Eat>()
    }

    override fun update(deltaTime: Float) {
        log.debug { "start" }
        super.update(deltaTime)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animals = engine.getCollectedAnimals().sortedBy { it[CollectedAnimal.mapper] }.toMutableList()
        val predatorPosition = entity[Position.mapper]!!

//        if (predatorPosition.vec.x <= 1f && predatorPosition.vec.y <= 1f) {
//            engine.removeEntity(entity)
//            return
//        }

        animals.forEach {
            val animalPosition = it[Position.mapper]!!

            if (animalPosition.vec.dst2(predatorPosition.vec) <= eatDistance) {
                log.debug { "eat" }
                engine.removeEntity(entity)
                engine.removeEntity(it)
                engine.getDruid().mana += 1
                engine.getDruid().animalsCount -= 1
                engine.getDruid().radius -= .1f
                animals.remove(it)

                for (j in animals.indices) {
                    animals[j][CollectedAnimal.mapper]!!.id = j
                    animals[j][CollectedAnimal.mapper]!!.order = j
                }

                engine.getSystem<SoundEffects>().play(Sound.EAT)

                return
            }
        }
    }

}
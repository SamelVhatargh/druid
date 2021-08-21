package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntitySystem
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.getCollectedAnimals
import com.github.samelvhatargh.druid.getDruid
import ktx.ashley.get
import ktx.log.logger

class LevelUp : EntitySystem() {

    companion object {
        val log = logger<LevelUp>()
    }

    override fun update(deltaTime: Float) {
        val animals = engine.getCollectedAnimals().sortedBy { entity -> entity[CollectedAnimal.mapper] }.toMutableList()

        if (animals.size <= 2) {
            return
        }

        var reachedEnd = false

        while (!reachedEnd) {
            for (i in animals.indices) {
                val previousEntity = try {
                    animals[i - 1]
                } catch (e: IndexOutOfBoundsException) {
                    animals.last()
                }
                val currentEntity = animals[i]
                val nextEntity = try {
                    animals[i + 1]
                } catch (e: Exception) {
                    animals.first()
                }

                val previousAnimal = previousEntity[Animal.mapper]!!
                val currentAnimal = currentEntity[Animal.mapper]!!
                val nextAnimal = nextEntity[Animal.mapper]!!


                if (previousAnimal.species == currentAnimal.species && currentAnimal.species == nextAnimal.species
                    && previousAnimal.level == currentAnimal.level && currentAnimal.level == nextAnimal.level) {
                    currentAnimal.level++
                    engine.removeEntity(previousEntity)
                    engine.removeEntity(nextEntity)
                    engine.getDruid().animalsCount -= 2
                    engine.getDruid().radius -= .2f
                    animals.remove(previousEntity)
                    animals.remove(nextEntity)

                    for (j in animals.indices) {
                        animals[j][CollectedAnimal.mapper]!!.id = j
                        animals[j][CollectedAnimal.mapper]!!.order = j
                    }
                    break
                }

                if (i == animals.size - 1) {
                    reachedEnd = true
                }
            }
        }
    }
}
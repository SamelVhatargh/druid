package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Druid
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.exclude
import ktx.ashley.get

class Collect : IteratingSystem(allOf(Animal::class, Position::class).exclude(CollectedAnimal::class).get()) {

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val position = entity[Position.mapper]!!
        val druid = engine.getEntitiesFor(allOf(Druid::class).get()).first()[Druid.mapper]!!

        if (position.vec.len() <= druid.radius) {

            val collectedAnimals =
                engine.getEntitiesFor(allOf(Animal::class, CollectedAnimal::class, Position::class).get())
            val newCollectedAnimals = mutableListOf<Entity>()

            val collectedAnimal = CollectedAnimal().apply {
                id = druid.animalsCount
                order = id
            }

            entity.add(collectedAnimal)

            var newItemAdded = false
            collectedAnimals.sortedBy { it -> it[Position.mapper]!!.vec.angleDeg() }.forEach {
                if (it[Position.mapper]!!.vec.angleDeg() < position.vec.angleDeg()) {
                    newCollectedAnimals.add(it)
                } else {
                    if (!newItemAdded) {
                        newCollectedAnimals.add(entity)
                        newItemAdded = true
                    }
                    newCollectedAnimals.add(it)
                }
            }

            if (!newItemAdded) {
                newCollectedAnimals.add(entity)
                newItemAdded = true
            }

            while (newCollectedAnimals[0][CollectedAnimal.mapper]!!.id != 0) {
                val temp = newCollectedAnimals.removeAt(0)
                newCollectedAnimals.add(temp)
            }

            var i = 0
            newCollectedAnimals.forEach {
                it[CollectedAnimal.mapper]!!.order = i
                i++
            }

            druid.animalsCount++
            druid.radius += .1f
        }
    }
}
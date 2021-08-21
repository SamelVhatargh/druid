package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Druid
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.get

class CollectedAnimalsPositionCalculator :
    SortedIteratingSystem(
        allOf(Animal::class, CollectedAnimal::class, Position::class).get(),
        compareBy { entity -> entity[CollectedAnimal.mapper] }
    ) {


    override fun update(deltaTime: Float) {
        forceSort()
        super.update(deltaTime)
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val druid = engine.getEntitiesFor(allOf(Druid::class).get()).first()[Druid.mapper]!!
        val position = entity[Position.mapper]!!
        val collectedAnimal = entity[CollectedAnimal.mapper]!!

        position.vec.set(0f, druid.radius)
        position.vec.rotateDeg(collectedAnimal.order * (360 / druid.animalsCount).toFloat() + druid.angle)
    }
}
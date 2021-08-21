package com.github.samelvhatargh.druid

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.utils.ImmutableArray
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.CollectedAnimal
import com.github.samelvhatargh.druid.components.Druid
import com.github.samelvhatargh.druid.components.Position
import ktx.ashley.allOf
import ktx.ashley.get

fun Engine.getCollectedAnimals(): ImmutableArray<Entity> {
    return getEntitiesFor(allOf(Animal::class, CollectedAnimal::class, Position::class).get())
}

fun Engine.getDruid(): Druid {
    return getEntitiesFor(allOf(Druid::class).get()).first()[Druid.mapper]!!
}
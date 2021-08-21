package com.github.samelvhatargh.druid.systems

import com.badlogic.ashley.core.EntitySystem
import com.github.samelvhatargh.druid.DruidGame
import com.github.samelvhatargh.druid.components.Animal
import com.github.samelvhatargh.druid.components.Species
import com.github.samelvhatargh.druid.getCollectedAnimals
import com.github.samelvhatargh.druid.getDruid
import com.github.samelvhatargh.druid.screens.LoseScreen
import com.github.samelvhatargh.druid.screens.WinScreen
import ktx.ashley.get

class EndGame(private val game: DruidGame) : EntitySystem() {

    override fun update(deltaTime: Float) {
        if (engine.getDruid().mana <= 0) {
            game.setScreen<LoseScreen>()
            return
        }

        val collectedSpecies = mutableListOf<Species>()
        engine.getCollectedAnimals().forEach {
            val animal = it[Animal.mapper]!!

            if (animal.level == 3) {
                if (!collectedSpecies.contains(animal.species)) {
                    collectedSpecies.add(animal.species)
                }
            }
        }

        if (collectedSpecies.size >= 4) {
            game.setScreen<WinScreen>()
            return
        }
    }
}
package org.jetbrains.kotlin.course.tamagotchi.game

import com.fasterxml.jackson.annotation.Nulls
import org.jetbrains.kotlin.course.tamagotchi.models.Command
import org.jetbrains.kotlin.course.tamagotchi.models.Mode
import org.springframework.stereotype.Service

@Service
class GameService {
    companion object {
        private const val MAX_CAPACITY = 16
    }
    val commands: ArrayDeque<Command> = ArrayDeque<Command>()
    fun addCommand(command: Command): Boolean = when(MAX_CAPACITY > commands.size){
        true-> commands.add(command)
        false->false
    }

    fun getCommand(mode: Mode): Command? = when(mode){
        Mode.Stack->commands.removeLastOrNull()
        Mode.Queue->commands.removeFirstOrNull()
        else->null
    }
}

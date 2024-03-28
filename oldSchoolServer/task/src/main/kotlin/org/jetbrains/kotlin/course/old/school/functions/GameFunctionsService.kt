package org.jetbrains.kotlin.course.old.school.functions

import org.jetbrains.kotlin.course.old.school.photo.Accessory
import org.jetbrains.kotlin.course.old.school.photo.Color
import org.jetbrains.kotlin.course.old.school.photo.PhotoCharacter
import org.springframework.stereotype.Service

@Service
class GameFunctionsService {
    fun getAllPossibleColors(): List<String> = Color.entries.map {it.name.lowercase()}
    private fun String.toColor(): Color = Color.valueOf(this.replaceFirstChar { it.titlecase() })

    private fun Iterable<String>.toPhotoCharacters(): List<PhotoCharacter> = this.map{PhotoCharacter.valueOf(it.replaceFirstChar{it.titlecase()})}

    fun Iterable<String>.findPhoto(colorStr: String): PhotoCharacter? = with(colorStr.toColor()){toPhotoCharacters().find{it.backgroundColor == this}}

    fun Iterable<String>.groupPhotosByColor(): List<PhotoCharacter> = this.toPhotoCharacters().groupBy{it.backgroundColor}.flatMap { it.value }

    fun Iterable<String>.groupPhotosByHairAndHat(): List<PhotoCharacter> = this.toPhotoCharacters().groupBy{it.hairType}.map{it.value.groupBy{ it.accessories?.contains(Accessory.Hat) == true}}.flatMap{it.values}.flatten()
}

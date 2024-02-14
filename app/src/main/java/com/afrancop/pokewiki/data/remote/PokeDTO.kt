package com.afrancop.pokewiki.data.remote

import com.afrancop.pokewiki.data.local.Poke
import com.google.gson.annotations.SerializedName

data class PokeDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: List<Types>,
    @SerializedName("abilities")
    val abilities: List<Abilities>,
    @SerializedName("sprites")
    val sprite: Sprite,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int
)

data class Abilities(
    @SerializedName("ability")
    val ability: Ability,
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("is_hidden")
    val isHidden: Boolean
)
data class Ability(
    @SerializedName("name")
    val name: String,
)
data class Types(
    @SerializedName("type")
    val type: Type,
    @SerializedName("slot")
    val slot: Int
)

data class Type(
    @SerializedName("name")
    val name: String
)
data class Sprite(
    @SerializedName("front_default")
    val frontDefault: String
)
fun PokeDTO.toLocalEntity() = Poke(
    id = id,
    name = name,
    type1 = types.getOrElse(0) { Types(Type(""), 0) }.type.name,
    type2 = types.getOrElse(1) { Types(Type(""), 0) }.type.name,
    sprite = sprite.frontDefault,
    skill1 = abilities.getOrElse(0) { Abilities(Ability(""), 0,false) }.ability.name,
    skill2 = abilities.getOrElse(1) { Abilities(Ability(""), 0,false) }.ability.name,
    skill3 = abilities.getOrElse(2) { Abilities(Ability(""), 0,false) }.ability.name,
    skill4 = abilities.getOrElse(3) { Abilities(Ability(""), 0,false) }.ability.name,
    height = height,
    weight = weight
)
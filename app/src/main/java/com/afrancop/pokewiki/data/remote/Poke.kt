package com.afrancop.pokewiki.data.remote

import android.media.Image
import com.afrancop.pokewiki.data.local.Poke
import com.google.gson.annotations.SerializedName

data class PokeDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("types")
    val types: Types,
    @SerializedName("abilities")
    val abilities: Abilities,
    @SerializedName("sprites")
    val sprite: Sprite,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int
)

data class Abilities(
    @SerializedName("ability")
    val ability: List<Ability>,
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("is_hidden")
    val isHidden: Boolean
)
data class Ability(
    @SerializedName("name")
    val name: String
)
data class Types(
    @SerializedName("type")
    val type: List<Type>,
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
    id = null,
    name = name,
    type1 = types.type.getOrNull(0)?.name.orEmpty(),
    type2 = types.type.getOrNull(1)?.name.orEmpty(),
    type3 = types.type.getOrNull(2)?.name.orEmpty(),
    sprite = sprite.frontDefault,
    skill1 = abilities.ability.getOrNull(0)?.name.orEmpty(),
    skill2 = abilities.ability.getOrNull(1)?.name.orEmpty(),
    skill3 = abilities.ability.getOrNull(2)?.name.orEmpty(),
    hideSkill = abilities.isHidden,
    height = height,
    weight = weight
)
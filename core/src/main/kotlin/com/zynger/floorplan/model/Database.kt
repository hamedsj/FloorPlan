package com.zynger.floorplan.model

import kotlinx.serialization.Serializable

@Serializable
data class Database(
    val version: Int,
    val identityHash: String,
    val entities: List<Entity>,
    val views: List<View> = emptyList(),
    val setupQueries: List<String>
)
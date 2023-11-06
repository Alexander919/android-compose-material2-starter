package com.hfad.introtocompose.model

import androidx.room.TypeConverter
import java.util.UUID

class UUIDConverter {
    @TypeConverter
    fun fromUUID(uuid: UUID): String {
        return uuid.toString()
    }
    @TypeConverter
    fun toUUID(strUUID: String?): UUID? {
        return UUID.fromString(strUUID)
    }
}
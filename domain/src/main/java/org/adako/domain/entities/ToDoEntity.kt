package org.adako.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDo")
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "isCompleted") val isCompleted: Boolean?,
)
package com.example.memoboard.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionDao {
    @Query("SELECT * FROM sections ORDER BY lastModifiedDate")
    fun getAllSections(): Flow<List<Section>>

    @Query("SELECT * FROM sections WHERE id = :id")
    fun getSectionById(id: Int): Flow<Section>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSection(section: Section)

    @Update
    suspend fun updateSection(section: Section)

    @Delete
    suspend fun deleteSection(section: Section)
}

package com.example.memoboard.data

import kotlinx.coroutines.flow.Flow

// Contains datasource
interface SectionRepository {
    fun getAllSections(): Flow<List<Section>>

    fun getSectionById(id: Int): Flow<Section?>

    suspend fun insertSection(section: Section)

    suspend fun updateSection(section: Section)

    suspend fun deleteSection(section: Section)
}

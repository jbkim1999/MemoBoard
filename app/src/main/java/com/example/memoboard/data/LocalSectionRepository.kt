package com.example.memoboard.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LocalSectionRepository(
    private val localSectionProvider: LocalSectionProvider
) : SectionRepository {

    override fun getAllSections(): Flow<List<Memo>> {
        return flowOf(localSectionProvider.getAllSections())
    }

    override fun getSectionById(id: Int): Flow<Memo?> {
        return flowOf(localSectionProvider.getAllSections().find { it.id == id })
    }

    override suspend fun insertSection(section: Memo) {
        localSectionProvider.getAllSections().add(section)
    }

    override suspend fun updateSection(section: Memo) {
        val id = section.id
        val index = localSectionProvider.getAllSections().indexOfFirst { it.id == id }
        if (index != -1) {
            localSectionProvider.getAllSections()[index] = section
        }
    }

    override suspend fun deleteSection(section: Memo) {
        val id = section.id
        val index = localSectionProvider.getAllSections().indexOfFirst { it.id == id }
        if (index != -1) {
            localSectionProvider.getAllSections().removeAt(index)
        }
    }
}

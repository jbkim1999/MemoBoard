package com.example.memoboard.data

import kotlinx.coroutines.flow.Flow

class DBSectionRepository(private val sectionDao: SectionDao) : SectionRepository {
    override fun getAllSections(): Flow<List<Memo>> = sectionDao.getAllSections()

    override fun getSectionById(id: Int): Flow<Memo> = sectionDao.getSectionById(id)

    override suspend fun insertSection(section: Memo) = sectionDao.insertSection(section)

    override suspend fun updateSection(section: Memo) = sectionDao.updateSection(section)

    override suspend fun deleteSection(section: Memo) = sectionDao.deleteSection(section)
}

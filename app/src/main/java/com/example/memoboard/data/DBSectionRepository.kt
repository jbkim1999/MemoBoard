package com.example.memoboard.data

import kotlinx.coroutines.flow.Flow

class DBSectionRepository(private val sectionDao: SectionDao) : SectionRepository {
    override fun getAllSections(): Flow<List<Section>> = sectionDao.getAllSections()

    override fun getSectionById(id: Int): Flow<Section> = sectionDao.getSectionById(id)

    override suspend fun insertSection(section: Section) = sectionDao.insertSection(section)

    override suspend fun updateSection(section: Section) = sectionDao.updateSection(section)

    override suspend fun deleteSection(section: Section) = sectionDao.deleteSection(section)
}

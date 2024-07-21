package com.example.memoboard.data

import kotlinx.coroutines.flow.Flow

// Contains datasource
interface MemoRepository {
    fun getAllMemos(): Flow<List<Memo>>

    fun getMemoById(id: Int): Flow<Memo?>

    suspend fun insertMemo(section: Memo)

    suspend fun updateMemo(section: Memo)

    suspend fun deleteMemo(section: Memo)
}

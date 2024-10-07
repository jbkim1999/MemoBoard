package com.jbjbjb.memoboard.data

import kotlinx.coroutines.flow.Flow

// Contains datasource
interface MemoRepository {
    fun getAllMemos(): Flow<List<Memo>>

    fun getMemoById(id: Int): Flow<Memo?>

    suspend fun insertMemo(memo: Memo)

    suspend fun updateMemo(memo: Memo)

    suspend fun deleteMemo(memo: Memo)
}

package com.jbjbjb.memoboard.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class LocalMemoRepository(
    private val localMemoProvider: LocalMemoProvider
) : MemoRepository {

    override fun getAllMemos(): Flow<List<Memo>> {
        return flowOf(localMemoProvider.getAllMemos())
    }

    override fun getMemoById(id: Int): Flow<Memo?> {
        return flowOf(localMemoProvider.getAllMemos().find { it.id == id })
    }

    override suspend fun insertMemo(memo: Memo) {
        localMemoProvider.getAllMemos().add(memo)
    }

    override suspend fun updateMemo(memo: Memo) {
        val id = memo.id
        val index = localMemoProvider.getAllMemos().indexOfFirst { it.id == id }
        if (index != -1) {
            localMemoProvider.getAllMemos()[index] = memo
        }
    }

    override suspend fun deleteMemo(memo: Memo) {
        val id = memo.id
        val index = localMemoProvider.getAllMemos().indexOfFirst { it.id == id }
        if (index != -1) {
            localMemoProvider.getAllMemos().removeAt(index)
        }
    }
}

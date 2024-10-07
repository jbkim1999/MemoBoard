package com.jbjbjb.memoboard.data

import kotlinx.coroutines.flow.Flow

class DBMemoRepository(private val memoDao: MemoDao) : MemoRepository {
    override fun getAllMemos(): Flow<List<Memo>> = memoDao.getAllMemos()

    override fun getMemoById(id: Int): Flow<Memo?> = memoDao.getMemoById(id)

    override suspend fun insertMemo(memo: Memo) = memoDao.insertMemo(memo)

    override suspend fun updateMemo(memo: Memo) = memoDao.updateMemo(memo)

    override suspend fun deleteMemo(memo: Memo) = memoDao.deleteMemo(memo)
}

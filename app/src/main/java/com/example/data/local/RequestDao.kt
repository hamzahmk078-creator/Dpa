package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RequestDao {
    @Query("SELECT * FROM dpa_requests ORDER BY id DESC")
    fun getAllRequests(): Flow<List<RequestEntity>>

    @Query("SELECT * FROM dpa_requests WHERE trackingNumber = :trackingNumber LIMIT 1")
    suspend fun getRequestByTracking(trackingNumber: String): RequestEntity?

    @Query("SELECT * FROM dpa_requests WHERE nationalId = :nationalId ORDER BY id DESC")
    fun getRequestsByNationalId(nationalId: String): Flow<List<RequestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(request: RequestEntity): Long

    @Update
    suspend fun updateRequest(request: RequestEntity)

    @Delete
    suspend fun deleteRequest(request: RequestEntity)

    @Query("DELETE FROM dpa_requests WHERE id = :id")
    suspend fun deleteRequestById(id: Long)

    @Query("SELECT COUNT(*) FROM dpa_requests")
    suspend fun getCount(): Int
}

package com.example.adbuy.db

import androidx.room.Dao

@Dao
interface AdbuyDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertRun(run: Run)
//
//    @Delete
//    suspend fun deleteRun(run: Run)
//
//    @Query("SELECT * FROM adbuy_table ORDER BY timestamp DESC")
//    fun getAllRunsSortedByDate(): LiveData<List<Run>>
//
//    @Query("SELECT * FROM adbuy_table ORDER BY timeInMillis DESC")
//    fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>>
//
//    @Query("SELECT * FROM adbuy_table ORDER BY caloriesBurned DESC")
//    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>>
//
//    @Query("SELECT * FROM adbuy_table ORDER BY avgSpeedInKMH DESC")
//    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>
//
//    @Query("SELECT * FROM adbuy_table ORDER BY distanceInMeters DESC")
//    fun getAllRunsSortedByDistance(): LiveData<List<Run>>
//
//    @Query("SELECT SUM(timeInMillis) FROM adbuy_table")
//    fun getTotalTimeInMillis(): LiveData<Long>
//
//    @Query("SELECT SUM(caloriesBurned) FROM adbuy_table")
//    fun getTotalCaloriesBurned(): LiveData<Int>
//
//    @Query("SELECT SUM(distanceInMeters) FROM adbuy_table")
//    fun getTotalDistance(): LiveData<Int>
//
//    @Query("SELECT AVG(avgSpeedInKMH) FROM adbuy_table")
//    fun getTotalAvgSpeed(): LiveData<Float>
}
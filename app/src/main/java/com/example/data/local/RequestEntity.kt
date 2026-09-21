package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dpa_requests")
data class RequestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val trackingNumber: String,
    val serviceType: String, // "MAKRUMA", "BUILDING_PERMIT", "RESIDENCY_CERT", "MAINTENANCE"
    val serviceTitleAr: String,
    val serviceTitleEn: String,
    val applicantName: String,
    val nationalId: String,
    val campName: String,
    val phoneNumber: String,
    val status: String, // "PENDING", "IN_REVIEW", "IN_PROGRESS", "APPROVED"
    val submissionDate: String,
    val details: String,
    val estimatedDays: Int = 3
)

package com.example.data.local

import kotlinx.coroutines.flow.Flow

class RequestRepository(private val requestDao: RequestDao) {

    val allRequests: Flow<List<RequestEntity>> = requestDao.getAllRequests()

    suspend fun getRequestByTracking(trackingNumber: String): RequestEntity? {
        return requestDao.getRequestByTracking(trackingNumber.trim())
    }

    suspend fun insert(request: RequestEntity): Long {
        return requestDao.insertRequest(request)
    }

    suspend fun deleteById(id: Long) {
        requestDao.deleteRequestById(id)
    }

    suspend fun seedInitialDataIfEmpty() {
        if (requestDao.getCount() == 0) {
            val samples = listOf(
                RequestEntity(
                    trackingNumber = "DPA-MK-2026-8491",
                    serviceType = "MAKRUMA",
                    serviceTitleAr = "المكرمة الملكية السامية لأبناء المخيمات",
                    serviceTitleEn = "Royal Makruma Admission for Camp Students",
                    applicantName = "حمزة محمد خليل",
                    nationalId = "9982014523",
                    campName = "مخيم البقعة",
                    phoneNumber = "0788123456",
                    status = "IN_PROGRESS",
                    submissionDate = "2026-09-18",
                    details = "معدل التوجيهي: 88.4% - الفرع العلمي - مدرسة ذكور البقعة الإعدادية للأونروا",
                    estimatedDays = 4
                ),
                RequestEntity(
                    trackingNumber = "DPA-BP-2026-3021",
                    serviceType = "BUILDING_PERMIT",
                    serviceTitleAr = "ترخيص بناء طابق سكني إضافي",
                    serviceTitleEn = "Additional Floor Construction Permit",
                    applicantName = "عمر أحمد عبد الرحمن",
                    nationalId = "9851029384",
                    campName = "مخيم الوحدات",
                    phoneNumber = "0795543210",
                    status = "APPROVED",
                    submissionDate = "2026-09-10",
                    details = "رخصة طابق ثانٍ - بلوك 4 - وحدة 18 - تمت الموافقة بعد الكشف الهندسي الميداني",
                    estimatedDays = 0
                ),
                RequestEntity(
                    trackingNumber = "DPA-RC-2026-1944",
                    serviceType = "RESIDENCY_CERT",
                    serviceTitleAr = "شهادة إثبات سكن وإقامة بالمخيم",
                    serviceTitleEn = "Proof of Camp Residency Certificate",
                    applicantName = "سارة إبراهيم القاسم",
                    nationalId = "2001948572",
                    campName = "مخيم الحسين",
                    phoneNumber = "0770987654",
                    status = "APPROVED",
                    submissionDate = "2026-09-19",
                    details = "الغرض: القبول الجامعي الموحد - دفتر العائلة رقم 458920 - الإقامة منذ عام 1999",
                    estimatedDays = 0
                ),
                RequestEntity(
                    trackingNumber = "DPA-MN-2026-5512",
                    serviceType = "MAINTENANCE",
                    serviceTitleAr = "صيانة شبكة تصريف وإنارة لجان الخدمات",
                    serviceTitleEn = "Drainage & Street Light Maintenance",
                    applicantName = "خالد محمود حسن",
                    nationalId = "9792038194",
                    campName = "مخيم حطين (شنلر)",
                    phoneNumber = "0789654321",
                    status = "IN_REVIEW",
                    submissionDate = "2026-09-21",
                    details = "صيانة عمود إنارة وشبكة تصريف الأمطار قرب مدرسة ذكور حطين",
                    estimatedDays = 2
                )
            )
            for (sample in samples) {
                requestDao.insertRequest(sample)
            }
        }
    }
}

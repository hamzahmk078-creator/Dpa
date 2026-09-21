package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.RequestEntity
import com.example.data.local.RequestRepository
import com.example.data.model.AppLanguage
import com.example.data.model.CampInfo
import com.example.data.model.CampsData
import com.example.data.model.NewsData
import com.example.data.model.NewsItem
import com.example.data.model.ServiceInfo
import com.example.data.model.ServicesData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class DpaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RequestRepository

    init {
        val dao = AppDatabase.getDatabase(application).requestDao()
        repository = RequestRepository(dao)
        viewModelScope.launch {
            repository.seedInitialDataIfEmpty()
        }
    }

    // Language State
    private val _language = MutableStateFlow(AppLanguage.ARABIC)
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    // Navigation Tab (0: Home, 1: Services, 2: Camps, 3: Tracker, 4: About)
    private val _currentTab = MutableStateFlow(0)
    val currentTab: StateFlow<Int> = _currentTab.asStateFlow()

    // Database Requests
    val requests: StateFlow<List<RequestEntity>> = repository.allRequests
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Search and filters
    private val _campSearchQuery = MutableStateFlow("")
    val campSearchQuery: StateFlow<String> = _campSearchQuery.asStateFlow()

    private val _governorateFilter = MutableStateFlow<String?>(null)
    val governorateFilter: StateFlow<String?> = _governorateFilter.asStateFlow()

    private val _trackerSearchQuery = MutableStateFlow("")
    val trackerSearchQuery: StateFlow<String> = _trackerSearchQuery.asStateFlow()

    private val _trackerStatusFilter = MutableStateFlow<String?>(null)
    val trackerStatusFilter: StateFlow<String?> = _trackerStatusFilter.asStateFlow()

    // Selected items for Dialogs / BottomSheets
    private val _selectedCamp = MutableStateFlow<CampInfo?>(null)
    val selectedCamp: StateFlow<CampInfo?> = _selectedCamp.asStateFlow()

    private val _selectedNews = MutableStateFlow<NewsItem?>(null)
    val selectedNews: StateFlow<NewsItem?> = _selectedNews.asStateFlow()

    private val _selectedServiceForm = MutableStateFlow<ServiceInfo?>(null)
    val selectedServiceForm: StateFlow<ServiceInfo?> = _selectedServiceForm.asStateFlow()

    private val _selectedRequestDetail = MutableStateFlow<RequestEntity?>(null)
    val selectedRequestDetail: StateFlow<RequestEntity?> = _selectedRequestDetail.asStateFlow()

    private val _lastSubmittedRequest = MutableStateFlow<RequestEntity?>(null)
    val lastSubmittedRequest: StateFlow<RequestEntity?> = _lastSubmittedRequest.asStateFlow()

    // Quick home search status tracking
    private val _quickTrackingResult = MutableStateFlow<RequestEntity?>(null)
    val quickTrackingResult: StateFlow<RequestEntity?> = _quickTrackingResult.asStateFlow()

    private val _quickTrackingSearched = MutableStateFlow(false)
    val quickTrackingSearched: StateFlow<Boolean> = _quickTrackingSearched.asStateFlow()

    fun toggleLanguage() {
        _language.value = if (_language.value == AppLanguage.ARABIC) {
            AppLanguage.ENGLISH
        } else {
            AppLanguage.ARABIC
        }
    }

    fun setTab(index: Int) {
        _currentTab.value = index
    }

    fun setCampSearch(query: String) {
        _campSearchQuery.value = query
    }

    fun setGovernorateFilter(filter: String?) {
        _governorateFilter.value = filter
    }

    fun setTrackerSearch(query: String) {
        _trackerSearchQuery.value = query
    }

    fun setTrackerStatusFilter(status: String?) {
        _trackerStatusFilter.value = status
    }

    fun selectCamp(camp: CampInfo?) {
        _selectedCamp.value = camp
    }

    fun selectNews(news: NewsItem?) {
        _selectedNews.value = news
    }

    fun openServiceForm(service: ServiceInfo) {
        _selectedServiceForm.value = service
    }

    fun closeServiceForm() {
        _selectedServiceForm.value = null
    }

    fun selectRequestDetail(request: RequestEntity?) {
        _selectedRequestDetail.value = request
    }

    fun dismissLastSubmission() {
        _lastSubmittedRequest.value = null
    }

    fun searchQuickTracking(trackingNumber: String) {
        if (trackingNumber.isBlank()) return
        viewModelScope.launch {
            _quickTrackingSearched.value = true
            _quickTrackingResult.value = repository.getRequestByTracking(trackingNumber)
        }
    }

    fun clearQuickTracking() {
        _quickTrackingResult.value = null
        _quickTrackingSearched.value = false
    }

    private fun generateTrackingNumber(prefix: String): String {
        val randNum = 1000 + Random.nextInt(9000)
        return "DPA-$prefix-2026-$randNum"
    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    fun submitMakruma(
        name: String,
        nationalId: String,
        campName: String,
        tawjihiNumber: String,
        tawjihiAverage: String,
        stream: String,
        unrwaSchool: String,
        phone: String
    ) {
        viewModelScope.launch {
            val tracking = generateTrackingNumber("MK")
            val details = "معدل التوجيهي: $tawjihiAverage% ($stream) - رقم الجلوس: $tawjihiNumber - مدرسة الأونروا: $unrwaSchool"
            val request = RequestEntity(
                trackingNumber = tracking,
                serviceType = "MAKRUMA",
                serviceTitleAr = "المكرمة الملكية السامية لأبناء المخيمات",
                serviceTitleEn = "Royal Makruma Admission for Camp Students",
                applicantName = name,
                nationalId = nationalId,
                campName = campName,
                phoneNumber = phone,
                status = "IN_REVIEW",
                submissionDate = getCurrentDate(),
                details = details,
                estimatedDays = 4
            )
            repository.insert(request)
            _selectedServiceForm.value = null
            _lastSubmittedRequest.value = request
        }
    }

    fun submitBuildingPermit(
        name: String,
        nationalId: String,
        campName: String,
        permitType: String,
        blockAndUnit: String,
        detailsInput: String,
        phone: String
    ) {
        viewModelScope.launch {
            val tracking = generateTrackingNumber("BP")
            val details = "نوع الترخيص: $permitType - الوحدة/البلوك: $blockAndUnit. التفاصيل: $detailsInput"
            val request = RequestEntity(
                trackingNumber = tracking,
                serviceType = "BUILDING_PERMIT",
                serviceTitleAr = "ترخيص بناء أو تعلية طوابق في المخيم",
                serviceTitleEn = "Building & Construction Permit in Camp",
                applicantName = name,
                nationalId = nationalId,
                campName = campName,
                phoneNumber = phone,
                status = "IN_REVIEW",
                submissionDate = getCurrentDate(),
                details = details,
                estimatedDays = 3
            )
            repository.insert(request)
            _selectedServiceForm.value = null
            _lastSubmittedRequest.value = request
        }
    }

    fun submitResidencyCert(
        name: String,
        nationalId: String,
        familyBookNo: String,
        campName: String,
        destinationEntity: String,
        yearsOfResidency: String,
        phone: String
    ) {
        viewModelScope.launch {
            val tracking = generateTrackingNumber("RC")
            val details = "الجهة الطالبة: $destinationEntity - دفتر العائلة: $familyBookNo - سنوات السكن بالمخيم: $yearsOfResidency سنة"
            val request = RequestEntity(
                trackingNumber = tracking,
                serviceType = "RESIDENCY_CERT",
                serviceTitleAr = "شهادة إثبات سكن وإقامة بالمخيم",
                serviceTitleEn = "Proof of Camp Residency Certificate",
                applicantName = name,
                nationalId = nationalId,
                campName = campName,
                phoneNumber = phone,
                status = "APPROVED", // Residency certificate generates certified approval immediately
                submissionDate = getCurrentDate(),
                details = details,
                estimatedDays = 0
            )
            repository.insert(request)
            _selectedServiceForm.value = null
            _lastSubmittedRequest.value = request
        }
    }

    fun submitMaintenanceReport(
        name: String,
        nationalId: String,
        campName: String,
        category: String,
        locationDetails: String,
        description: String,
        phone: String
    ) {
        viewModelScope.launch {
            val tracking = generateTrackingNumber("MN")
            val details = "قسم الصيانة: $category - الموقع: $locationDetails. الشكوى: $description"
            val request = RequestEntity(
                trackingNumber = tracking,
                serviceType = "MAINTENANCE",
                serviceTitleAr = "بلاغ صيانة وبنية تحتية للجان الخدمات",
                serviceTitleEn = "Infrastructure Maintenance Report",
                applicantName = name,
                nationalId = nationalId,
                campName = campName,
                phoneNumber = phone,
                status = "IN_PROGRESS",
                submissionDate = getCurrentDate(),
                details = details,
                estimatedDays = 2
            )
            repository.insert(request)
            _selectedServiceForm.value = null
            _lastSubmittedRequest.value = request
        }
    }

    fun deleteRequest(id: Long) {
        viewModelScope.launch {
            repository.deleteById(id)
            if (_selectedRequestDetail.value?.id == id) {
                _selectedRequestDetail.value = null
            }
        }
    }
}

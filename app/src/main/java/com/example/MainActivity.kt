package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.data.model.AppLanguage
import com.example.data.model.ServicesData
import com.example.ui.DpaViewModel
import com.example.ui.components.DpaBottomNav
import com.example.ui.components.DpaTopAppBar
import com.example.ui.components.NewsDetailDialog
import com.example.ui.components.RequestDetailDialog
import com.example.ui.components.SubmissionSuccessDialog
import com.example.ui.screens.AboutScreen
import com.example.ui.screens.CampDetailDialog
import com.example.ui.screens.CampsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ServiceFormDialog
import com.example.ui.screens.ServicesScreen
import com.example.ui.screens.TrackerScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel: DpaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                DpaApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun DpaApp(viewModel: DpaViewModel) {
    val language by viewModel.language.collectAsState()
    val currentTab by viewModel.currentTab.collectAsState()
    val requests by viewModel.requests.collectAsState()

    val campSearchQuery by viewModel.campSearchQuery.collectAsState()
    val governorateFilter by viewModel.governorateFilter.collectAsState()
    val trackerSearchQuery by viewModel.trackerSearchQuery.collectAsState()
    val trackerStatusFilter by viewModel.trackerStatusFilter.collectAsState()

    val selectedCamp by viewModel.selectedCamp.collectAsState()
    val selectedNews by viewModel.selectedNews.collectAsState()
    val selectedServiceForm by viewModel.selectedServiceForm.collectAsState()
    val selectedRequestDetail by viewModel.selectedRequestDetail.collectAsState()
    val lastSubmittedRequest by viewModel.lastSubmittedRequest.collectAsState()

    val quickTrackingResult by viewModel.quickTrackingResult.collectAsState()
    val quickTrackingSearched by viewModel.quickTrackingSearched.collectAsState()

    val layoutDirection = if (language == AppLanguage.ARABIC) {
        LayoutDirection.Rtl
    } else {
        LayoutDirection.Ltr
    }

    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                DpaTopAppBar(
                    language = language,
                    onToggleLanguage = { viewModel.toggleLanguage() }
                )
            },
            bottomBar = {
                DpaBottomNav(
                    selectedTab = currentTab,
                    onTabSelected = { viewModel.setTab(it) },
                    language = language
                )
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (currentTab) {
                    0 -> HomeScreen(
                        language = language,
                        quickTrackingResult = quickTrackingResult,
                        quickTrackingSearched = quickTrackingSearched,
                        onSearchTracking = { viewModel.searchQuickTracking(it) },
                        onClearQuickTracking = { viewModel.clearQuickTracking() },
                        onNavigateToServices = { viewModel.setTab(1) },
                        onNavigateToCamps = { viewModel.setTab(2) },
                        onNavigateToTracker = { viewModel.setTab(3) },
                        onSelectService = { viewModel.openServiceForm(it) },
                        onSelectNews = { viewModel.selectNews(it) },
                        onRequestDetailClick = { viewModel.selectRequestDetail(it) }
                    )

                    1 -> ServicesScreen(
                        language = language,
                        onOpenServiceForm = { viewModel.openServiceForm(it) }
                    )

                    2 -> CampsScreen(
                        language = language,
                        searchQuery = campSearchQuery,
                        governorateFilter = governorateFilter,
                        onSearchChange = { viewModel.setCampSearch(it) },
                        onGovernorateFilterChange = { viewModel.setGovernorateFilter(it) },
                        onCampClick = { viewModel.selectCamp(it) }
                    )

                    3 -> TrackerScreen(
                        requests = requests,
                        language = language,
                        searchQuery = trackerSearchQuery,
                        statusFilter = trackerStatusFilter,
                        onSearchChange = { viewModel.setTrackerSearch(it) },
                        onStatusFilterChange = { viewModel.setTrackerStatusFilter(it) },
                        onRequestClick = { viewModel.selectRequestDetail(it) }
                    )

                    4 -> AboutScreen(
                        language = language
                    )
                }
            }
        }

        // Dialogs & Sheets
        selectedCamp?.let { camp ->
            CampDetailDialog(
                camp = camp,
                language = language,
                onDismiss = { viewModel.selectCamp(null) },
                onApplyForThisCamp = {
                    viewModel.selectCamp(null)
                    viewModel.setTab(1) // Navigate to services
                }
            )
        }

        selectedNews?.let { news ->
            NewsDetailDialog(
                news = news,
                language = language,
                onDismiss = { viewModel.selectNews(null) }
            )
        }

        selectedServiceForm?.let { service ->
            ServiceFormDialog(
                service = service,
                language = language,
                onDismiss = { viewModel.closeServiceForm() },
                onSubmitMakruma = { name, nid, camp, tawjihiNo, avg, stream, school, phone ->
                    viewModel.submitMakruma(name, nid, camp, tawjihiNo, avg, stream, school, phone)
                },
                onSubmitBuildingPermit = { name, nid, camp, permitType, blockUnit, details, phone ->
                    viewModel.submitBuildingPermit(name, nid, camp, permitType, blockUnit, details, phone)
                },
                onSubmitResidencyCert = { name, nid, fb, camp, dest, yrs, phone ->
                    viewModel.submitResidencyCert(name, nid, fb, camp, dest, yrs, phone)
                },
                onSubmitMaintenance = { name, nid, camp, cat, loc, desc, phone ->
                    viewModel.submitMaintenanceReport(name, nid, camp, cat, loc, desc, phone)
                }
            )
        }

        lastSubmittedRequest?.let { submitted ->
            SubmissionSuccessDialog(
                request = submitted,
                language = language,
                onDismiss = {
                    viewModel.dismissLastSubmission()
                    viewModel.setTab(3) // switch to tracker
                }
            )
        }

        selectedRequestDetail?.let { request ->
            RequestDetailDialog(
                request = request,
                language = language,
                onDismiss = { viewModel.selectRequestDetail(null) },
                onDelete = { id -> viewModel.deleteRequest(id) }
            )
        }
    }
}

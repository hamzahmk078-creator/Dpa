package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.RequestEntity
import com.example.data.model.AppLanguage
import com.example.data.model.NewsData
import com.example.data.model.NewsItem
import com.example.data.model.ServiceInfo
import com.example.data.model.ServicesData
import com.example.ui.components.SectionHeader
import com.example.ui.components.StatusBadge
import com.example.ui.theme.DpaGoldContainer
import com.example.ui.theme.DpaGoldSecondary
import com.example.ui.theme.DpaGreenContainer
import com.example.ui.theme.DpaGreenDark
import com.example.ui.theme.DpaGreenPrimary
import com.example.ui.theme.StatusApprovedColor

@Composable
fun HomeScreen(
    language: AppLanguage,
    quickTrackingResult: RequestEntity?,
    quickTrackingSearched: Boolean,
    onSearchTracking: (String) -> Unit,
    onClearQuickTracking: () -> Unit,
    onNavigateToServices: () -> Unit,
    onNavigateToCamps: () -> Unit,
    onNavigateToTracker: () -> Unit,
    onSelectService: (ServiceInfo) -> Unit,
    onSelectNews: (NewsItem) -> Unit,
    onRequestDetailClick: (RequestEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    var trackingInput by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        // Hero Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_hero_dpa),
                    contentDescription = "DPA Hero Banner",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.85f)
                                ),
                                startY = 50f
                            )
                        )
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp)
                ) {
                    Surface(
                        color = DpaGoldSecondary,
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.padding(bottom = 6.dp)
                    ) {
                        Text(
                            text = if (language == AppLanguage.ARABIC) "البوابة الرسمية المعتمدة" else "Official Government Portal",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                    Text(
                        text = if (language == AppLanguage.ARABIC)
                            "دائرة الشؤون الفلسطينية"
                        else
                            "Department of Palestinian Affairs",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (language == AppLanguage.ARABIC)
                            "رعاية وتطوير شؤون مخيمات اللاجئين الفلسطينيين الـ 13 في المملكة الأردنية الهاشمية"
                        else
                            "Overseeing and developing the 13 Palestinian refugee camps across Jordan",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }

        // Fast Tracking Box
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .testTag("fast_tracking_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(DpaGreenContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = DpaGreenPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = if (language == AppLanguage.ARABIC) "استعلام سريع عن المعاملات" else "Quick Transaction Inquiry",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = if (language == AppLanguage.ARABIC) "أدخل رقم التتبع (مثال: DPA-MK-2026-8491)" else "Enter tracking code (e.g. DPA-MK-2026-8491)",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = trackingInput,
                            onValueChange = { trackingInput = it },
                            placeholder = {
                                Text(
                                    text = if (language == AppLanguage.ARABIC) "رقم التتبع..." else "Tracking code...",
                                    fontSize = 13.sp
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("home_tracking_input"),
                            shape = RoundedCornerShape(10.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { onSearchTracking(trackingInput) },
                            colors = ButtonDefaults.buttonColors(containerColor = DpaGreenPrimary),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.testTag("home_tracking_search_btn")
                        ) {
                            Text(if (language == AppLanguage.ARABIC) "بحث" else "Track")
                        }
                    }

                    // Result box if searched
                    AnimatedVisibility(visible = quickTrackingSearched) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            if (quickTrackingResult != null) {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = DpaGreenContainer),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onRequestDetailClick(quickTrackingResult) }
                                ) {
                                    Column(modifier = Modifier.padding(12.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = quickTrackingResult.trackingNumber,
                                                fontWeight = FontWeight.Bold,
                                                color = DpaGreenDark
                                            )
                                            StatusBadge(status = quickTrackingResult.status, language = language)
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = if (language == AppLanguage.ARABIC) quickTrackingResult.serviceTitleAr else quickTrackingResult.serviceTitleEn,
                                            style = MaterialTheme.typography.bodySmall,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                        Text(
                                            text = "${if (language == AppLanguage.ARABIC) "المستفيد: " else "Beneficiary: "}${quickTrackingResult.applicantName} (${quickTrackingResult.campName})",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            } else {
                                Card(
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                                    shape = RoundedCornerShape(12.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Info,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = if (language == AppLanguage.ARABIC)
                                                "لم يتم العثور على معاملة برقم التتبع المدخل. يرجى التحقق وإعادة المحاولة."
                                            else
                                                "No transaction found matching this tracking code.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onErrorContainer
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Quick E-Services Grid Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SectionHeader(
                    title = if (language == AppLanguage.ARABIC) "أبرز الخدمات الإلكترونية" else "Top E-Services",
                    subtitle = if (language == AppLanguage.ARABIC) "تقديم ومتابعة المعاملات المعتمدة" else "Apply and manage certified requests"
                )
                TextButton(onClick = onNavigateToServices) {
                    Text(
                        text = if (language == AppLanguage.ARABIC) "عرض الكل" else "View All",
                        color = DpaGreenPrimary,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // 4 Service Quick Cards
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val s1 = ServicesData.services.getOrNull(0)
                    if (s1 != null) {
                        HomeServiceTile(
                            service = s1,
                            icon = Icons.Default.School,
                            language = language,
                            onClick = { onSelectService(s1) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    val s2 = ServicesData.services.getOrNull(1)
                    if (s2 != null) {
                        HomeServiceTile(
                            service = s2,
                            icon = Icons.Default.Apartment,
                            language = language,
                            onClick = { onSelectService(s2) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val s3 = ServicesData.services.getOrNull(2)
                    if (s3 != null) {
                        HomeServiceTile(
                            service = s3,
                            icon = Icons.Default.VerifiedUser,
                            language = language,
                            onClick = { onSelectService(s3) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    val s4 = ServicesData.services.getOrNull(3)
                    if (s4 != null) {
                        HomeServiceTile(
                            service = s4,
                            icon = Icons.Default.Build,
                            language = language,
                            onClick = { onSelectService(s4) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Official Statistics
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(containerColor = DpaGreenDark),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (language == AppLanguage.ARABIC) "مؤشرات دائرة الشؤون الفلسطينية" else "DPA Institutional Metrics",
                        style = MaterialTheme.typography.titleSmall,
                        color = DpaGoldSecondary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        StatItem(
                            number = "13",
                            label = if (language == AppLanguage.ARABIC) "مخيماً رسمياً" else "Official Camps"
                        )
                        StatItem(
                            number = "350",
                            label = if (language == AppLanguage.ARABIC) "مقعد مكرمة سنوياً" else "Royal Univ. Seats"
                        )
                        StatItem(
                            number = "2.4M+",
                            label = if (language == AppLanguage.ARABIC) "مستفيد مسجل" else "Registered Ref."
                        )
                        StatItem(
                            number = "100%",
                            label = if (language == AppLanguage.ARABIC) "تحول رقمي" else "Digital Gov"
                        )
                    }
                }
            }
        }

        // Camps Exploration Banner
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable { onNavigateToCamps() }
                    .testTag("explore_camps_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DpaGoldContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (language == AppLanguage.ARABIC) "دليل المخيمات الفلسطينية الـ 13" else "13 Refugee Camps Guide",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = DpaGreenDark
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (language == AppLanguage.ARABIC)
                                "استكشف بيانات البقعة، الوحدات، الحسين، حطين، وباقي المخيمات ومقار لجان الخدمات."
                            else
                                "Explore demographic data, UNRWA clinics, and services committees contacts.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(DpaGreenPrimary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        // Latest News & Announcements
        item {
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = if (language == AppLanguage.ARABIC) "آخر الأخبار والإعلانات الرسمية" else "Official News & Announcements",
                subtitle = if (language == AppLanguage.ARABIC) "البيانات الصادرة عن دائرة الشؤون الفلسطينية" else "Statements and publications by DPA",
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        items(NewsData.newsList) { news ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .clickable { onSelectNews(news) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = if (news.isFeatured) DpaGoldSecondary else DpaGreenContainer,
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = if (language == AppLanguage.ARABIC) news.categoryAr else news.categoryEn,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (news.isFeatured) Color.Black else DpaGreenPrimary,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                            )
                        }
                        Text(
                            text = news.date,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = if (language == AppLanguage.ARABIC) news.titleAr else news.titleEn,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (language == AppLanguage.ARABIC) news.summaryAr else news.summaryEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun HomeServiceTile(
    service: ServiceInfo,
    icon: ImageVector,
    language: AppLanguage,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() }
            .testTag("service_tile_${service.id}"),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(14.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DpaGreenContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = DpaGreenPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (language == AppLanguage.ARABIC) service.titleAr else service.titleEn,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = if (language == AppLanguage.ARABIC) service.feeAr else service.feeEn,
                style = MaterialTheme.typography.labelSmall,
                color = DpaGoldSecondary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun StatItem(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold,
            color = Color.White
        )
        Text(
            text = label,
            fontSize = 11.sp,
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
    }
}

package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrackChanges
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.TrackChanges
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.example.ui.theme.DpaCrimsonTertiary
import com.example.ui.theme.DpaGoldContainer
import com.example.ui.theme.DpaGoldSecondary
import com.example.ui.theme.DpaGreenContainer
import com.example.ui.theme.DpaGreenDark
import com.example.ui.theme.DpaGreenPrimary
import com.example.ui.theme.StatusApprovedColor
import com.example.ui.theme.StatusInProgressColor
import com.example.ui.theme.StatusPendingColor

@Composable
fun DpaTopAppBar(
    language: AppLanguage,
    onToggleLanguage: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Official Emblem Thumbnail
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(DpaGreenDark)
                        .border(1.dp, DpaGoldSecondary, RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_app_icon),
                        contentDescription = "DPA Emblem",
                        modifier = Modifier.size(38.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = if (language == AppLanguage.ARABIC) "المملكة الأردنية الهاشمية" else "Hashemite Kingdom of Jordan",
                        style = MaterialTheme.typography.labelSmall,
                        color = DpaGoldSecondary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (language == AppLanguage.ARABIC) "دائرة الشؤون الفلسطينية" else "Dept. of Palestinian Affairs",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "dpa.gov.jo",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Language Toggle Chip
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { onToggleLanguage() }
                    .testTag("language_toggle_button")
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Language,
                        contentDescription = "Language",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (language == AppLanguage.ARABIC) "English" else "عربي",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Composable
fun DpaBottomNav(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Triple(
            if (language == AppLanguage.ARABIC) "الرئيسية" else "Home",
            Icons.Default.Home,
            Icons.Outlined.Home
        ),
        Triple(
            if (language == AppLanguage.ARABIC) "الخدمات" else "Services",
            Icons.Default.Assignment,
            Icons.Outlined.Assignment
        ),
        Triple(
            if (language == AppLanguage.ARABIC) "المخيمات" else "Camps",
            Icons.Default.Apartment,
            Icons.Outlined.Apartment
        ),
        Triple(
            if (language == AppLanguage.ARABIC) "طلباتي" else "My Requests",
            Icons.Default.TrackChanges,
            Icons.Outlined.TrackChanges
        ),
        Triple(
            if (language == AppLanguage.ARABIC) "عن الدائرة" else "About DPA",
            Icons.Default.Info,
            Icons.Outlined.Info
        )
    )

    NavigationBar(
        modifier = modifier.testTag("main_bottom_nav"),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 6.dp
    ) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedTab == index
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (isSelected) item.second else item.third,
                        contentDescription = item.first,
                        tint = if (isSelected) DpaGreenPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = {
                    Text(
                        text = item.first,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = DpaGreenContainer,
                    selectedIconColor = DpaGreenPrimary,
                    selectedTextColor = DpaGreenPrimary
                ),
                modifier = Modifier.testTag("nav_item_$index")
            )
        }
    }
}

@Composable
fun StatusBadge(
    status: String,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val (label, bg, fg) = when (status.uppercase()) {
        "APPROVED" -> Triple(
            if (language == AppLanguage.ARABIC) "معتمد ومكتمل" else "Approved",
            Color(0xFFDCFCE7),
            StatusApprovedColor
        )
        "IN_PROGRESS" -> Triple(
            if (language == AppLanguage.ARABIC) "قيد الإجراء الميداني" else "In Progress",
            Color(0xFFFEF3C7),
            StatusInProgressColor
        )
        "IN_REVIEW" -> Triple(
            if (language == AppLanguage.ARABIC) "قيد المراجعة والتدقيق" else "Under Review",
            Color(0xFFDBEAFE),
            StatusPendingColor
        )
        else -> Triple(
            if (language == AppLanguage.ARABIC) "جديد" else "Submitted",
            Color(0xFFF3F4F6),
            Color(0xFF374151)
        )
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(bg)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = fg
        )
    }
}

@Composable
fun SectionHeader(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 4.dp, height = 20.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(DpaGreenPrimary)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        if (subtitle != null) {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 12.dp, top = 2.dp)
            )
        }
    }
}

@Composable
fun SubmissionSuccessDialog(
    request: RequestEntity,
    language: AppLanguage,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = DpaGreenPrimary),
                modifier = Modifier.testTag("dialog_confirm_button")
            ) {
                Text(if (language == AppLanguage.ARABIC) "تم، متابعة الطلب" else "Done, Track Request")
            }
        },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Success",
                    tint = StatusApprovedColor,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (language == AppLanguage.ARABIC) "تم إرسال الطلب بنجاح" else "Request Submitted Successfully",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = if (language == AppLanguage.ARABIC)
                        "تم تسجيل معاملتك لدى دائرة الشؤون الفلسطينية بنجاح. يرجى الاحتفاظ برقم التتبع لمتابعة الإجراءات."
                    else
                        "Your transaction has been recorded with the Department of Palestinian Affairs. Keep this tracking code to check status.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = DpaGoldContainer),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (language == AppLanguage.ARABIC) "رقم التتبع المعتمد" else "Official Tracking Number",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = request.trackingNumber,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = DpaGreenDark
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (language == AppLanguage.ARABIC) "مقدم الطلب:" else "Applicant:",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = request.applicantName,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (language == AppLanguage.ARABIC) "المخيم:" else "Camp:",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = request.campName,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun RequestDetailDialog(
    request: RequestEntity,
    language: AppLanguage,
    onDismiss: () -> Unit,
    onDelete: (Long) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (language == AppLanguage.ARABIC) request.serviceTitleAr else request.serviceTitleEn,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Tracking code & Status
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (language == AppLanguage.ARABIC) "رقم التتبع" else "Tracking #",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = request.trackingNumber,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = DpaGreenPrimary
                        )
                    }
                    StatusBadge(status = request.status, language = language)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Progress Stepper (4 steps)
                val currentStep = when (request.status.uppercase()) {
                    "PENDING" -> 1
                    "IN_REVIEW" -> 2
                    "IN_PROGRESS" -> 3
                    "APPROVED" -> 4
                    else -> 1
                }

                Text(
                    text = if (language == AppLanguage.ARABIC) "مراحل إنجاز المعاملة:" else "Processing Timeline:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                val steps = listOf(
                    if (language == AppLanguage.ARABIC) "1. تقديم الطلب وتوليد الرمز" else "1. Submission",
                    if (language == AppLanguage.ARABIC) "2. تدقيق الوثائق والشروط" else "2. Document Audit",
                    if (language == AppLanguage.ARABIC) "3. الكشف الميداني والتنسيق" else "3. Field Verification",
                    if (language == AppLanguage.ARABIC) "4. الاعتماد النهائي والإصدار" else "4. Official Approval"
                )

                steps.forEachIndexed { index, stepName ->
                    val isDone = index + 1 <= currentStep
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 3.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .clip(CircleShape)
                                .background(if (isDone) DpaGreenPrimary else Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            if (isDone) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = stepName,
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = if (isDone) FontWeight.Bold else FontWeight.Normal,
                            color = if (isDone) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(10.dp))

                // Details list
                DetailRow(
                    label = if (language == AppLanguage.ARABIC) "الاسم:" else "Name:",
                    value = request.applicantName
                )
                DetailRow(
                    label = if (language == AppLanguage.ARABIC) "الرقم الوطني:" else "National ID:",
                    value = request.nationalId
                )
                DetailRow(
                    label = if (language == AppLanguage.ARABIC) "المخيم:" else "Camp:",
                    value = request.campName
                )
                DetailRow(
                    label = if (language == AppLanguage.ARABIC) "رقم الهاتف:" else "Phone:",
                    value = request.phoneNumber
                )
                DetailRow(
                    label = if (language == AppLanguage.ARABIC) "تاريخ التقديم:" else "Date:",
                    value = request.submissionDate
                )

                Spacer(modifier = Modifier.height(8.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(
                            text = if (language == AppLanguage.ARABIC) "بيانات المعاملة:" else "Details:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = request.details,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = DpaGreenPrimary)
            ) {
                Text(if (language == AppLanguage.ARABIC) "إغلاق" else "Close")
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = { onDelete(request.id) },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = DpaCrimsonTertiary)
            ) {
                Text(if (language == AppLanguage.ARABIC) "حذف المعاملة" else "Delete")
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun NewsDetailDialog(
    news: com.example.data.model.NewsItem,
    language: AppLanguage,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (language == AppLanguage.ARABIC) news.categoryAr else news.categoryEn,
                    style = MaterialTheme.typography.labelMedium,
                    color = DpaGoldSecondary,
                    fontWeight = FontWeight.Bold
                )
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (language == AppLanguage.ARABIC) news.titleAr else news.titleEn,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DpaGreenDark
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = news.date,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = if (language == AppLanguage.ARABIC) news.contentAr else news.contentEn,
                    style = MaterialTheme.typography.bodyMedium,
                    lineHeight = 22.sp
                )
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = DpaGreenPrimary)
            ) {
                Text(if (language == AppLanguage.ARABIC) "إغلاق" else "Close")
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}


package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.CampInfo
import com.example.data.model.CampsData
import com.example.ui.components.SectionHeader
import com.example.ui.theme.DpaGoldContainer
import com.example.ui.theme.DpaGoldSecondary
import com.example.ui.theme.DpaGreenContainer
import com.example.ui.theme.DpaGreenDark
import com.example.ui.theme.DpaGreenPrimary

@Composable
fun CampsScreen(
    language: AppLanguage,
    searchQuery: String,
    governorateFilter: String?,
    onSearchChange: (String) -> Unit,
    onGovernorateFilterChange: (String?) -> Unit,
    onCampClick: (CampInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    val governorates = listOf(
        Pair(null, if (language == AppLanguage.ARABIC) "كافة المحافظات" else "All Governorates"),
        Pair("عمان", if (language == AppLanguage.ARABIC) "العاصمة عمان (4)" else "Amman (4)"),
        Pair("البلقاء", if (language == AppLanguage.ARABIC) "البلقاء (2)" else "Balqa (2)"),
        Pair("الزرقاء", if (language == AppLanguage.ARABIC) "الزرقاء (2)" else "Zarqa (2)"),
        Pair("إربد", if (language == AppLanguage.ARABIC) "إربد (2)" else "Irbid (2)"),
        Pair("جرش", if (language == AppLanguage.ARABIC) "جرش (2)" else "Jerash (2)"),
        Pair("مادبا", if (language == AppLanguage.ARABIC) "مادبا (1)" else "Madaba (1)")
    )

    val filteredCamps = CampsData.allCamps.filter { camp ->
        val matchesSearch = if (searchQuery.isBlank()) true else {
            camp.nameAr.contains(searchQuery, ignoreCase = true) ||
                    camp.nameEn.contains(searchQuery, ignoreCase = true) ||
                    camp.governorateAr.contains(searchQuery, ignoreCase = true) ||
                    camp.descriptionAr.contains(searchQuery, ignoreCase = true)
        }
        val matchesGov = if (governorateFilter == null) true else {
            camp.governorateAr.contains(governorateFilter) || camp.governorateEn.contains(governorateFilter)
        }
        matchesSearch && matchesGov
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("camps_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            SectionHeader(
                title = if (language == AppLanguage.ARABIC) "دليل مخيمات اللاجئين الفلسطينين (13 مخيماً)" else "13 Palestinian Refugee Camps Directory",
                subtitle = if (language == AppLanguage.ARABIC)
                    "المخيمات الرسمية المشرفة عليها دائرة الشؤون الفلسطينية بالتنسيق مع وكالة الغوث (الأونروا)"
                else
                    "Official camps supervised by DPA in coordination with UNRWA in Jordan"
            )
        }

        // Search Input
        item {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                placeholder = {
                    Text(if (language == AppLanguage.ARABIC) "ابحث باسم المخيم أو المحافظة..." else "Search camp or governorate...")
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = DpaGreenPrimary)
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { onSearchChange("") }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("camp_search_field")
            )
        }

        // Filter Chips Row
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(governorates) { (key, label) ->
                    val isSelected = governorateFilter == key
                    FilterChip(
                        selected = isSelected,
                        onClick = { onGovernorateFilterChange(key) },
                        label = { Text(text = label, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = DpaGreenPrimary,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }
        }

        items(filteredCamps) { camp ->
            CampCard(
                camp = camp,
                language = language,
                onClick = { onCampClick(camp) }
            )
        }
    }
}

@Composable
fun CampCard(
    camp: CampInfo,
    language: AppLanguage,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .testTag("camp_card_${camp.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (language == AppLanguage.ARABIC) camp.nameAr else camp.nameEn,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = DpaGreenDark
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = DpaGreenPrimary,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (language == AppLanguage.ARABIC) camp.governorateAr else camp.governorateEn,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Surface(
                    color = DpaGoldContainer,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "${if (language == AppLanguage.ARABIC) "تأسس " else "Est. "}${camp.establishmentYear}",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (language == AppLanguage.ARABIC) camp.descriptionAr else camp.descriptionEn,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(8.dp))

            // Population and Area row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Group,
                        contentDescription = null,
                        tint = DpaGreenPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = camp.population,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.SquareFoot,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = camp.areaSqKm,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        tint = DpaGoldSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = camp.phone,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun CampDetailDialog(
    camp: CampInfo,
    language: AppLanguage,
    onDismiss: () -> Unit,
    onApplyForThisCamp: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (language == AppLanguage.ARABIC) camp.nameAr else camp.nameEn,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = DpaGreenDark
                    )
                    Text(
                        text = if (language == AppLanguage.ARABIC) camp.governorateAr else camp.governorateEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = onDismiss) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = if (language == AppLanguage.ARABIC) camp.descriptionAr else camp.descriptionEn,
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(14.dp))

                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = if (language == AppLanguage.ARABIC) "مقر لجنة خدمات المخيم:" else "Camp Committee Office:",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = DpaGreenDark
                        )
                        Text(
                            text = if (language == AppLanguage.ARABIC) camp.committeeAddressAr else camp.committeeAddressEn,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "${if (language == AppLanguage.ARABIC) "هاتف اللجنة / المكتب: " else "Phone: "}${camp.phone}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = if (language == AppLanguage.ARABIC) "منشآت وخدمات وكالة الغوث (الأونروا):" else "UNRWA Facilities & Services:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(6.dp))

                val facilities = if (language == AppLanguage.ARABIC) camp.unrwaFacilitiesAr else camp.unrwaFacilitiesEn
                facilities.forEach { facility ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(DpaGreenPrimary)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = facility,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onApplyForThisCamp,
                colors = ButtonDefaults.buttonColors(containerColor = DpaGreenPrimary)
            ) {
                Text(if (language == AppLanguage.ARABIC) "تقديم معاملة لهذا المخيم" else "Apply for This Camp")
            }
        },
        shape = RoundedCornerShape(16.dp)
    )
}

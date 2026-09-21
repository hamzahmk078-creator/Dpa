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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.AppLanguage
import com.example.ui.components.SectionHeader
import com.example.ui.theme.DpaGoldContainer
import com.example.ui.theme.DpaGoldSecondary
import com.example.ui.theme.DpaGreenContainer
import com.example.ui.theme.DpaGreenDark
import com.example.ui.theme.DpaGreenPrimary

@Composable
fun AboutScreen(
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("about_screen"),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = DpaGreenDark)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(2.dp, DpaGoldSecondary, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_app_icon),
                            contentDescription = "DPA Emblem",
                            modifier = Modifier.size(62.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (language == AppLanguage.ARABIC) "المملكة الأردنية الهاشمية" else "Hashemite Kingdom of Jordan",
                        style = MaterialTheme.typography.labelMedium,
                        color = DpaGoldSecondary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = if (language == AppLanguage.ARABIC) "دائرة الشؤون الفلسطينية" else "Department of Palestinian Affairs",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "dpa.gov.jo",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
            }
        }

        // Institutional Mandate
        item {
            SectionHeader(
                title = if (language == AppLanguage.ARABIC) "النشأة والمهام الوطنية" else "Mandate & Mission"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = if (language == AppLanguage.ARABIC)
                            "تأسست دائرة الشؤون الفلسطينية كجهاز حكومي أردني متخصص للإشراف على شؤون اللاجئين الفلسطينيين ومتابعة قضاياهم الحياتية والخدماتية داخل المخيمات الـ 13 المنتشرة في محافظات المملكة. وتعمل الدائرة بالتنسيق الوثيق مع وكالة الأمم المتحدة لإغاثة وتشغيل اللاجئين الفلسطينيين (الأونروا) والوزارات المعنية لصيانة البنية التحتية وتقديم التسهيلات وتأكيد ثوابت الأردن التاريخية في حماية حقوق اللاجئين."
                        else
                            "The Department of Palestinian Affairs (DPA) was established as a specialized Jordanian governmental institution overseeing Palestinian refugee affairs and community development across the 13 refugee camps in Jordan. DPA coordinates closely with UNRWA and national ministries to upgrade infrastructure, administer public welfare, and reinforce Jordan's historical commitment.",
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(14.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(12.dp))

                    val tasks = listOf(
                        if (language == AppLanguage.ARABIC) "الإشراف الكامل على لجان خدمات المخيمات الـ 13 ومشاريع التطوير" else "Supervision of 13 Camp Services Committees and projects",
                        if (language == AppLanguage.ARABIC) "تنظيم وتدقيق طلبات المكرمة الملكية السامية للقبول الجامعي" else "Administration of Royal Makruma university admissions",
                        if (language == AppLanguage.ARABIC) "إصدار رخص وتراخيص البناء وتعلية الطوابق وفق المخططات" else "Issuing building permits and vertical expansions",
                        if (language == AppLanguage.ARABIC) "التنسيق الدبلوماسي والميداني المستمر مع وكالة الغوث (الأونروا)" else "Diplomatic and field coordination supporting UNRWA mandate"
                    )

                    tasks.forEach { task ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Shield,
                                contentDescription = null,
                                tint = DpaGreenPrimary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = task,
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }

        // Contact Information
        item {
            SectionHeader(
                title = if (language == AppLanguage.ARABIC) "المقر الرئيسي ومعلومات الاتصال" else "Headquarters & Contact"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ContactRow(
                        icon = Icons.Default.LocationOn,
                        title = if (language == AppLanguage.ARABIC) "الموقع:" else "Address:",
                        value = if (language == AppLanguage.ARABIC) "عمان - الشميساني - شارع عبد الحميد شرف - مبنى دائرة الشؤون الفلسطينية" else "Amman - Shmeisani - Abdul Hameed Sharaf St - DPA Building"
                    )
                    ContactRow(
                        icon = Icons.Default.Call,
                        title = if (language == AppLanguage.ARABIC) "الهاتف الرئيسي:" else "Main Phone:",
                        value = "+962 6 5683261 / +962 6 5683262"
                    )
                    ContactRow(
                        icon = Icons.Default.Call,
                        title = if (language == AppLanguage.ARABIC) "الخط الساخن للجان المخيمات:" else "Camps Committee Hotline:",
                        value = "06-4720101"
                    )
                    ContactRow(
                        icon = Icons.Default.Language,
                        title = if (language == AppLanguage.ARABIC) "الموقع الإلكتروني الرسمي:" else "Official Website:",
                        value = "https://dpa.gov.jo"
                    )
                    ContactRow(
                        icon = Icons.Default.Email,
                        title = if (language == AppLanguage.ARABIC) "البريد الإلكتروني:" else "Email:",
                        value = "info@dpa.gov.jo"
                    )
                    ContactRow(
                        icon = Icons.Default.Schedule,
                        title = if (language == AppLanguage.ARABIC) "ساعات الدوام الرسمي:" else "Working Hours:",
                        value = if (language == AppLanguage.ARABIC) "الأحد - الخميس: 8:30 ص إلى 3:30 م" else "Sunday - Thursday: 8:30 AM - 3:30 PM"
                    )
                }
            }
        }

        // FAQs
        item {
            SectionHeader(
                title = if (language == AppLanguage.ARABIC) "الأسئلة الشائعة" else "Frequently Asked Questions"
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        val faqs = listOf(
            Pair(
                if (language == AppLanguage.ARABIC) "ما هي شروط التقدم للمكرمة الملكية السامية لأبناء المخيمات؟" else "What are the eligibility criteria for the Royal Makruma?",
                if (language == AppLanguage.ARABIC)
                    "يشترط أن يكون الطالب مقيماً فعلياً في أحد مخيمات اللاجئين الـ 13، وأن يكون حاصلاً على معدل 65% فأعلى في الثانوية العامة (التوجيهي)، وأن يكون قد درس في مدارس وكالة الغوث (الأونروا) للمرحلة الأساسية أو جزءاً منها."
                else
                    "The student must be an actual resident of one of the 13 camps, have a Tawjihi score of 65% or above, and have studied in UNRWA schools for all or part of basic education."
            ),
            Pair(
                if (language == AppLanguage.ARABIC) "كم طابقاً يسمح بتعليته فوق الوحدات السكنية داخل المخيم؟" else "How many floors can be added to residential units in camps?",
                if (language == AppLanguage.ARABIC)
                    "تسمح تعليمات دائرة الشؤون الفلسطينية ببناء حتى طابقين إضافيين فوق الوحدة السكنية بعد الحصول على الكشف الإنشائي الهندسي من مهندس لجنة خدمات المخيم ودفع الرسوم المقررة."
                else
                    "DPA regulations permit adding up to two additional floors above the residential unit following an engineering safety inspection by Camp Services engineers."
            ),
            Pair(
                if (language == AppLanguage.ARABIC) "كيف يمكن الاستعلام عن حالة معاملة مقدمة؟" else "How can I track the status of my application?",
                if (language == AppLanguage.ARABIC)
                    "يمكنك إدخال رقم التتبع (مثل DPA-MK-2026-XXXX) في شاشة الرئيسية أو الانتقال إلى تبويب 'طلباتي' للاطلاع على تفاصيل الخطوات الأربع والموافقة المعتمدة."
                else
                    "Enter your tracking code on the Home screen or navigate to the 'My Requests' tab to view the 4-step live progress and approvals."
            )
        )

        items(faqs.size) { index ->
            val (q, a) = faqs[index]
            FaqItemCard(question = q, answer = a)
        }
    }
}

@Composable
fun ContactRow(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(DpaGreenContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = DpaGreenPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun FaqItemCard(question: String, answer: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded },
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
                Text(
                    text = question,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = DpaGreenPrimary
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = answer,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = 20.sp
                    )
                }
            }
        }
    }
}

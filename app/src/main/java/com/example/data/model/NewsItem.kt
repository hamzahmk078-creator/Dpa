package com.example.data.model

data class NewsItem(
    val id: String,
    val titleAr: String,
    val titleEn: String,
    val date: String,
    val categoryAr: String,
    val categoryEn: String,
    val summaryAr: String,
    val summaryEn: String,
    val contentAr: String,
    val contentEn: String,
    val isFeatured: Boolean = false
)

object NewsData {
    val newsList = listOf(
        NewsItem(
            id = "news_1",
            titleAr = "بدء استقبال طلبات الاستفادة من المكرمة الملكية السامية لأبناء المخيمات في الجامعات الأردنية",
            titleEn = "Applications open for Royal Makruma University Admission for Camp Students",
            date = "2026-09-18",
            categoryAr = "مكرمة ملكية",
            categoryEn = "Royal Initiative",
            summaryAr = "تعلن دائرة الشؤون الفلسطينية عن بدء تقديم طلبات الاستفادة من الـ 350 مقعداً جامعياً المخصصة لأبناء المخيمات إلكترونياً بالتزامن مع القبول الموحد.",
            summaryEn = "DPA announces online applications for the 350 Royal university seats allocated for camp students in coordination with Unified Admission.",
            contentAr = "تنفيذاً للتوجيهات الملكية السامية من جلالة الملك عبدالله الثاني ابن الحسين، تعلن دائرة الشؤون الفلسطينية عن إطلاق منصة استقبال طلبات الاستفادة من مقاعد المكرمة الملكية السامية في الجامعات الرسمية الأردنية للطلبة الناجحين في الثانوية العامة بمعدل لا يقل عن 65% والذين درسوا في مدارس وكالة الغوث.",
            contentEn = "Under the Royal directives of His Majesty King Abdullah II, DPA launched the electronic submission portal for the 350 university seats allocated across official Jordanian universities for students with at least 65% Tawjihi average who attended UNRWA schools.",
            isFeatured = true
        ),
        NewsItem(
            id = "news_2",
            titleAr = "إطلاق الحزمة التطويرية للبنية التحتية وشبكات تصريف الأمطار في مخيمات البقعة والوحدات وحطين",
            titleEn = "Launch of infrastructure upgrade and storm drainage projects in Baqa'a, Wehdat and Hittin camps",
            date = "2026-09-15",
            categoryAr = "مشاريع وتطوير",
            categoryEn = "Development",
            summaryAr = "مدير عام دائرة الشؤون الفلسطينية يعلن انطلاق مشاريع تعبيد الشوارع واستبدال خطوط الصرف وشبكات الإنارة الموفرة للطاقة بتكلفة ممولة حكومياً.",
            summaryEn = "Director General of DPA inaugurates road paving, drainage lines replacement, and energy-saving lighting in refugee camps.",
            contentAr = "أكدت دائرة الشؤون الفلسطينية استمرار تنفيذ الخطة الشاملة لتحسين الظروف البيئية والخدمية في كافة المخيمات الـ 13، من خلال تجهيز محطات تصريف مياه الأمطار وصيانة شبكات الإنارة وتعبيد الشوارع الرئيسية والتنسيق اللوجستي المستمر مع لجان الخدمات والبلديات.",
            contentEn = "The Department of Palestinian Affairs reaffirmed its ongoing comprehensive development plan across all 13 refugee camps, executing stormwater drainage projects, LED road illumination, and continuous coordination with camp service committees.",
            isFeatured = true
        ),
        NewsItem(
            id = "news_3",
            titleAr = "الأردن يجدد في المحافل الدولية موقفه الثابت بدعم وكالة الغوث 'الأونروا' واستمرار خدماتها",
            titleEn = "Jordan reiterates steadfast stance supporting UNRWA mandate and uninterrupted operations",
            date = "2026-09-10",
            categoryAr = "بيان رسمي",
            categoryEn = "Official Statement",
            summaryAr = "أكدت الدائرة أن الحفاظ على الأونروا ودورها الإنساني والتعليمي واجب دولي لحين التوصل إلى حل عادل ودائم لقضية اللاجئين وفق القرارات الأممية.",
            summaryEn = "The Department emphasizes that safeguarding UNRWA's mandate is an international responsibility until a just and lasting resolution is achieved.",
            contentAr = "شددت دائرة الشؤون الفلسطينية على أن المملكة الأردنية الهاشمية، بقيادتها الهاشمية الحكيمة، تبذل جهوداً دبلوماسية مكثفة لحشد الدعم المالي والسياسي لوكالة الغوث الدولية لتمكينها من مواصلة تقديم خدماتها الحيوية في التعليم والصحة والإغاثة دون انقطاع.",
            contentEn = "Jordan, under its Hashemite leadership, exerts diplomatic efforts to mobilize political and financial support for UNRWA, enabling uninterrupted vital educational, healthcare, and relief operations for millions of refugees.",
            isFeatured = false
        ),
        NewsItem(
            id = "news_4",
            titleAr = "حملات بيئية شاملة للنظافة وتجميل المرافق العامة بالتنسيق مع أندية المخيمات والجمعيات",
            titleEn = "Comprehensive environmental and sanitation campaigns launched with camp clubs and NGOs",
            date = "2026-09-05",
            categoryAr = "نشاطات مجتمعية",
            categoryEn = "Community Action",
            summaryAr = "مشاركة تطوعية واسعة من شباب المخيمات في حملات تنظيف وتجميل الجدران وغرس الأشجار لتعزيز الثقافة البيئية والسلامة العامة.",
            summaryEn = "Youth voluntary participation across camps in tree planting, cleanups, and public facility enhancement promoting civic pride.",
            contentAr = "نظمت لجان تحسين المخيمات بالتعاون مع دائرة الشؤون الفلسطينية يوماً وطنياً للنظافة والتشجير استهدف المرافق التعليمية ومحيط المدارس والحدائق المجتمعية في كافة محافظات المملكة.",
            contentEn = "Camp improvement committees alongside DPA organized a national greening and sanitation drive targeting educational surroundings, community parks, and common thoroughfares across governorates.",
            isFeatured = false
        )
    )
}

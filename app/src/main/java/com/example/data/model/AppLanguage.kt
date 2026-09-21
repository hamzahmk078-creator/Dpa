package com.example.data.model

enum class AppLanguage {
    ARABIC,
    ENGLISH
}

data class ServiceInfo(
    val id: String,
    val titleAr: String,
    val titleEn: String,
    val descAr: String,
    val descEn: String,
    val feeAr: String,
    val feeEn: String,
    val processingTimeAr: String,
    val processingTimeEn: String,
    val requirementsAr: List<String>,
    val requirementsEn: List<String>
)

object ServicesData {
    val services = listOf(
        ServiceInfo(
            id = "MAKRUMA",
            titleAr = "المكرمة الملكية لأبناء المخيمات (الجامعات)",
            titleEn = "Royal Makruma Admission for Camp Students",
            descAr = "الاستفادة من الـ 350 مقعداً جامعياً المخصصة بمكرمة ملكية سامية في الجامعات الرسمية الأردنية للطلبة من سكان المخيمات.",
            descEn = "Apply for 350 royal university seats designated for camp resident students in public Jordanian universities.",
            feeAr = "مجاناً",
            feeEn = "Free",
            processingTimeAr = "3 - 5 أيام عمل (خلال فترة القبول الموحد)",
            processingTimeEn = "3 - 5 business days (during Unified Admission period)",
            requirementsAr = listOf(
                "أن يكون الطالب مقيماً فعلياً في أحد المخيمات الـ 13",
                "ألا يقل معدل الثانوية العامة (التوجيهي) عن 65%",
                "أن يكون الطالب قد درس المرحلة الأساسية أو جزءاً منها في مدارس وكالة الغوث (الأونروا)",
                "إحضار شهادة إثبات دراسة من وكالة الغوث وصورة دفتر العائلة"
            ),
            requirementsEn = listOf(
                "Actual residency in one of the 13 refugee camps in Jordan",
                "Minimum Tawjihi general secondary score of 65%",
                "Completed basic education or part of it in UNRWA schools",
                "UNRWA school attendance certificate and copy of family book"
            )
        ),
        ServiceInfo(
            id = "BUILDING_PERMIT",
            titleAr = "تراخيص البناء وتعلية الطوابق داخل المخيم",
            titleEn = "Building & Extra Floor Construction Permits",
            descAr = "إصدار وتجديد رخص البناء والترميم وتعلية حتى طابقين إضافيين فوق الوحدات السكنية وفق المخططات التنظيمية.",
            descEn = "Issuance and renewal of residential construction, renovation, and addition of up to two floors inside camps.",
            feeAr = "رسوم رمزية مقررة للجان الخدمات",
            feeEn = "Nominal fee prescribed by Services Committee",
            processingTimeAr = "2 - 4 أيام عمل (بعد الكشف الميداني)",
            processingTimeEn = "2 - 4 business days (following field inspection)",
            requirementsAr = listOf(
                "إثبات تخصيص الوحدة السكنية باسم مقدم الطلب",
                "كشف هندسي من مهندس لجنة خدمات المخيم لسلامة الأساسات",
                "مخطط كروكي مبسط للأعمال الإنشائية أو الترميم",
                "براءة ذمة من لجنة خدمات المخيم"
            ),
            requirementsEn = listOf(
                "Proof of residential unit allocation in applicant's name",
                "Engineering inspection from Camp Services engineer for structural integrity",
                "Architectural sketch of proposed construction or renovation",
                "Financial clearance from Camp Services Committee"
            )
        ),
        ServiceInfo(
            id = "RESIDENCY_CERT",
            titleAr = "شهادة إثبات سكن وإقامة داخل المخيم",
            titleEn = "Proof of Camp Residency Certificate",
            descAr = "وثيقة رسمية معتمدة تثبت سكن المواطن داخل المخيم للجهات الحكومية والجامعات والمنظمات الدولية.",
            descEn = "Official certified document proving applicant's residency within camp borders for ministries, universities, and UNRWA.",
            feeAr = "مجاناً",
            feeEn = "Free",
            processingTimeAr = "نفس اليوم فورياً / إلكترونياً",
            processingTimeEn = "Same day instant / electronic",
            requirementsAr = listOf(
                "الرقم الوطني ودفتر العائلة ساري المفعول",
                "تحديد رقم القطعة / البلوك / الوحدة السكنية",
                "تحديد الجهة الموجه إليها الشهادة"
            ),
            requirementsEn = listOf(
                "Valid National ID and Family Book",
                "Plot / Block / Housing Unit number confirmation",
                "Specification of the requesting authority/entity"
            )
        ),
        ServiceInfo(
            id = "MAINTENANCE",
            titleAr = "شكاوى وبلاغات صيانة البنية التحتية",
            titleEn = "Camp Infrastructure & Municipal Reports",
            descAr = "تقديم طلبات الصيانة الطارئة والعامة لشبكات الصرف الصحي، إنارة الشوارع، تصريف الأمطار، والنظافة للجان الخدمات.",
            descEn = "Submit urgent and municipal maintenance requests for sewer networks, streetlights, stormwater drains, and hygiene.",
            feeAr = "مجاناً",
            feeEn = "Free",
            processingTimeAr = "24 - 48 ساعة حسب طبيعة البلاغ",
            processingTimeEn = "24 - 48 hours according to urgency level",
            requirementsAr = listOf(
                "تحديد موقع العطل أو الخلل بدقة داخل المخيم",
                "وصف المشكلة (إنارة، هبوط شارع، انسداد مصرف)",
                "رقم هاتف للتواصل الميداني مع فرقة الصيانة"
            ),
            requirementsEn = listOf(
                "Precise location specification within the camp",
                "Clear description of issue (lighting, road depression, drainage block)",
                "Contact phone for on-site team coordination"
            )
        )
    )
}

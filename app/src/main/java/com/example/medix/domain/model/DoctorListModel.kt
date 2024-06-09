package com.example.medix.domain.model

fun generateFakePagingItems(count: Int): List<Doctor> {
    val items = mutableListOf<Doctor>()
    for (i in 0 until count) {
        val item = Doctor(
            id = 1,
            speciality = "Dentist",
            bio = "he is the best around here",
            name = "Abdelrahman Tarif",
            address = "",
            phone = "0123456789",
            dateOfBirth = "12/12/2023",
            gender = "Male",
            email = "",
            image = "",
            wage = 0.0,
            favorites = emptyList(),
            appointments = emptyList(),
            imagefile = ""
        )
        items.add(item)
    }
    return items
}
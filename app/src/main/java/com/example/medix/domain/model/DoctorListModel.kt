package com.example.medix.domain.model

fun generateFakePagingItems(count: Int): List<Doctor> {
    val items = mutableListOf<Doctor>()
    for (i in 0 until count) {
        val item = Doctor(
            id = i,
            speciality = "Doctor $i",
            bio = "he is the best around here",
            name = "Abdelrahman Tarif",
            address = "",
            phoneNumber = "0123456789",
            dateOfBirth = "12/12/2023",
            gender = Gender.MALE,
            email = "",
            image = "",
            password = "",
            wage = 0.0
        )
        items.add(item)
    }
    return items
}
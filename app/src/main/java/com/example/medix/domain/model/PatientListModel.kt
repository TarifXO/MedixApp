package com.example.medix.domain.model

fun generateFakePagingItemsForPatients(count: Int): List<Patient> {
    val items = mutableListOf<Patient>()
    for (i in 0 until count) {
        val item = Patient(
            id = i,
            phoneNumber = "Patient $i",
            email = "he needs some medical help",
            name = "Youssef Hawash",
            image = "",
            dateOfBirth = "",
            gender = Gender.MALE,
            password = "123456789"
        )
        items.add(item)
    }
    return items
}
package com.example.medix.domain.model

fun generateFakePagingItemsForPatients(count: Int): List<Patient> {
    val items = mutableListOf<Patient>()
    for (i in 0 until count) {
        val item = Patient(
            id = i,
            name = "Patient $i",
            description = "he needs some medical help",
            title = "Youssef Hawash",
            url = "",
            urlToImage = ""
        )
        items.add(item)
    }
    return items
}
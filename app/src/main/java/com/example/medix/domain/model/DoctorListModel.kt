package com.example.medix.domain.model

fun generateFakePagingItems(count: Int): List<Doctor> {
    val items = mutableListOf<Doctor>()
    for (i in 0 until count) {
        val item = Doctor(
            id = i,
            name = "Doctor $i",
            description = "he is the best around here",
            title = "Abdelrahman Tarif",
            url = "",
            urlToImage = ""
        )
        items.add(item)
    }
    return items
}
package com.example.storeroom.util

enum class CategoryItemType(val value: String) {
    USUAL("Usual"),
    SEE_MORE("See More");

    companion object {
        fun fromString(value: String): CategoryItemType? {
            return values().find { it.value == value }
        }
    }
}

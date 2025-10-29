package com.example.memorygame.model

import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "memory_cards")
data class MemoryCard(
    val name: String,
    val theme: String,
    val imageUrl: String
) : BaseEntity()
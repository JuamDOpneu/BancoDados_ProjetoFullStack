package com.example.memorygame.repository

import com.example.memorygame.model.MemoryCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemoryCardRepository : JpaRepository<MemoryCard, Long> {

    /**
     * Requisito: Fazer uma consulta (por nome, valor, etc).
     * O Spring Data JPA entende o nome dessa função e cria a consulta
     * "SELECT * FROM memory_cards WHERE theme LIKE '%?%'".
     */
    fun findByThemeContainingIgnoreCase(theme: String): List<MemoryCard>

    /**
     * Consulta extra para o filtro por nome.
     */
    fun findByNameContainingIgnoreCase(name: String): List<MemoryCard>
}
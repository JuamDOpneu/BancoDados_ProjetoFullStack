package com.example.memorygame.controller

import com.example.memorygame.model.MemoryCard
import com.example.memorygame.service.CardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/cards")
// ESTA LINHA É A MAIS IMPORTANTE PARA A CONEXÃO:
@CrossOrigin(origins = ["http://localhost:5173"])
class MemoryCardController(
    private val cardService: CardService
) {

    /**
     * Requisito: Buscar lista E Fazer consulta.
     * GET /api/cards -> Lista todos
     * GET /api/cards?theme=Animais -> Filtra por tema
     */
    @GetMapping
    fun getCards(
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) theme: String?
    ): List<MemoryCard> {
        println("pegar cartas")
        return cardService.search(name, theme)
    }
    @GetMapping("/themes")
    fun getDistinctThemes(): List<String> {
        return cardService.findDistinctThemes()
    }

    /**
     * Requisito: Salvar entidade.
     * POST /api/cards
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCard(@RequestBody card: MemoryCard): MemoryCard {
        println("criar cartas")
        return cardService.save(card)
    }

    /**
     * GET /api/cards/{id}
     * Um endpoint extra para o React buscar uma carta específica para editar.
     */
    @GetMapping("/{id}")
    fun getCardById(@PathVariable id: Long): ResponseEntity<MemoryCard> {
        val card = cardService.findById(id)
        println("carta id: $id")
        return if (card != null) {
            ResponseEntity.ok(card)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    /**
     * Requisito: Editar entidade.
     * PUT /api/cards/{id}
     */
    @PutMapping("/{id}")
    fun updateCard(@PathVariable id: Long, @RequestBody card: MemoryCard): ResponseEntity<MemoryCard> {
        val updatedCard = cardService.update(id, card)
        println("atualizar cartas")
        return if (updatedCard != null) {
            ResponseEntity.ok(updatedCard)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    /**
     * Requisito: Excluir entidade.
     * DELETE /api/cards/{id}
     */
    @DeleteMapping("/{id}")
    fun deleteCard(@PathVariable id: Long): ResponseEntity<Void> {
        return if (cardService.delete(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
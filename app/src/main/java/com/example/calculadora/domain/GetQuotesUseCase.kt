package com.example.calculadora.domain

import com.example.calculadora.data.model.QuoteModel
import com.example.calculadora.data.repositories.QuoteRepository

class GetQuotesUseCase {
    private val repository = QuoteRepository()

    suspend operator fun invoke():List<QuoteModel>? = repository.getAllQuotes()
}
package com.example.calculadora.data.repositories

import com.example.calculadora.data.model.QuoteModel
import com.example.calculadora.data.model.QuoteProvider
import com.example.calculadora.data.network.QuoteService

class QuoteRepository {
    private val  api = QuoteService()

    suspend fun getAllQuotes():List<QuoteModel>{
        val response:List<QuoteModel> = api.getQuotes()
        QuoteProvider.quotes= response
        return response
    }
}
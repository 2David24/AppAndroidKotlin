package com.example.calculadora.UI.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calculadora.data.model.QuoteModel
import com.example.calculadora.data.model.QuoteProvider
import com.example.calculadora.domain.GetQuotesUseCase
import kotlinx.coroutines.launch

class QuoteViewModel:ViewModel() {
    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()
    var getQuotesUseCase = GetQuotesUseCase()


    fun OneCreate() {
       viewModelScope.launch {
           isLoading.postValue(true)
           val result:List<QuoteModel>? = getQuotesUseCase()

           if (!result.isNullOrEmpty()){
               isLoading.postValue(false)
               quoteModel.postValue(result[0])
           }
       }
    }

    fun randomQuote(){
   //     val currentQuote = QuoteProvider.random()
     //   quoteModel.postValue(currentQuote)
    }

}
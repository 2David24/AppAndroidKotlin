package com.example.calculadora.UI.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.calculadora.databinding.ActivityFrasesBinding
import com.example.calculadora.UI.viewmodel.QuoteViewModel

class FrasesActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFrasesBinding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFrasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.OneCreate()


        quoteViewModel.quoteModel.observe(this, Observer {
            binding.tvQuote.text = it.quote
            binding.tvAuthor.text = it.author
        })
        quoteViewModel.isLoading.observe(this,{
            binding.progress.isVisible = it
        })


        binding.viewContainer.setOnClickListener { quoteViewModel.randomQuote() }
    }
}
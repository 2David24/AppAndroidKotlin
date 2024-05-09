package com.example.calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var operand1: Double = 0.0
    private var operator: String = ""
    private var isNewOperation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        display = findViewById(R.id.display)

        val buttons = arrayOf(
            findViewById<Button>(R.id.btn_0),
            findViewById<Button>(R.id.btn_1),
            findViewById<Button>(R.id.btn_2),
            findViewById<Button>(R.id.btn_3),
            findViewById<Button>(R.id.btn_4),
            findViewById<Button>(R.id.btn_5),
            findViewById<Button>(R.id.btn_6),
            findViewById<Button>(R.id.btn_7),
            findViewById<Button>(R.id.btn_8),
            findViewById<Button>(R.id.btn_9),
            findViewById<Button>(R.id.btn_clear),
            findViewById<Button>(R.id.btn_backspace),
            findViewById<Button>(R.id.btn_divide),
            findViewById<Button>(R.id.btn_multiply),
            findViewById<Button>(R.id.btn_subtract),
            findViewById<Button>(R.id.btn_add),
            findViewById<Button>(R.id.btn_equals),
            findViewById<Button>(R.id.btn_square_root),
            findViewById<Button>(R.id.btn_decimal),
        )

        for (button in buttons) {
            button.setOnClickListener { onButtonClick(button) }
        }
    }

    private fun onButtonClick(view: View) {
        val buttonText = (view as Button).text.toString()

        when {
            buttonText == "C" -> clearDisplay()
            buttonText == "⌫" -> backspace()
            buttonText == "/" -> setOperator("/")
            buttonText == "x" -> setOperator("*")
            buttonText == "-" -> setOperator("-")
            buttonText == "+" -> setOperator("+")
            buttonText == "=" -> {
                // Solo calcular el resultado si no es una nueva operación
                if (!isNewOperation) {
                    calculateResult()
                }
            }
            buttonText == "." -> addDecimalPoint()
            buttonText == "√" -> calculateSquareRoot()
            else -> appendDigit(buttonText)
        }
    }

    private fun clearDisplay() {
        display.text = "0"
        operand1 = 0.0
        operator = ""
        isNewOperation = true
    }

    private fun backspace() {
        val currentText = display.text.toString()
        if (currentText.length > 1) {
            display.text = currentText.substring(0, currentText.length - 1)
        } else {
            display.text = "0"
        }
    }

    private fun setOperator(op: String) {
        // Calcular el resultado si no es una nueva operación y hay un operador existente
        if (!isNewOperation && operator.isNotBlank()) {
            calculateResult()
        }
        operator = op
        isNewOperation = true

        // Guardar el primer operando
        operand1 = display.text.toString().toDouble()
    }


    private fun calculateResult() {
        val operand2 = display.text.toString().toDouble()
        val result = when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> {
                if (operand2 != 0.0) {
                    operand1 / operand2
                } else {
                    // Manejo de división por cero
                    Double.NaN
                }
            }
            else -> 0.0
        }

        if (!result.isNaN()) {
            // Formatear el resultado para eliminar ".0" si es un número entero
            val formattedResult = if (result % 1 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }
            display.text = formattedResult
        } else {
            // Mensaje de error para división por cero
            display.text = "Error"
        }

        // Limpiar operandos y operador después de la operación
        operand1 = 0.0
        operator = ""
        isNewOperation = true
    }


    private fun addDecimalPoint() {
        if (isNewOperation) {
            display.text = "0.0"
            isNewOperation = false
        } else if (!display.text.contains(".")) {
            display.append(".")
        }
    }

    private fun appendDigit(digit: String) {
        if (isNewOperation) {
            display.text = digit
            isNewOperation = false
        } else {
            if (display.text == "0" && digit != "0") {
                display.text = digit
            } else {
                display.append(digit)
            }
        }
    }


    private fun calculateSquareRoot() {
        val currentValue = display.text.toString().toDouble()
        display.text = sqrt(currentValue).toString()
    }
}
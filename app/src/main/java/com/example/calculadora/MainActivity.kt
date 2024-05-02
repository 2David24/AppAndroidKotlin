package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    //Inicializar componentes de vista

    //Button
    lateinit var tvUno:Button
    lateinit var tvDos:Button
    lateinit var tvTres:Button
    lateinit var tvCuatro:Button
    lateinit var tvCinco:Button
    lateinit var tvSeis:Button
    lateinit var tvSiete:Button
    lateinit var tvOcho:Button
    lateinit var tvNueve:Button
    lateinit var tvCero:Button

    // Buttons Operadores matemáticos
    lateinit var sum: Button
    lateinit var rest: Button
    lateinit var mult: Button
    lateinit var div: Button
    lateinit var raiz: Button
    lateinit var porcentaje: Button
    lateinit var exponente: Button

    // Buttons Acciones
    lateinit var igual: Button
    lateinit var borrar: Button
    lateinit var punto: Button

    // TextView
    lateinit var txt_nunUno: TextView
    lateinit var txt_nunDos: TextView
    lateinit var tvResult: TextView

    // Variables que almacenan
    var op:String = ""
    var numr1:Double = 0.0
    var numr2:Double = 0.0
    var resul:Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Buttons Numeros
        tvUno = findViewById(R.id.bt_1)
        tvDos = findViewById(R.id.bt_2)
        tvTres = findViewById(R.id.bt_3)
        tvCuatro = findViewById(R.id.bt_4)
        tvCinco = findViewById(R.id.bt_5)
        tvSeis = findViewById(R.id.bt_6)
        tvSiete = findViewById(R.id.bt_7)
        tvOcho = findViewById(R.id.bt_8)
        tvNueve = findViewById(R.id.bt_9)
        tvCero = findViewById(R.id.bt_0)

        // Buttons Operadores matemáticos
        sum = findViewById(R.id.bt_suma);
        rest = findViewById(R.id.bt_resta);
        mult = findViewById(R.id.bt_multiply);
        div = findViewById(R.id.bt_divide);
        raiz = findViewById(R.id.bt_raiz);
        exponente = findViewById(R.id.bt_exponente);
        porcentaje = findViewById(R.id.bt_porcentaje);

        // Buttons Acciones
        igual = findViewById(R.id.bt_igual);
        borrar = findViewById(R.id.bt_ac);
        punto = findViewById(R.id.bt_punto);

        // TxtView de Resultado
        tvResult = findViewById(R.id.txt_resultado)

        // Llamar Funciones
        buttonsNums()
        buttonsAction()
        operations()

    }

    private fun buttonsNums(){
        // Asigna un OnClickListener al TextView tvUno
        tvUno.setOnClickListener {
            // Obtiene el texto actual del TextView, agrega "1" y lo establece como el nuevo texto del TextView
            tvResult.setText(tvResult.getText().toString() + "1")
        }

        tvDos.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "2")
        }

        tvTres.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "3")
        }

        tvCuatro.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "4")
        }

        tvCinco.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "5")
        }

        tvSeis.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "6")
        }

        tvSiete.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "7")
        }

        tvOcho.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "8")
        }

        tvNueve.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "9")
        }

        tvCero.setOnClickListener {
            tvResult.setText(tvResult.getText().toString() + "0")
        }

    }

    private fun buttonsAction() {
        // Asigna un OnClickListener al botón borrar
        borrar.setOnClickListener { tvResult.setText("") }// Establece el texto del TextView tvResult como una cadena vacía
        // Asigna un OnClickListener al botón punto
        punto.setOnClickListener {
            if (tvResult.getText().toString() == ""){
                tvResult.setText(tvResult.getText().toString() + "0.") // Obtiene el texto actual del TextView y si el texto es vacio entonces agrega "0."  y lo establece como el nuevo texto del TextView
            }else{
                tvResult.setText(tvResult.getText().toString() + ".") // Obtiene el texto actual del TextView, agrega "." y lo establece como el nuevo texto del TextView
            }
        }
    }

    private fun operations() {
        // Asigna un OnClickListener al botón de suma (sum)
        sum.setOnClickListener {
            // Verifica si el TextView está vacío
            if (tvResult.text.toString() == "") {
                // Muestra un mensaje de error si está vacío
                Toast.makeText(this, "Por favor ingresa un valor en el campo", Toast.LENGTH_SHORT).show()
            } else {
                // Si no está vacío, convierte el texto del TextView a Double y lo asigna a numr1
                numr1 = tvResult.text.toString().toDouble()
                // Asigna el operador "+" a la variable op
                op = "+"
                // Establece el texto del TextView tvResult como una cadena vacía
                tvResult.setText("")
                // Llama a la función igual con los parámetros op y numr1
                igual(op,numr1)
            }
        }
        rest.setOnClickListener {
            if (tvResult.text.toString() == "") {
                tvResult.setText(tvResult.getText().toString() + "-")
            } else {
                numr1 = tvResult.text.toString().toDouble()
                op = "-"
                tvResult.setText("")
                igual(op,numr1)
            }
        }
        mult.setOnClickListener {
            if (tvResult.text.toString() == "") {
                Toast.makeText(this, "Por favor ingresa un valor en el campo", Toast.LENGTH_SHORT).show()
            } else {
                numr1 = tvResult.text.toString().toDouble()
                op = "*"
                tvResult.setText("")
                igual(op,numr1)
            }
        }
        div.setOnClickListener {
            if (tvResult.text.toString() == "") {
                Toast.makeText(this, "Por favor ingresa un valor en el campo", Toast.LENGTH_SHORT).show()
            } else {
                numr1 = tvResult.text.toString().toDouble()
                op = "/"
                tvResult.setText("")
                igual(op,numr1)
            }

        }
        raiz.setOnClickListener {
            if (tvResult.text.toString() == "") {
                Toast.makeText(this, "Por favor ingresa un valor en el campo", Toast.LENGTH_SHORT).show()
            } else {
                numr1 = tvResult.text.toString().toDouble()
                op = "√"
                igual(op,numr1)
            }

        }
        porcentaje.setOnClickListener {
            if (tvResult.text.toString() == "") {
                Toast.makeText(this, "Por favor ingresa un valor en el campo", Toast.LENGTH_SHORT).show()
            } else {
                numr1 = tvResult.text.toString().toDouble()
                op = "%"
                tvResult.setText("")
                igual(op,numr1)
            }

        }
        exponente.setOnClickListener {
            if (tvResult.text.toString() == "") {
                Toast.makeText(this, "Por favor ingresa un valor en el campo", Toast.LENGTH_SHORT).show()
            } else {
                numr1 = tvResult.text.toString().toDouble()
                op = "^"
                tvResult.text = ""
                igual(op, numr1)
            }

        }
    }

    private fun igual(op:String, numr1:Double) {
        // Asigna un OnClickListener al botón igual
        igual.setOnClickListener {
            // Convierte el texto del TextView a Double y lo asigna a numr2
            numr2 = tvResult.text.toString().toDouble()
            when (op) {
                // Realiza la operación correspondiente según el operador almacenado en op
                "+" -> resul = sumar(numr1,numr2)
                "/" -> resul = divisio(numr1,numr2)
                "*" -> resul = multiplicar(numr1,numr2)
                "-" -> resul = restar(numr1,numr2)
                "^" -> resul = exponente(numr1,numr2)
                "%" -> resul = porcentaje(numr1,numr2)
                "√" -> resul = raiz(numr1)
                // Si el operador no es válido, muestra un mensaje de error
                else ->  Toast.makeText(this, "Por favor ingresa valores en todos los campos", Toast.LENGTH_SHORT).show()
            }
            if (!resul.isNaN()){
                Toast.makeText(this, "No puedes dividir en 0", Toast.LENGTH_SHORT).show()
            }else{
                // Establece el resultado de la operación como el texto del TextView tvResult
                tvResult.setText(resul.toString())
            }

        }
    }

    // Funciones de operaciones matemáticas
    private fun sumar(a:Double, b:Double):Double {return a + b}

    private fun restar(a:Double, b:Double):Double {return a - b}

    private fun multiplicar(a:Double, b:Double):Double {return a * b}

    private fun exponente(a:Double, b:Double):Double {return a.pow(b)}

    private fun porcentaje(a:Double, b:Double):Double {return a * b / 100}

    private fun raiz(a:Double):Double {
        if (a <= 0 ) {
            Double.NaN
        }
        return sqrt(a)
    }

    private fun divisio(a: Double, b: Double): Double {
        return if (b > 0.0) {
             a / b
        } else {
            println("Error: El divisor no puede ser cero.")
            Double.NaN
        }
    }



}
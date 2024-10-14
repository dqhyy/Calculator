package com.example.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var textViewInput: TextView
    private var currentInput: String = ""
    private var currentOperator: String? = null
    private var firstValue: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewInput = findViewById(R.id.textView_input)

        // Setup button listeners
        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        val button0 = findViewById<Button>(R.id.button_0)
        val button1 = findViewById<Button>(R.id.button_1)
        val button2 = findViewById<Button>(R.id.button_2)
        val button3 = findViewById<Button>(R.id.button_3)
        val button4 = findViewById<Button>(R.id.button_4)
        val button5 = findViewById<Button>(R.id.button_5)
        val button6 = findViewById<Button>(R.id.button_6)
        val button7 = findViewById<Button>(R.id.button_7)
        val button8 = findViewById<Button>(R.id.button_8)
        val button9 = findViewById<Button>(R.id.button_9)

        val buttonPlus = findViewById<Button>(R.id.button_plus)
        val buttonMinus = findViewById<Button>(R.id.button_minus)
        val buttonMultiply = findViewById<Button>(R.id.button_multiply)
        val buttonDivide = findViewById<Button>(R.id.button_divide)
        val buttonEquals = findViewById<Button>(R.id.button_equals)
        val buttonClear = findViewById<Button>(R.id.button_c)
        val buttonCE = findViewById<Button>(R.id.button_ce)
        val buttonDot = findViewById<Button>(R.id.button_dot)

        // Number buttons
        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }

        // Operator buttons
        buttonPlus.setOnClickListener { setOperator("+") }
        buttonMinus.setOnClickListener { setOperator("-") }
        buttonMultiply.setOnClickListener { setOperator("*") }
        buttonDivide.setOnClickListener { setOperator("/") }

        // Function buttons
        buttonEquals.setOnClickListener { calculateResult() }
        buttonClear.setOnClickListener { clearAll() }
        buttonCE.setOnClickListener { clearEntry() }
        buttonDot.setOnClickListener { appendDot() }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        textViewInput.text = currentInput
    }

    private fun appendDot() {
        if (!currentInput.contains(".")) {
            currentInput += "."
            textViewInput.text = currentInput
        }
    }

    private fun setOperator(operator: String) {
        if (currentInput.isNotEmpty()) {
            firstValue = currentInput.toDouble()
            currentOperator = operator
            currentInput = ""
        }
    }

    private fun calculateResult() {
        if (firstValue != null && currentOperator != null && currentInput.isNotEmpty()) {
            val secondValue = currentInput.toDouble()
            var result: Double? = null

            when (currentOperator) {
                "+" -> result = firstValue!! + secondValue
                "-" -> result = firstValue!! - secondValue
                "*" -> result = firstValue!! * secondValue
                "/" -> result = if (secondValue != 0.0) firstValue!! / secondValue else null
            }

            if (result != null) {
                textViewInput.text = result.toString()
                firstValue = result
                currentInput = ""
            } else {
                textViewInput.text = "Error"
            }
        }
    }

    private fun clearAll() {
        currentInput = ""
        currentOperator = null
        firstValue = null
        textViewInput.text = ""
    }

    private fun clearEntry() {
        currentInput = ""
        textViewInput.text = ""
    }
}

package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    var prev_op : Char = '\u0000'
    var curr_op : Char = '\u0000'
    var prev_value : Double = 0.0
    var curr_value : Double = 0.0

    lateinit var etxt_total : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etxt_total = findViewById(R.id.etxt_total)
        etxt_total.text.append('0')

        findViewById<Button>(R.id.btn_clear).setOnClickListener  { onClick_Operation('c') }
        findViewById<Button>(R.id.btn_sign).setOnClickListener   { onClick_Operation('±') }
        findViewById<Button>(R.id.btn_per).setOnClickListener    { onClick_Operation('%') }
        findViewById<Button>(R.id.btn_add).setOnClickListener    { onClick_Operation('+') }
        findViewById<Button>(R.id.btn_sub).setOnClickListener    { onClick_Operation('-') }
        findViewById<Button>(R.id.btn_mul).setOnClickListener    { onClick_Operation('*') }
        findViewById<Button>(R.id.btn_div).setOnClickListener    { onClick_Operation('/') }
        findViewById<Button>(R.id.btn_dot).setOnClickListener    { onClick_Operation('.') }
        findViewById<Button>(R.id.btn_equals).setOnClickListener { onClick_Operation('=') }

        findViewById<Button>(R.id.btn_digit_0).setOnClickListener { onClick_Digit(0) }
        findViewById<Button>(R.id.btn_digit_1).setOnClickListener { onClick_Digit(1) }
        findViewById<Button>(R.id.btn_digit_2).setOnClickListener { onClick_Digit(2) }
        findViewById<Button>(R.id.btn_digit_3).setOnClickListener { onClick_Digit(3) }
        findViewById<Button>(R.id.btn_digit_4).setOnClickListener { onClick_Digit(4) }
        findViewById<Button>(R.id.btn_digit_5).setOnClickListener { onClick_Digit(5) }
        findViewById<Button>(R.id.btn_digit_6).setOnClickListener { onClick_Digit(6) }
        findViewById<Button>(R.id.btn_digit_7).setOnClickListener { onClick_Digit(7) }
        findViewById<Button>(R.id.btn_digit_8).setOnClickListener { onClick_Digit(8) }
        findViewById<Button>(R.id.btn_digit_9).setOnClickListener { onClick_Digit(9) }
    }

    fun onClick_Operation(operation: Char) {
        when (operation) {
            'c' -> {
                prev_op = '\u0000'
                prev_value = 0.0
                curr_value = 0.0
                etxt_total.text.clear()
                etxt_total.text.append('0')
            }

            '.' -> {
                if (!etxt_total.text.contains('.'))
                    etxt_total.text.append('.')
            }

            '=' -> {
                if (etxt_total.text.endsWith('.'))
                    etxt_total.text.append('0')

                curr_value = etxt_total.text.toString().toDouble()

                when (prev_op) {
                    '+' -> prev_value += curr_value
                    '-' -> prev_value -= curr_value
                    '*' -> prev_value *= curr_value
                    '/' -> prev_value /= curr_value
                }

                etxt_total.text.clear()
                etxt_total.text.append(prev_value.toString())
            }

            '+', '-', '*', '/' -> pushOp(operation)
        }

    }

    fun onClick_Digit(digit: Int) {
        if (etxt_total.text[0] == '0')
            etxt_total.text.clear()

        etxt_total.text.append(digit.toString())
    }

    fun pushOp(operation: Char) {
        if (etxt_total.text.endsWith('.'))
            etxt_total.text.append('0')

        prev_value = etxt_total.text.toString().toDouble()

        etxt_total.text.clear()
        etxt_total.text.append('0')

        prev_op = operation;
    }

}
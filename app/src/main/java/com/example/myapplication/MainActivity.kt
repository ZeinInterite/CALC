package com.example.myapplication

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat
import java.text.ParseException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {
        // Обработка нажатий кнопок и добавление значений в поле ввода
        binding.buttonClear.setOnClickListener {
            binding.input.text = " "// Очистка поля ввода
            binding.output.text = " "// Очистка поля вывода
        }
        binding.buttonBracketLeft.setOnClickListener {
            addToInputText("(") // Добавление открывающей скобки
        }
        binding.buttonBracketRight.setOnClickListener {
            addToInputText(")") // Добавление закрывающей скобки
        }
        binding.button0.setOnClickListener { addToInputText("0") }
        binding.button1.setOnClickListener { addToInputText("1") }
        binding.button2.setOnClickListener { addToInputText("2") }
        binding.button3.setOnClickListener { addToInputText("3") }
        binding.button4.setOnClickListener { addToInputText("4") }
        binding.button5.setOnClickListener { addToInputText("5") }
        binding.button6.setOnClickListener { addToInputText("6") }
        binding.button7.setOnClickListener { addToInputText("7") }
        binding.button8.setOnClickListener { addToInputText("8") }
        binding.button9.setOnClickListener { addToInputText("9") }
        binding.buttonDot.setOnClickListener { addToInputText(".") } // Добавление десятичной точки

        // Обработка нажатий математических операций
        binding.buttonDivision.setOnClickListener { addToInputText("/") }
        binding.buttonMultiply.setOnClickListener { addToInputText("*") }
        binding.buttonSubtraction.setOnClickListener { addToInputText("-") }
        binding.buttonAddition.setOnClickListener { addToInputText("+") }

        // Вычисление результата
        binding.buttonEquals.setOnClickListener {
            showResult() // Вывод результата
        }

        // Обработка нажатия процента
        binding.buttonPercent.setOnClickListener { addToInputText("%") }
    }

    // Функция добавления текста в поле ввода
    private fun addToInputText(value: String) {
        binding.input.append(value)
    }

    // Функция получения строки выражения
    private fun getInputExpression(): String {
        return binding.input.text.toString()
    }

    // Функция вывода результата
    private fun showResult() {
        try {
            // Вычисление выражения
            val expression = getInputExpression().replace("%", "/100")
            val result = ExpressionBuilder(expression).build().evaluate()

            // Форматирование результата
            val formattedResult = DecimalFormat("0.######").format(result).toString()
            binding.output.text = formattedResult
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.neon_green))
        } catch (e: ParseException) {
            Log.e("Calculator", "Ошибка при парсинге выражения", e)
            binding.output.text = "Ошибка: неверное выражение"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        } catch (e: ArithmeticException) {
            Log.e("Calculator", "Ошибка при вычислении результата", e)
            binding.output.text = "Ошибка: деление на ноль"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        } catch (e: NumberFormatException) {
            Log.e("Calculator", "Ошибка при форматировании результата", e)
            binding.output.text = "Ошибка: некорректное значение"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        } catch (e: Exception) {
            Log.e("Calculator", "Неизвестная ошибка", e)
            binding.output.text = "Ошибка"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}

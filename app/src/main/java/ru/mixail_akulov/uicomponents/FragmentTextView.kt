package ru.mixail_akulov.uicomponents

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mixail_akulov.uicomponents.databinding.FragmentTextViewBinding
import kotlin.random.Random

class FragmentTextView : Fragment(), View.OnClickListener {

    private var binding: FragmentTextViewBinding ? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextViewBinding.inflate(inflater, container, false)

        binding?.changeTextView?.setOnClickListener(this)

        return binding?.root
    }

    override fun onClick(v: View?) {
        with(binding?.testTextView) {
            this?.setText(R.string.lorem_ipsum)
            this?.setTextColor(Color.RED)
            this?.setLines(2)
        }

        // Присваиваем переменной currentTextValue значение R.string.lorem_ipsum и передаем в лог.
        val currentTextValue: String = binding?.testTextView?.text.toString()
        Log.d("TESTLOG", "Current text value: $currentTextValue")

        // Добавляем к тексту случайное значение от 0 до 100 и присваиваем это тексту
        val textValueWithRandomNumber = "$currentTextValue ${Random.nextInt(100)}"
        binding?.testTextView?.text = textValueWithRandomNumber

        // Получаем обратно обновленное значение и добавляем в лог
        val updatedTextValue =  binding?.testTextView?.text.toString()
        Log.d("TESTLOG", "Updated text value: $updatedTextValue")
    }
}
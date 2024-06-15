package ru.mixail_akulov.uicomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.mixail_akulov.uicomponents.databinding.FragmentButtonBinding
import ru.mixail_akulov.uicomponents.databinding.FragmentEditTextBinding
import ru.mixail_akulov.uicomponents.databinding.FragmentImageViewBinding
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class FragmentEditText : Fragment() {

    private var binding: FragmentEditTextBinding? = null

    private var useKeyword: Boolean = false // используется, если много checkBox-ов. Если он один,
    // то просто везде его через биндинг прописать: binding.useKeywordCheckBox.isChecked

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEditTextBinding.inflate(inflater, container, false)

        binding?.getRandomImageButton?.setOnClickListener {
            onGetRandomImagePressed()
        }

        // прослушивание нажатия на клавишу ввода
        binding?.keywordEditText?.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                return@setOnEditorActionListener onGetRandomImagePressed()
            }
            return@setOnEditorActionListener false
        }

        // срабатывает только когда пользователь нажал
        binding?.useKeywordCheckBox?.setOnClickListener {
            this.useKeyword = binding?.useKeywordCheckBox!!.isChecked

            updateUi()
        }

        // срабатывает и когда программно изменился checkBox
        binding?.useAutoEnterSwitch?.setOnCheckedChangeListener { _, isChecked ->
            binding?.keywordEditText?.setText("girl")
        }

        updateUi()

        return binding?.root
    }

    private fun onGetRandomImagePressed(): Boolean {
        val keyword = binding?.keywordEditText?.text.toString()
        if (useKeyword && keyword.isBlank()) {
            binding?.keywordEditText?.error = "Keyword is empty"

            return true // возвращаем тру, чтобы клавиатруа не скрывалась
        }

        val encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.name())
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600?${if (useKeyword) "?$encodedKeyword" else ""}")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.wait)
            .into(binding?.testImageView!!)

        return false  // возвращаем false, чтобы скрыть клаву
    }

    private fun updateUi() = with(binding!!) {
        useKeywordCheckBox.isChecked = useKeyword
        if (useKeyword) keywordEditText.visibility = View.VISIBLE
        else keywordEditText.visibility = View.GONE
    }

}
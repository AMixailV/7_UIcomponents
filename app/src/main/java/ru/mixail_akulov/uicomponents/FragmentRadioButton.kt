package ru.mixail_akulov.uicomponents

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.BoringLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.mixail_akulov.uicomponents.databinding.FragmentEditTextBinding
import ru.mixail_akulov.uicomponents.databinding.FragmentRadioButtonBinding
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class FragmentRadioButton : Fragment() {

    private var binding: FragmentRadioButtonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRadioButtonBinding.inflate(inflater, container, false)

        binding?.getRandomImageButton?.setOnClickListener {
            onGetRandomImagePressed()
        }

        // срабатывает при нажатии на кнопку выбора
        binding?.keywordRadioGroup?.setOnCheckedChangeListener { _, checkedId ->
            onGetRandomImagePressed()
        }

        return binding?.root
    }

    private fun onGetRandomImagePressed(): Boolean {
        val checkedId = binding?.keywordRadioGroup?.checkedRadioButtonId  // берем выбранную кнопку
        val keyword = checkedId?.let { binding?.keywordRadioGroup?.findViewById<RadioButton>(it)?.text.toString() } // берем ее название
        val encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.name()) // и передаем в сервис

        binding?.progressBar?.visibility = View.VISIBLE
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600?$encodedKeyword")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    binding?.progressBar?.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(resource: Drawable?,model: Any?,target: Target<Drawable>?,dataSource: DataSource?,isFirstResource: Boolean): Boolean {
                    binding?.progressBar?.visibility = View.GONE
                    return false
                }

            })
            .placeholder(R.drawable.wait)
            .into(binding?.testImageView!!)

        return false
    }
}
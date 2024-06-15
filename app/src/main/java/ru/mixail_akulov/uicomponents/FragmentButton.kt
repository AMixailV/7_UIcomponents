package ru.mixail_akulov.uicomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.mixail_akulov.uicomponents.databinding.FragmentButtonBinding
import ru.mixail_akulov.uicomponents.databinding.FragmentImageViewBinding
import kotlin.random.Random

class FragmentButton : Fragment() {

    private var binding: FragmentButtonBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentButtonBinding.inflate(inflater, container, false)

        binding?.getRandomImageButton?.setOnClickListener{ onGetRandomImagePressed() }

        binding?.getRandomImageButton?.setOnLongClickListener { showToastWithRandomNumber() }

        return binding?.root
    }

    private fun onGetRandomImagePressed() {
        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.wait)
            .into(binding?.testImageView!!)

    }

    private fun showToastWithRandomNumber(): Boolean {
        val number = Random.nextInt(100)
        val message = "${getString(R.string.random_number)} $number"
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

        return true
    }

}
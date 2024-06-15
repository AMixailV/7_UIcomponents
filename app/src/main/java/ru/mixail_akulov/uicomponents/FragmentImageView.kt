package ru.mixail_akulov.uicomponents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import ru.mixail_akulov.uicomponents.databinding.FragmentImageViewBinding

class FragmentImageView : Fragment(), View.OnClickListener {

    private var binding: FragmentImageViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentImageViewBinding.inflate(inflater, container, false)

        binding?.testImageView?.setBackgroundResource(R.drawable.background_shape)
        binding?.testImageView?.setImageResource(R.drawable.test_image)

        binding?.newImageView?.setOnClickListener(this)

        return binding?.root
    }

    override fun onClick(v: View?) {

        binding?.testImageView?.layoutParams?.width = resources.getDimensionPixelSize(R.dimen.image_width)
        binding?.testImageView?.layoutParams?.height = resources.getDimensionPixelSize(R.dimen.image_height)

        Glide.with(this)
            .load("https://source.unsplash.com/random/800x600")
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .placeholder(R.drawable.test_image)
            .into(binding?.testImageView!!)
    }
}
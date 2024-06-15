package ru.mixail_akulov.uicomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ru.mixail_akulov.uicomponents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.fragmentTextView?.setOnClickListener(this)
        binding?.fragmentImageView?.setOnClickListener(this)
        binding?.fragmentButton?.setOnClickListener(this)
        binding?.fragmentEditText?.setOnClickListener(this)
        binding?.fragmentRadioGroup?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.fragment_text_view ->
                supportFragmentManager.beginTransaction().replace(R.id.content, FragmentTextView())
                    .commit()

            R.id.fragment_image_view ->
                supportFragmentManager.beginTransaction().replace(R.id.content, FragmentImageView())
                    .commit()

            R.id.fragment_button ->
                supportFragmentManager.beginTransaction().replace(R.id.content, FragmentButton())
                    .commit()

            R.id.fragment_edit_text ->
                supportFragmentManager.beginTransaction().replace(R.id.content, FragmentEditText())
                    .commit()

            R.id.fragment_radio_group ->
                supportFragmentManager.beginTransaction().replace(R.id.content, FragmentRadioButton())
                    .commit()
        }
    }
}
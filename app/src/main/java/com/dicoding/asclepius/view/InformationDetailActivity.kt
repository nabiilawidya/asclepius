package com.dicoding.asclepius.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.databinding.ActivityInformationDetailBinding

class InformationDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        @Suppress("DEPRECATION")
        val data = intent.getParcelableExtra<ArticlesItem>(DATA)

        if (data != null) {
            binding.newsTitle.text = data.title
            binding.newsUrl.text = data.url
            binding.newsDescription.text = data.description
            Glide.with(this)
                .load(data.urlToImage)
                .into(binding.newsImage)
        }

        findViewById<ImageView>(R.id.arrow_back).setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        private const val DATA = "data_detail"
    }
}
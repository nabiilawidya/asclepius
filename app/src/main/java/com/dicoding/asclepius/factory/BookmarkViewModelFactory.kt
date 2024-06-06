package com.dicoding.asclepius.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.data.repository.BookmarkRepository
import com.dicoding.asclepius.di.Injection
import com.dicoding.asclepius.viewModel.BookmarkViewModel
import com.dicoding.asclepius.viewModel.ResultViewModel

class ViewModelFactory private constructor(private val bookmarkRepository: BookmarkRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(bookmarkRepository) as T
        } else {
            return ResultViewModel(bookmarkRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context))
        }.also { instance = it }
    }
}
package com.yvnzhdeh.filmsmercadon.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.yvnzhdeh.filmsmercadon.R
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.databinding.ActivityMainBinding
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import com.yvnzhdeh.filmsmercadon.ui.fragments.DetailFilmFragment
import com.yvnzhdeh.filmsmercadon.ui.fragments.ListFilmsFragment
import com.yvnzhdeh.filmsmercadon.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.HiltAndroidApp
import java.util.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var transition = supportFragmentManager.beginTransaction()

    @Inject
    private var filmRepository: FilmRepository = FilmRepository()

    @Inject
    private var getFilmsUseCase: GetFilmsUseCase = GetFilmsUseCase(filmRepository)

    @Inject
    private var viewModelfactory: MainActivityViewModel.Factory = MainActivityViewModel.Factory(getFilmsUseCase)

    private val viewModel: MainActivityViewModel by viewModels { viewModelfactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transition.add(binding.flContainer.id, ListFilmsFragment(),"ListFilmsFragment").commit()

        viewModel.itemListFilmsClicked.observe(this) {
            transition = supportFragmentManager.beginTransaction()
            transition.replace(binding.flContainer.id, DetailFilmFragment(),"DetailFilmFragment").addToBackStack(null).commit()
        }
    }
}
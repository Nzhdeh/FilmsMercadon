package com.yvnzhdeh.filmsmercadon.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.data.repositories.GetFilmsRoomRepository
import com.yvnzhdeh.filmsmercadon.data.repositories.SaveListFilmsInRoomRepository
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsRoomUseCase
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.data.usecases.SaveListFilmsInRoomUseCase
import com.yvnzhdeh.filmsmercadon.databinding.ActivityMainBinding
import com.yvnzhdeh.filmsmercadon.ui.fragments.DetailFilmFragment
import com.yvnzhdeh.filmsmercadon.ui.fragments.ListFilmsFragment
import com.yvnzhdeh.filmsmercadon.ui.viewmodels.MainActivityViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var transition = supportFragmentManager.beginTransaction()

    @Inject
    private var filmRepository: FilmRepository = FilmRepository()

    @Inject
    private var getFilmsUseCase: GetFilmsUseCase = GetFilmsUseCase(filmRepository)

    @Inject
    private var saveListFilmsInRoomRepository: SaveListFilmsInRoomRepository = SaveListFilmsInRoomRepository()

    @Inject
    private var saveListFilmsInRoomUseCase: SaveListFilmsInRoomUseCase = SaveListFilmsInRoomUseCase(saveListFilmsInRoomRepository)

    @Inject
    private var getFilmsRoomRepository: GetFilmsRoomRepository = GetFilmsRoomRepository()

    @Inject
    private var getFilmsRoomUseCase: GetFilmsRoomUseCase = GetFilmsRoomUseCase(getFilmsRoomRepository)

    @Inject
    private var viewModelfactory: MainActivityViewModel.Factory = MainActivityViewModel.Factory(getFilmsUseCase, saveListFilmsInRoomUseCase, getFilmsRoomUseCase)

    private val viewModel: MainActivityViewModel by viewModels { viewModelfactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.context = this
        transition.add(binding.flContainer.id, ListFilmsFragment(),"FragemntMenu").commit()

        viewModel.itemListFilmsClicked.observe(this) {
            transition = supportFragmentManager.beginTransaction()
            transition.replace(binding.flContainer.id, DetailFilmFragment(),"DetailFilmFragment").addToBackStack(null).commit()
        }
    }
}
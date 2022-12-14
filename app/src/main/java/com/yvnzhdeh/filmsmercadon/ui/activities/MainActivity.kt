package com.yvnzhdeh.filmsmercadon.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
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

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainActivityViewModel.Factory(getFilmsUseCase, saveListFilmsInRoomUseCase, getFilmsRoomUseCase))[MainActivityViewModel::class.java]
        viewModel.context = this

        if (viewModel.isFirstExecution)
        {
            transition.add(binding.flContainer.id, ListFilmsFragment(),"ListFilmsFragment").commitAllowingStateLoss()
            //transition.add(binding.flContainer.id, DetailFilmFragment(),"DetailFilmFragment").commitAllowingStateLoss()//para poder navegar a los detalles ya que el onClick no me funciona porque el observer que tengo m??s abajo no detecta el cambio de LiveData
            viewModel.isFirstExecution = false
        }


        viewModel.itemListFilmsClicked.observe(this) {
            transition = supportFragmentManager.beginTransaction()
            transition.replace(binding.flContainer.id, DetailFilmFragment(),"DetailFilmFragment").addToBackStack("ListFilmsFragment").commitAllowingStateLoss()
        }
    }
}
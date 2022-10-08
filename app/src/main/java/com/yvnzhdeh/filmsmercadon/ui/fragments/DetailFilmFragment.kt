package com.yvnzhdeh.filmsmercadon.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.data.repositories.GetFilmsRoomRepository
import com.yvnzhdeh.filmsmercadon.data.repositories.SaveListFilmsInRoomRepository
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsRoomUseCase
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.data.usecases.SaveListFilmsInRoomUseCase
import com.yvnzhdeh.filmsmercadon.databinding.FragmentDetailFilmBinding
import com.yvnzhdeh.filmsmercadon.ui.viewmodels.MainActivityViewModel
import javax.inject.Inject

class DetailFilmFragment : Fragment() {

    private lateinit var _binding: FragmentDetailFilmBinding
    private val binding get() = _binding

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailFilmBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(requireActivity(), MainActivityViewModel.Factory(getFilmsUseCase, saveListFilmsInRoomUseCase, getFilmsRoomUseCase))[MainActivityViewModel::class.java]
        viewModel.context = requireContext()
        val itemSelected = viewModel.itemListFilmsClicked.value

        Picasso.with(context).load(itemSelected?.image).into(binding.ivItemClicked)
        binding.etTitleItemClicked.setText(itemSelected?.title)
        binding.etDirectorItemClicked.setText(itemSelected?.director)
        binding.etDateItemClicked.setText(itemSelected?.releaseDate)
        binding.etOriginalTitleItemClicked.setText(itemSelected?.originalTitle)
        binding.etOriginalTitleRomanisedItemClicked.setText(itemSelected?.originalTitleRomanised)

        binding.btnUpdate.setOnClickListener {
            itemSelected?.title = binding.etTitleItemClicked.text.toString()
            itemSelected?.director = binding.etDirectorItemClicked.text.toString()
            itemSelected?.releaseDate = binding.etDateItemClicked.text.toString()
            itemSelected?.originalTitle = binding.etOriginalTitleItemClicked.text.toString()
            itemSelected?.originalTitleRomanised = binding.etOriginalTitleRomanisedItemClicked.text.toString()

            if (itemSelected != null) {
                try {
                    viewModel.updateFilm(itemSelected)
                    Toast.makeText(requireContext(),"Se ha actualizado correctamente",Toast.LENGTH_SHORT).show()
                }
                catch (e: Exception)
                {
                    Toast.makeText(requireContext(),"Ha habido un error",Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }
}
package com.yvnzhdeh.filmsmercadon.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.databinding.FragmentDetailFilmBinding
import com.yvnzhdeh.filmsmercadon.ui.viewmodels.MainActivityViewModel
import javax.inject.Inject

class DetailFilmFragment : Fragment() {

    private lateinit var _binding: FragmentDetailFilmBinding
    private val binding get() = _binding!!

    @Inject
    private var filmRepository: FilmRepository = FilmRepository()

    @Inject
    private var getFilmsUseCase: GetFilmsUseCase = GetFilmsUseCase(filmRepository)

    @Inject
    private var viewModelfactory: MainActivityViewModel.Factory = MainActivityViewModel.Factory(getFilmsUseCase)

    private val viewModel: MainActivityViewModel by viewModels { viewModelfactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailFilmBinding.inflate(inflater, container, false)

        val itemSelected = viewModel.itemListFilmsClicked.value

        Picasso.with(context).load(itemSelected?.image).into(binding.ivItemClicked)
        binding.etTitleItemClicked.setText(itemSelected?.title)
        binding.etDirectorItemClicked.setText(itemSelected?.director)
        binding.etDateItemClicked.setText(itemSelected?.releaseDate)
        binding.etOriginalTitleItemClicked.setText(itemSelected?.originalTitle)
        binding.etOriginalTitleRomanisedItemClicked.setText(itemSelected?.originalTitleRomanised)

        val hghghghg = binding.etTitleItemClicked.text

        binding.btnUpdate.setOnClickListener {
            //TODO update
        }

        return binding.root
    }
}
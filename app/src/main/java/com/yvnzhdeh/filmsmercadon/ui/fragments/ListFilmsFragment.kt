package com.yvnzhdeh.filmsmercadon.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yvnzhdeh.filmsmercadon.R
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.databinding.FragmentListFilmsBinding
import com.yvnzhdeh.filmsmercadon.model.domain.Film
import com.yvnzhdeh.filmsmercadon.ui.adapters.ListFilmsAdapter
import com.yvnzhdeh.filmsmercadon.ui.viewmodels.MainActivityViewModel
import javax.inject.Inject


class ListFilmsFragment : Fragment() {

    private lateinit var _binding: FragmentListFilmsBinding
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
        _binding = FragmentListFilmsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listsAllFilms.observe(requireActivity()){
            if (!it.isNullOrEmpty())
            {
                binding.progressBar.visibility = View.GONE
            }
            val recyclerView: RecyclerView = binding.rvListFilms
            recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            recyclerView.adapter = ListFilmsAdapter(it) { film ->
                viewModel.changeItemClicked(film)
            }
        }
    }
}
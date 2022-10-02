package com.yvnzhdeh.filmsmercadon.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yvnzhdeh.filmsmercadon.R
import com.yvnzhdeh.filmsmercadon.data.repositories.FilmRepository
import com.yvnzhdeh.filmsmercadon.data.repositories.GetFilmsRoomRepository
import com.yvnzhdeh.filmsmercadon.data.repositories.SaveListFilmsInRoomRepository
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsRoomUseCase
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.data.usecases.SaveListFilmsInRoomUseCase
import com.yvnzhdeh.filmsmercadon.databinding.FragmentListFilmsBinding
import com.yvnzhdeh.filmsmercadon.ui.adapters.ListFilmsAdapter
import com.yvnzhdeh.filmsmercadon.ui.viewmodels.MainActivityViewModel
import javax.inject.Inject


class ListFilmsFragment : Fragment() {

    private lateinit var _binding: FragmentListFilmsBinding
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

    @Inject
    private var viewModelfactory: MainActivityViewModel.Factory = MainActivityViewModel.Factory(getFilmsUseCase, saveListFilmsInRoomUseCase, getFilmsRoomUseCase)

    private val viewModel: MainActivityViewModel by viewModels { viewModelfactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListFilmsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.context = requireContext()
        viewModel.getFilmsRoom()
        viewModel.listsAllFilmsRoom.observe(viewLifecycleOwner){
            if (!it.isNullOrEmpty())
            {
                binding.progressBar.visibility = View.GONE
                binding.ivMenuOptions.visibility = View.VISIBLE
                binding.tvError.visibility = View.GONE
            }
            else{
                binding.tvError.visibility = View.VISIBLE
            }
            val recyclerView: RecyclerView = binding.rvListFilms
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = ListFilmsAdapter(it) { film ->
                viewModel.changeItemClicked(film)
            }
        }

        binding.ivMenuOptions.setOnClickListener {
            showMenu(it)
        }
    }

    private fun showMenu(v: View){
        val popMenu = PopupMenu(requireContext(), v)
        popMenu.inflate(R.menu.menu_options)
        popMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.delete -> {
                    viewModel.deleteBBDD()
                    true
                }
                R.id.update -> {
                    viewModel.updateBBDD()
                    true
                }
                else -> {
                    false
                }
            }
        }
        popMenu.show()
    }
}
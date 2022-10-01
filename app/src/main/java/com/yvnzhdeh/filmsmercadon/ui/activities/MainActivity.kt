package com.yvnzhdeh.filmsmercadon.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.yvnzhdeh.filmsmercadon.R
import com.yvnzhdeh.filmsmercadon.data.usecases.GetFilmsUseCase
import com.yvnzhdeh.filmsmercadon.databinding.ActivityMainBinding
import com.yvnzhdeh.filmsmercadon.ui.fragments.ListFilmsFragment
import com.yvnzhdeh.filmsmercadon.ui.viewmodels.MainActivityViewModel
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModelfactory: MainActivityViewModel.Factory

    private var transition = supportFragmentManager.beginTransaction()

    private val viewModel: MainActivityViewModel by viewModels { viewModelfactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transition.add(R.id.flContainer, ListFilmsFragment(),"FragemntMenu").commit()
    }
}
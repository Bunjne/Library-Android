package com.example.practiceafterwat.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.practiceafterwat.data.viewModel.MazeShowViewModel
import com.example.practiceafterwat.databinding.FragmentHomeBinding
import com.example.practiceafterwat.util.Resource
import com.example.practiceafterwat.util.hide
import com.example.practiceafterwat.util.show
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MazeShowViewModel by viewModel()

    private var showListAdapter: ShowListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initView() {
        showListAdapter = ShowListAdapter { showId ->
            Toast.makeText(context, showId, Toast.LENGTH_LONG)
        }

        binding.recyclerView.adapter = showListAdapter
        binding.srlContainer.setOnRefreshListener {
            viewModel.getShowList()
        }
        binding.retryButton.setOnClickListener {
            viewModel.getShowList()
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.showListState.collect { resource ->
                    when (resource.status) {
                        Resource.Status.SUCCESS -> {
                            binding.srlContainer.hide()
                            binding.errorLayout.hide()
                            showListAdapter?.submitList(resource.data)
                        }
                        Resource.Status.LOADING -> {
                            binding.srlContainer.show()
                            binding.errorLayout.hide()
                        }
                        Resource.Status.ERROR -> {
                            binding.srlContainer.hide()
                            binding.errorLayout.show()
                            binding.errorMsg.text = resource.message
                        }
                    }
                }
            }
        }

        viewModel.getShowList()
    }
}
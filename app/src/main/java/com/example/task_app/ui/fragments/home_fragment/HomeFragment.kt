package com.example.task_app.ui.fragments.home_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task_app.utils.navigateFragment
import com.example.task_app.utils.onSingleClick
import com.example.task_app.R
import com.example.task_app.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            this?.callListButton?.onSingleClick {
                findNavController().navigateFragment(R.id.action_homeFragment_to_callListFragment)
            }
            this?.buyList?.onSingleClick {
                findNavController().navigateFragment(R.id.action_homeFragment_to_buyListFragment)
            }
            this?.sellList?.onSingleClick {
                findNavController().navigateFragment(R.id.action_homeFragment_to_sellListFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
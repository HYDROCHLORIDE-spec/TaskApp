package com.example.task_app.ui.fragments.sell_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_app.databinding.FragmentSellListBinding
import com.example.task_app.ui.activities.main.viewmodel.MainViewModel
import com.example.task_app.ui.fragments.sell_fragment.adapter.SellAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellListFragment : Fragment() {

    private var _binding: FragmentSellListBinding? = null
    private val binding get() = _binding
    private val mViewModel by viewModels<MainViewModel>()
    private var adapter: SellAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View ?{
        _binding = FragmentSellListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getToSellData()
        binding?.recOfSell?.apply {
            layoutManager = LinearLayoutManager(context)
        }
        mViewModel.toSellResponseLiveData.observe(viewLifecycleOwner) {
            adapter = SellAdapter(it, context)
            binding?.recOfSell?.adapter = adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
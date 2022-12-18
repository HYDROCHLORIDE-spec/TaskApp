package com.example.task_app.ui.fragments.buy_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_app.ui.fragments.buy_list.adapter.BuyAdapter
import com.example.task_app.ui.activities.main.viewmodel.MainViewModel
import com.example.task_app.data.network.ApiResultSealed
import com.example.task_app.utils.gone
import com.example.task_app.utils.showToast
import com.example.task_app.utils.visible
import com.example.task_app.databinding.FragmentBuyListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyListFragment : Fragment() {
    private var _binding: FragmentBuyListBinding? = null
    private val binding get() = _binding
    private val mViewModel by viewModels<MainViewModel>()
    private var adapter: BuyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentBuyListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getBuyData()
        with(binding) {
            this?.recOfBuy?.apply {
                layoutManager = LinearLayoutManager(context)
            }
        }
        initResult()
    }

    private fun initResult() {
        mViewModel.buyResponseLivedata.observe(viewLifecycleOwner) {
            binding?.toBuyProgressbar?.gone()
            when (it) {
                is ApiResultSealed.Success -> {
                    adapter = BuyAdapter(it.data, context)
                    binding?.recOfBuy?.adapter = adapter
                }
                is ApiResultSealed.Error -> {
                    context?.showToast(it.message.toString())
                }
                is ApiResultSealed.Loading -> {
                    binding?.toBuyProgressbar?.visible()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
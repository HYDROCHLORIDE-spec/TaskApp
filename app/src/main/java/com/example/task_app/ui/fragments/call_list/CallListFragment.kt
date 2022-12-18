package com.example.task_app.ui.fragments.call_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_app.ui.fragments.call_list.adapter.CallAdapter
import com.example.task_app.ui.activities.main.viewmodel.MainViewModel
import com.example.task_app.data.network.ApiResultSealed
import com.example.task_app.utils.gone
import com.example.task_app.utils.showToast
import com.example.task_app.utils.visible
import com.example.task_app.databinding.FragmentCallListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallListFragment : Fragment() {
    private var _binding: FragmentCallListBinding? = null
    private val binding get() = _binding
    private val mViewModel by viewModels<MainViewModel>()
    private var adapter: CallAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCallListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.getCallData()
        with(binding) {
            this?.recOfCall?.apply {
                layoutManager = LinearLayoutManager(context)
            }
        }
        initResult()
    }

    private fun initResult() {
        mViewModel.callResponseLivedata.observe(viewLifecycleOwner) {
            binding?.toCallProgressbar?.gone()
            when (it) {
                is ApiResultSealed.Success -> {
                    adapter = CallAdapter(it.data, context)
                    binding?.recOfCall?.adapter = adapter
                }
                is ApiResultSealed.Error -> {
                    context?.showToast(it.message.toString())
                }
                is ApiResultSealed.Loading -> {
                    binding?.toCallProgressbar?.visible()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package com.example.task_app.ui.fragments.call_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_app.R
import com.example.task_app.databinding.CallListItemBinding
import com.example.task_app.ui.fragments.call_list.model.ToCallResponseItem


class CallAdapter(private val mList: List<ToCallResponseItem>?, val context: Context?) :
    RecyclerView.Adapter<CallAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CallListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            name.text = buildString {
                append(context?.getString(R.string.name))
                append(mList?.get(position)?.name)
            }
            number.text =
                buildString {
                    append(context?.getString(R.string.number))
                    append(mList?.get(position)?.number.toString())
                }
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class ViewHolder(val binding: CallListItemBinding) : RecyclerView.ViewHolder(binding.root)
}

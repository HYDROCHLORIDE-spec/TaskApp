package com.example.task_app.ui.fragments.buy_list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.task_app.R
import com.example.task_app.ui.fragments.buy_list.model.ToBuyResponseItem
import com.example.task_app.databinding.BuyListItemBinding


class BuyAdapter(private val mList: List<ToBuyResponseItem>?, val context: Context?) :
    RecyclerView.Adapter<BuyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BuyListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            name.text = buildString {
                append(context?.getString(R.string.name))
                append(mList?.get(position)?.name)
            }
            price.text =
                buildString {
                    append(context?.getString(R.string.price))
                    append(mList?.get(position)?.price.toString())
                }
            quantity.text =
                buildString {
                    append(context?.getString(R.string.quantity))
                    append(mList?.get(position)?.quantity.toString())
                }
        }
    }

    override fun getItemCount(): Int {
        return mList?.size ?: 0
    }

    class ViewHolder(val binding: BuyListItemBinding) : RecyclerView.ViewHolder(binding.root)
}

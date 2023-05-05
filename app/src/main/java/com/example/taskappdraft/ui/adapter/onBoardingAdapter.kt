package com.example.taskappdraft.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskappdraft.databinding.ItemBoardBinding
import com.example.taskappdraft.ui.OnBoard.utils.loadImage
import com.example.taskappdraft.ui.task.model.onBoardModel
import kotlin.reflect.KFunction1

class onBoardingAdapter(private val onClick: KFunction1<onBoardModel, Unit>):
    RecyclerView.Adapter<onBoardingAdapter.onBoardingViewHolder>() {

    val data = arrayListOf(
        onBoardModel(title = "Title first", desc = "Description first",  "https://cdn-icons-png.flaticon.com/512/2098/2098402.png"),
        onBoardModel(title = "Title second", desc = "Description second", "https://cdn-icons-png.flaticon.com/512/2098/2098402.png"),
        onBoardModel(title = "Title third", desc = "Description third",  "https://cdn-icons-png.flaticon.com/512/2098/2098402.png"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): onBoardingViewHolder {
        return onBoardingViewHolder(ItemBoardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: onBoardingViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class onBoardingViewHolder(private val binding: ItemBoardBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoardModel: onBoardModel) {
            binding.btnItem.setOnClickListener(){
                onClick(onBoardModel)

            }
            binding.tvItemDesc.text = onBoardModel.desc
            binding.tvItemTitle.text = onBoardModel.title

            binding.imgItem.loadImage(onBoardModel.image)
        }
    }
}
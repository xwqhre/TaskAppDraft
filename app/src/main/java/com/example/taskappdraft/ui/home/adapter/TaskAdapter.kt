package com.example.taskappdraft.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskappdraft.databinding.ItemTaskBinding
import com.example.taskappdraft.model.TaskModel

class TaskAdapter(
    private val onLongClick: (TaskModel) -> Unit,
    private val onClick: (TaskModel) -> Unit
) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val data = arrayListOf<TaskModel>()

    fun addTasks(taskModel: List<TaskModel>) {
        data.clear()
        data.addAll(taskModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data.get(position))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.itmTitle.text = taskModel.title
            binding.itmDesc.text = taskModel.desc

            itemView.setOnLongClickListener {
                onLongClick(taskModel)
                false
            }

            itemView.setOnClickListener {
                onClick(taskModel)
            }
        }
    }
}

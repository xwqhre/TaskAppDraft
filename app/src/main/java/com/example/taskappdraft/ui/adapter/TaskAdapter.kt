package com.example.taskappdraft.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskappdraft.databinding.ItemTaskBinding
import com.example.taskappdraft.ui.task.model.TaskModel

class TaskAdapter:RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private val data = arrayListOf<TaskModel>()

    fun addTasks (taskModel: List<TaskModel>){
        data.clear()
        data.addAll(taskModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data.get(position))
    }
    override fun getItemCount(): Int {
        return data.size
    }

    class TaskViewHolder(private val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(taskModel: TaskModel) {
            binding.itmTitle.text = taskModel.title
            binding.itmDesc.text = taskModel.desc
        }
    }
}
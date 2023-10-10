package com.example.taskappdraft.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.example.taskappdraft.App
import com.example.taskappdraft.databinding.FragmentTaskBinding
import com.example.taskappdraft.model.TaskModel

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding
    private lateinit var navArgs: TaskFragmentArgs
    private var taskModel: TaskModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            navArgs = TaskFragmentArgs.fromBundle(it)
            taskModel = navArgs.task
        }
        
        if (taskModel != null){
            binding.etTitle.setText(taskModel!!.title)
            binding.etDesc.setText(taskModel!!.desc)
            binding.btnTask.text = "Update"
        }else{
            binding.btnTask.text = "Save"
        }
        save()
    }

    private fun save() {
        binding.btnTask.setOnClickListener() {
            val data = TaskModel(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
            
            if (data.title!!.isEmpty() || data.desc!!.isEmpty()){
                Toast.makeText(requireContext(), "Title and desc can not be empty",
                    Toast.LENGTH_SHORT).show()
            }

            if (taskModel != null){
                taskModel!!.title = data.title
                taskModel!!.desc = data.desc
                App.dp.taskDao().update(taskModel!!)
            }else{
                taskModel = TaskModel(title = data.title, desc = data.desc)
                App.dp.taskDao().insert(taskModel!!)
            }
            findNavController().navigateUp()
        }
    }

    companion object {
        const val TASK_REQUEST = "task.requestKey"
        const val TASK_KEY = "task.key"
    }
}
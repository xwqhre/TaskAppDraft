package com.example.taskappdraft.ui.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.example.taskappdraft.App
import com.example.taskappdraft.databinding.FragmentTaskBinding
import com.example.taskappdraft.ui.task.model.TaskModel

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnTask.setOnClickListener(){
            save()
        }
    }
    private fun save(){
        val data = TaskModel(
            title = binding.etTitle.text.toString(),
            desc = binding.etDesc.text.toString()
        )
        App.dp.taskDao().insert(data)
        findNavController().navigateUp()    }

    companion object{
        const val TASK_REQUEST = "task.requestKey"
        const val TASK_KEY = "task.key"
    }
}
package com.example.taskappdraft.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskappdraft.App
import com.example.taskappdraft.R
import com.example.taskappdraft.databinding.FragmentHomeBinding
import com.example.taskappdraft.ui.home.adapter.TaskAdapter
import com.example.taskappdraft.model.TaskModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = TaskAdapter(this::onLongClick, this::onClick)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root ?: binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.adapter = adapter
        val list = App.dp.taskDao().getAll()
        adapter.addTasks(list)

        binding.fabHome.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }
    private fun onLongClick(taskModel: TaskModel) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Вы точно хотите удалить?")
        alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }
        })

        alertDialog.setPositiveButton("Yes",object : DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                App.dp.taskDao().delete(taskModel)
                setData()
            }
        })
        alertDialog.create().show()
    }
    private fun setData() {
        val list = App.dp.taskDao().getAll()
        adapter.addTasks(list)
    }

    private fun onClick(taskModel: TaskModel){
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment(taskModel))
    }

}
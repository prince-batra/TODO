package com.secureapps.todo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.secureapps.entity.Task
import com.secureapps.presenter.SingleTaskViewData
import com.secureapps.todo.databinding.SingleEventLayoutBinding

class TasksListAdapter(
    private val inflater: LayoutInflater
) : RecyclerView.Adapter<TaskListViewHolder>() {

    private var resultsList: List<SingleTaskViewData> = ArrayList()

    fun updateList(newList: List<SingleTaskViewData>) {
        notifyItemRangeRemoved(0, resultsList.size)
        resultsList = newList
        notifyItemRangeInserted(0, newList.size)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        return TaskListViewHolder(SingleEventLayoutBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return resultsList.size
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.bind(resultsList.get(position))
    }

}
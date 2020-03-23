package com.secureapps.todo.adapters

import androidx.recyclerview.widget.RecyclerView
import com.secureapps.entity.Task
import com.secureapps.presenter.SingleTaskViewData
import com.secureapps.todo.databinding.SingleEventLayoutBinding

class TaskListViewHolder (private val itemBinder: SingleEventLayoutBinding) : RecyclerView.ViewHolder(itemBinder.root) {

    fun bind(task: SingleTaskViewData) {
        itemBinder.singleTask = task;
    }

}
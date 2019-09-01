package com.mvvm.cvapplication.projectHistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.cvapplication.R
import com.mvvm.cvapplication.cvdetail.model.Projects
import com.mvvm.cvapplication.util.convertYYYYMMDD_TO_DDMMMYYYY
import kotlinx.android.synthetic.main.item_project.view.*

class ProjectListAdapter(private var projectList: List<Projects>) :
    RecyclerView.Adapter<ProjectListAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_project, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindView(projectList[position])
    }

    fun updateList(projectList: List<Projects>){
        this.projectList = projectList
    }

    class ListViewHolder(var viewHolder: View) : RecyclerView.ViewHolder(viewHolder) {

        fun bindView(project: Projects) {
            viewHolder.txtProjectTitle.text = project.name
            viewHolder.txtProjectRole.text = "As ${project.role?.capitalize()}"
            viewHolder.txtProjectDuration.text = "${project.start?.convertYYYYMMDD_TO_DDMMMYYYY()} To ${project.end?.convertYYYYMMDD_TO_DDMMMYYYY()}"
        }

    }
}
package com.mvvm.cvapplication.projectHistory

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm.cvapplication.R
import com.mvvm.cvapplication.cvdetail.model.Projects
import com.mvvm.cvapplication.util.AppConstants
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_project_history.*
import javax.inject.Inject

class ProjectHistoryActivity : DaggerAppCompatActivity() {

    //Holds index of Views in ViewFlipper class
    private val DISPLAY_LIST = 0
    private val DISPLAY_ERROR = 1

    lateinit var viewModel: ProjectHistoryViewModel

    private lateinit var projectListAdapter: ProjectListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_project_history)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ProjectHistoryViewModel::class.java)

        setTitle(R.string.project_history_title)

        initView()

        intent?.let {
            viewModel.projectListLiveData.value =
                it.getParcelableArrayListExtra<Projects>(AppConstants.PRAM_DATA)
        }

        initViewModel()

    }

    /**
     * Init view
     * */
    private fun initView() {
        projectListAdapter = ProjectListAdapter(ArrayList())
        rvProjectHistory.layoutManager = LinearLayoutManager(this)
        rvProjectHistory.adapter = projectListAdapter
    }

    /**
     * Init view model
     * */
    private fun initViewModel() {
        viewModel.projectListLiveData.observe(this, Observer {
            showList(it)
        })
    }

    /**
     * update adapter and show list or error depending on list size
     * */
    private fun showList(updatedList: List<Projects>) {
        if (updatedList.isEmpty()) {
            vfProjectHistory.displayedChild = DISPLAY_ERROR
        } else {
            vfProjectHistory.displayedChild = DISPLAY_LIST
            projectListAdapter.updateList(updatedList)
            projectListAdapter.notifyDataSetChanged()
        }
    }

}
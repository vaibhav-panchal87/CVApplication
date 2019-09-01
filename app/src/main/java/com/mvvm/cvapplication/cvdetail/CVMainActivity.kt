package com.mvvm.cvapplication.cvdetail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mvvm.cvapplication.R
import com.mvvm.cvapplication.cvdetail.model.CVModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_mycvdetail.*
import kotlinx.android.synthetic.main.layout_api_error.*
import javax.inject.Inject

class CVMainActivity : DaggerAppCompatActivity() {

    //Holds index of Views in ViewFlipper class
    private val SHOW_LOADING = 0
    private val SHOW_DATA = 1
    private val SHOW_ERROR = 2

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: CVDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mycvdetail)

        initView()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CVDetailViewModel::class.java)

        viewModel.loadCVData()

        initViewModel()
    }

    /**
     * Init view listeners
     * */
    private fun initView() {
        btnCVProjectHistory.setOnClickListener {
            viewModel.cvMutableLiveData.value?.let {
                //TODO Project screen
            }
        }

        btnLayoutError.setOnClickListener {
            viewModel.loadCVData()
        }
    }

    /**
     * Init view model
     * */
    private fun initViewModel() {
        viewModel.cvMutableLiveData.observe(this,
            Observer<CVModel> { observer ->
                viewModel.showProgressLiveData.value = false
                observer?.let {
                    vfCVDetail.displayedChild = SHOW_DATA
                    loadDataOnUI(it)
                }

            })

        viewModel.showProgressLiveData.observe(this, Observer {
            if (it)
                vfCVDetail.displayedChild = SHOW_LOADING
        })

        viewModel.showError.observe(this, Observer {
            if (it) {
                vfCVDetail.displayedChild = SHOW_ERROR
            }
        })
    }


    /**
     * Load Data in UI
     * */
    private fun loadDataOnUI(cvModel: CVModel) {

        txtCVFullName.text = "${cvModel.firstName} ${cvModel.lastName}"
        txtCVAddress.text = cvModel.address
        txtCVEmail.text = cvModel.email
        txtCVGender.text = cvModel.gender
        txtCVSkills.text = cvModel.skills
        txtCVSummery.text = cvModel.summary
        txtCVPhoneNumber.text = cvModel.phoneNumber
    }
}

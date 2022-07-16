package com.findingfalcone.feproblem1.ui.module.OutPutScreen

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.findingfalcone.feproblem1.R
import com.findingfalcone.feproblem1.databinding.ActivityFindingFalconeSolutionBinding
import com.findingfalcone.feproblem1.di.component.ActivityComponent
import com.findingfalcone.feproblem1.ui.base.BaseActivity

class FindingFalconeSolutionActivity : BaseActivity<FindFalconeSolutionModel>() {
    companion object {
        const val TAG = "FindingFalconeSolutionActivity"
    }

    private lateinit var binding: ActivityFindingFalconeSolutionBinding


    override fun showMessage(appCompatActivity: AppCompatActivity) {
        TODO("Not yet implemented")
    }

    override fun provideLayoutView(): View {
        binding = ActivityFindingFalconeSolutionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        val bundle: Bundle? = intent.extras
        val planet = bundle?.get("planet")

        if (planet == null) {
            binding.txtFoundQueen.text = R.string.str_success_message.toString()
            binding.txtPlanet.text = planet
        }else{
            binding.txtFoundQueen.text = R.string.str_failure_message.toString()
            binding.txtPlanet.text = R.string.str_planet_not_found.toString()
        }


    }

}
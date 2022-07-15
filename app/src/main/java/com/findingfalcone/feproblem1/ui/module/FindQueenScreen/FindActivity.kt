package com.findingfalcone.feproblem1.ui.module.FindQueenScreen

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.findingfalcone.domain.model.Vehicle
import com.findingfalcone.feproblem1.R
import com.findingfalcone.feproblem1.data.remote.response.VehicleApiResponse
import com.findingfalcone.feproblem1.databinding.ActivityFindBinding
import com.findingfalcone.feproblem1.di.component.ActivityComponent
import com.findingfalcone.feproblem1.ui.base.BaseActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class FindActivity : BaseActivity<FindViewModel>() {
    companion object {
        const val TAG = "FindFalconeActivity"
    }

    private lateinit var binding: ActivityFindBinding

    private lateinit var selectedVehicleOne: String
    private lateinit var selectedVehicleTwo: String
    private lateinit var selectedVehicleThree: String
    private lateinit var selectedVehicleFour: String
    private lateinit var planetsDistance: String
    private lateinit var vehicleSpeed: String
    private var selectedVehicleIndex: Int = 0
    private val vehicleList: ArrayList<VehicleApiResponse.VehicleResponseItem> = ArrayList()
    private val vehicleNameList: ArrayList<String> = ArrayList<String>()
    private val vehicleSpeedList: ArrayList<Int> = ArrayList<Int>()
    private val planetsDistanceList: ArrayList<Int> = ArrayList<Int>()


    override fun showMessage(appCompatActivity: AppCompatActivity) {
    }

    override fun provideLayoutView(): View {
        binding = ActivityFindBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)

    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.getPlanetsList()
        viewModel.getVehicleList()

        viewModel.planetsList.observe(this, Observer {
            it?.run {
                Log.e("planetList", it.toString())
                val planetAdapter = PlanetAdapter(context, R.layout.custom_spinner_item, it)
                binding.sprDestinationOne.setAdapter(planetAdapter)
                binding.sprDestinationTwo.setAdapter(planetAdapter)
                binding.sprDestinationThree.setAdapter(planetAdapter)
                binding.sprDestinationFour.setAdapter(planetAdapter)
                planetsDistanceList.clear()
                for (i in it) {
                    planetsDistanceList.add(i.distance)
                }
            }
        })

        viewModel.vehiclesList.observe(this, Observer {
            it?.run {
                vehicleNameList.clear()
                vehicleSpeedList.clear()
                vehicleList.clear()
                for (item in it) {
                    vehicleList.add(item)
                    val vehicleString = item.name + "\nNo:" + item.totalNo + ", Speed:" + item.speed +", Distance:"+ item.maxDistance
                    vehicleNameList.add(vehicleString)
                    vehicleSpeedList.add(item.speed)
                }
            }
        })


    }

    override fun setupView(savedInstanceState: Bundle?) {
        binding.lnrVehicleSelectionOne.setOnClickListener {
            openVehicleSelectionDestinationOneDialogue()
        }
        binding.lnrVehicleSelectionTwo.setOnClickListener {
            openVehicleSelectionDestinationTwoDialogue()
        }
        binding.lnrVehicleSelectionThree.setOnClickListener {
            openVehicleSelectionDestinationThreeDialogue()
        }
        binding.lnrVehicleSelectionFour.setOnClickListener {
            openVehicleSelectionDestinationFourDialogue()
        }

        binding.btnFindFalcon.setOnClickListener {

            val planetList: String = "${binding.sprDestinationOne.text},${binding.sprDestinationTwo.text},${binding.sprDestinationThree.text},${binding.sprDestinationFour.text}"
            val planetFinalList: List<String> = listOf(*planetList.split(",").toTypedArray())
            Log.i("ListOfPlanetSlected","$planetFinalList")

            val vehicleList: String = "${binding.txtSelectSpaceOne.text},${binding.txtVehicleSelectionTwo.text},${binding.txtVehicleSelectionThree.text},${binding.txtVehicleSelectionFour.text}"
            val vehicleFinalList: List<String> = listOf(*vehicleList.split(",").toTypedArray())
            Log.i("ListOfVehceleSlected","$vehicleFinalList")

            viewModel.getQueen(planetFinalList,vehicleFinalList)

        }
    }

    private fun openVehicleSelectionDestinationOneDialogue() {
        selectedVehicleOne = vehicleNameList[selectedVehicleIndex]
        var vehicle = vehicleList.get(selectedVehicleIndex)
        MaterialAlertDialogBuilder(this)
            .setTitle("List of Vehicles")
            .setSingleChoiceItems(vehicleNameList.toTypedArray() , selectedVehicleIndex) { dialog_, which ->
                selectedVehicleIndex = which
                selectedVehicleOne = vehicleNameList[which]
            }
            .setPositiveButton("Ok") { dialog, which ->
                Toast.makeText(this, "$selectedVehicleOne Selected", Toast.LENGTH_SHORT)
                    .show()
                binding.txtSelectSpaceOne.text = "${vehicle.name}"
                vehicle.isSelected = true

                // binding.ivVehicleSelectionOne.setImageResource(it.getImage())
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }


    private fun openVehicleSelectionDestinationTwoDialogue() {
        selectedVehicleTwo = vehicleNameList[selectedVehicleIndex]
        var vehicle = vehicleList.get(selectedVehicleIndex)
        MaterialAlertDialogBuilder(this)
            .setTitle("List of Vehicles")
            .setSingleChoiceItems(vehicleNameList.toTypedArray() , selectedVehicleIndex) { dialog_, which ->
                selectedVehicleIndex = which
                selectedVehicleTwo = vehicleNameList[which]
            }
            .setPositiveButton("Ok") { dialog, which ->
                Toast.makeText(this, "$selectedVehicleTwo Selected", Toast.LENGTH_SHORT)
                    .show()
                binding.txtVehicleSelectionTwo.text = "${vehicle.name}"
                // binding.ivVehicleSelectionOne.setImageResource(it.getImage())
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
    private fun openVehicleSelectionDestinationThreeDialogue() {
        selectedVehicleThree = vehicleNameList[selectedVehicleIndex]
        var vehicle = vehicleList.get(selectedVehicleIndex)
        MaterialAlertDialogBuilder(this)
            .setTitle("List of Vehicles")
            .setSingleChoiceItems(vehicleNameList.toTypedArray(), selectedVehicleIndex) { dialog_, which ->
                selectedVehicleIndex = which
                selectedVehicleThree = vehicleNameList[which]

            }
            .setPositiveButton("Ok") { dialog, which ->
                Toast.makeText(this, "$selectedVehicleThree Selected", Toast.LENGTH_SHORT)
                    .show()
                binding.txtVehicleSelectionThree.text = "${vehicle.name}"
                // binding.ivVehicleSelectionOne.setImageResource(it.getImage())
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
    private fun openVehicleSelectionDestinationFourDialogue() {
        selectedVehicleFour = vehicleNameList[selectedVehicleIndex]
        var vehicle = vehicleList.get(selectedVehicleIndex)
        MaterialAlertDialogBuilder(this)
            .setTitle("List of Vehicles")
            .setSingleChoiceItems(vehicleNameList.toTypedArray(), selectedVehicleIndex) { dialog_, which ->
                selectedVehicleIndex = which
                selectedVehicleFour = vehicleNameList[which]
            }
            .setPositiveButton("Ok") { dialog, which ->
                Toast.makeText(this, "$selectedVehicleFour Selected", Toast.LENGTH_SHORT)
                    .show()
                binding.txtVehicleSelectionFour.text = "${vehicle.name}"
                // binding.ivVehicleSelectionOne.setImageResource(it.getImage())
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu_reset, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btnNavReset -> {
                val intent = intent
                finish()
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
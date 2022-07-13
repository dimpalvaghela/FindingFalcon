package com.findingfalcone.feproblem1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var selectedVehicle: String
    private var selectedVehicleIndex: Int = 0
    private val spaceVehicles = arrayOf("SPACE POD","SPACE ROCKET","SPACE SHUTTLE","SPACE SHIP")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        val txtSelectSpace = findViewById<TextView>(R.id.txtSelectSpace)

        val languages = resources.getStringArray(R.array.planet_name)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_menu, languages)
        autocompleteTV.setAdapter(arrayAdapter)


        txtSelectSpace.setOnClickListener {
            openVehicleSelectionDialogue()
        }

    }


    private fun openVehicleSelectionDialogue() {
        selectedVehicle = spaceVehicles[selectedVehicleIndex]
        MaterialAlertDialogBuilder(this)
            .setTitle("List of Vehicles")
            .setSingleChoiceItems(spaceVehicles, selectedVehicleIndex) { dialog_, which ->
                selectedVehicleIndex = which
                selectedVehicle = spaceVehicles[which]
            }
            .setPositiveButton("Ok") { dialog, which ->
                Toast.makeText(this, "$selectedVehicle Selected", Toast.LENGTH_SHORT)
                    .show()

//                txtSelectSpace.text = selectedVehicle.toString()
//                setImageDrawable(ContextCompat.getDrawable(R.drawable.space_icon))

            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }
}
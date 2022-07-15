package com.findingfalcone.feproblem1.ui.module.FindQueenScreen

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import com.example.findingfalcone.domain.model.Planet
import com.findingfalcone.feproblem1.R
import com.findingfalcone.feproblem1.data.remote.response.PlanetsApiResponse
import java.util.*


class PlanetAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    cities: ArrayList<PlanetsApiResponse.PlanetResponseItem>
) :
    ArrayAdapter<PlanetsApiResponse.PlanetResponseItem>(mContext, mLayoutResourceId, cities) {
    private val mPlanetsApi: MutableList<PlanetsApiResponse.PlanetResponseItem> = ArrayList(cities)
    private var allPlanetsApi: List<PlanetsApiResponse.PlanetResponseItem> = ArrayList(cities)
    override fun getCount(): Int {
        return mPlanetsApi.size
    }

    override fun getItem(position: Int): PlanetsApiResponse.PlanetResponseItem {
        return mPlanetsApi[position]
    }


    private class ItemHolder(row: View?) {
        val label: TextView
        // val img: ImageView

        init {
            label = row?.findViewById(R.id.autoCompleteTextView) as TextView
            //   img = row?.findViewById(R.id.img) as ImageView
        }
    }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
      var convertView = convertView
      if (convertView == null) {
          val inflater = (mContext as Activity).layoutInflater
          convertView = inflater.inflate(mLayoutResourceId, parent, false)
      }
      try {
          val planetsApi: PlanetsApiResponse.PlanetResponseItem = getItem(position)

          val cityAutoCompleteView = convertView!!.findViewById<View>(R.id.autoCompleteTextView) as TextView
          cityAutoCompleteView.text = planetsApi.name;

      } catch (e: Exception) {
          e.printStackTrace()
      }
      return convertView!!
  }



    override fun getFilter(): Filter {
        return object : Filter() {
            override fun convertResultToString(resultValue: Any): String {
                return (resultValue as PlanetsApiResponse.PlanetResponseItem).name
            }

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if (constraint != null) {
                    val mPlanetsApiSuggestion: MutableList<PlanetsApiResponse.PlanetResponseItem> =
                        ArrayList()
                    for (mPlanet in allPlanetsApi) {
                        if (mPlanet.name.lowercase(Locale.getDefault()).startsWith(
                                constraint.toString()
                                    .lowercase(Locale.getDefault())
                            )
                        ) {
                            mPlanetsApiSuggestion.add(mPlanet)
                        }
                    }
                    filterResults.values = mPlanetsApiSuggestion
                    filterResults.count = mPlanetsApiSuggestion.size
                }
                return filterResults
            }

            override fun publishResults(
                constraint: CharSequence?,
                results: FilterResults
            ) {
                mPlanetsApi.clear()
                if (results.count > 0) {
                    for (result in results.values as List<*>) {
                        if (result is PlanetsApiResponse.PlanetResponseItem) {
                            mPlanetsApi.add(result as PlanetsApiResponse.PlanetResponseItem)
                        }
                    }
                    notifyDataSetChanged()
                } else if (constraint == null) {
                    mPlanetsApi.addAll(allPlanetsApi)
                    notifyDataSetInvalidated()
                }
            }
        }
    }
}

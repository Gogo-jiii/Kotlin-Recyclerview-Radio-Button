package com.example.recyclerviewradiobutton

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_item.view.*

class MyAdapter(context: MainActivity, arrayList: ArrayList<ModelClass>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private val context: Context
    private val arrayList: ArrayList<ModelClass>
    private var isNewRadioButtonChecked = false
    private var lastCheckedPosition = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelClass = arrayList[position]
        holder.itemView.textView.text = modelClass.name

        if (isNewRadioButtonChecked) {
            holder.itemView.radioButton.isChecked = modelClass.isSelected
        } else {
            if (holder.adapterPosition == 0) {
                holder.itemView.radioButton.isChecked = true
                lastCheckedPosition = 0
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.radioButton.setOnClickListener { handleRadiobuttonChecks(adapterPosition) }
            itemView.rowitem.setOnClickListener {
                Toast.makeText(
                    context, arrayList[adapterPosition].toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun handleRadiobuttonChecks(adapterPosition: Int) {
        //Navin radiobutton check jhalay so junya button chi state false karaychi, navin button chi
        //state true karaychi ani lastCheckposition update karun current adapterposition thevaychi.
        isNewRadioButtonChecked = true
        arrayList[lastCheckedPosition].isSelected = false
        arrayList[adapterPosition].isSelected = true
        lastCheckedPosition = adapterPosition
        notifyDataSetChanged()
    }

    init {
        this.context = context
        this.arrayList = arrayList
    }
}
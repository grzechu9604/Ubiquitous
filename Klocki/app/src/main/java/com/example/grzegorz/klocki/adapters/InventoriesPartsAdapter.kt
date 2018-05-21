package com.example.grzegorz.klocki.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import com.example.grzegorz.klocki.DataTypes.InventoriesPart
import com.example.grzegorz.klocki.R

class InventoriesPartsAdapter(private val context: Context,
                              private val dataSource: ArrayList<InventoriesPart>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rowView = convertView

        if (rowView == null)
            rowView = inflater.inflate(R.layout.inventory_part_row, parent, false)

        val stringText = rowView!!.findViewById<TextView>(R.id.list_item_string)
        val incrementButton = rowView.findViewById<Button>(R.id.incrementCounter)
        val decrementButton = rowView.findViewById<Button>(R.id.decrementCounter)


        stringText.text = dataSource[position].getText()

        incrementButton.setOnClickListener(View.OnClickListener {
            if (dataSource[position].quantityInSet < dataSource[position].quantityInStore)
                dataSource[position].quantityInSet++

            notifyDataSetChanged()
        })

        decrementButton.setOnClickListener(View.OnClickListener {
            if (dataSource[position].quantityInSet > 0)
                dataSource[position].quantityInSet--

            notifyDataSetChanged()
        })

        //dataSource[position].getImage()

        return rowView
    }

}
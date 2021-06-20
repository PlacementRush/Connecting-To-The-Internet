package com.example.connectingtotheinternet

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PostAdapter(private val context: Context,
                  private val dataSource: List<Posts>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.row, parent, false)
        val titleTextView = rowView.findViewById(R.id.header) as TextView
        val bodyTextView = rowView.findViewById(R.id.text) as TextView

        val post = getItem(position) as Posts

        titleTextView.text = post.title
        bodyTextView.text = post.body

        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}

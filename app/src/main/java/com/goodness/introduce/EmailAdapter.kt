package com.goodness.introduce

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class EmailAdapter(
	private val context: Context,
	private val list: List<EmailData>
) : BaseAdapter() {
	override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
		val view: View = LayoutInflater.from(context).inflate(R.layout.item, null)

		val emailBody = view.findViewById<TextView>(R.id.tv_email_body)

		emailBody.text = list[position].emailBody

		return view
	}

	override fun getCount(): Int {
		return list.size
	}

	override fun getItem(position: Int): Any {
		return list[position]
	}

	override fun getItemId(position: Int): Long {
		return list[position].id
	}

}

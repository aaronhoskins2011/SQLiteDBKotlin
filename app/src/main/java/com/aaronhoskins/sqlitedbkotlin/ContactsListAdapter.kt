package com.aaronhoskins.sqlitedbkotlin

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactsListAdapter(val contactList : ArrayList<Contact>, val context: Context) : RecyclerView.Adapter<ContactsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ContactsListAdapter.ViewHolder{
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false))
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactsListAdapter.ViewHolder, position: Int) {
        holder.tvName?.text = contactList[position].contactName
        holder.tvAddress?.text = contactList[position].contactAddress
        holder.tvPhoneNumber?.text = contactList[position].contactPhoneNumber
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView?.tv_contact_name
        val tvAddress = itemView?.tv_contact_address
        val tvPhoneNumber = itemView?.tv_contact_phone
    }
}
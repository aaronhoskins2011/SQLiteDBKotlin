package com.aaronhoskins.sqlitedbkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.rv_contact_list

class MainActivity : AppCompatActivity() {
    lateinit var databaseHelper : ContactsSQLiteDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = ContactsSQLiteDBHelper(this)
        val dummyList : ArrayList<Contact> = dummyList()
        dummyList.forEach { databaseHelper.insertContact(it) }
        initContactListView()
    }

    fun initContactListView() {
        rv_contact_list.layoutManager = LinearLayoutManager(this)
        rv_contact_list.adapter = ContactsListAdapter(databaseHelper.retriveAllContact(),this)
    }

    fun dummyList() : ArrayList<Contact>{
        val list = ArrayList<Contact>()

        list.add(Contact("5555555501","Bill","111 somewhere St"))
        list.add(Contact("5555555502","Mary","111 somewhere St"))
        list.add(Contact("5555555503","Luke","111 somewhere St"))
        list.add(Contact("5555555504","Jean","111 somewhere St"))
        list.add(Contact("5555555505","Han","111 somewhere St"))
        list.add(Contact("5555555506","Bob","111 somewhere St"))
        list.add(Contact("5555555507","James","111 somewhere St"))
        list.add(Contact("5555555508","William","111 somewhere St"))
        list.add(Contact("5555555509","Brittany","111 somewhere St"))
        list.add(Contact("5555555510","Ronnie","111 somewhere St"))
        list.add(Contact("5555555511","Gary","111 somewhere St"))

        return list
    }
}

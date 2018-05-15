package com.aaronhoskins.sqlitedbkotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.aaronhoskins.sqlitedbkotlin.ContactDatabaseConstants.CONTACTS_DATABASE_NAME
import com.aaronhoskins.sqlitedbkotlin.ContactDatabaseConstants.CONTACTS_DATABASE_VERSION
import com.aaronhoskins.sqlitedbkotlin.ContactDatabaseConstants.CONTACTS_FIELD_CONTACT_ADDRESS
import com.aaronhoskins.sqlitedbkotlin.ContactDatabaseConstants.CONTACTS_FIELD_CONTACT_NAME
import com.aaronhoskins.sqlitedbkotlin.ContactDatabaseConstants.CONTACTS_FIELD_CONTACT_NUMBER
import com.aaronhoskins.sqlitedbkotlin.ContactDatabaseConstants.CONTACTS_TABLE_NAME
import com.aaronhoskins.sqlitedbkotlin.ContactDatabaseConstants.CREATE_QUERY
import com.aaronhoskins.sqlitedbkotlin.ContactDatabaseConstants.DROP_QUERY

class ContactsSQLiteDBHelper(context : Context)
    : SQLiteOpenHelper(context, CONTACTS_DATABASE_NAME, null, CONTACTS_DATABASE_VERSION ) {
    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL(CREATE_QUERY)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        database?.execSQL(DROP_QUERY)
        onCreate(database)
    }

    override fun onDowngrade(database : SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(database, oldVersion, newVersion)
    }

    @Throws(SQLiteConstraintException::class)
    fun insertContact(contact : Contact) : Boolean {
        val database = writableDatabase
        val contentValues = ContentValues()

        contentValues.put(CONTACTS_FIELD_CONTACT_NAME, contact.contactName)
        contentValues.put(CONTACTS_FIELD_CONTACT_ADDRESS, contact.contactAddress)
        contentValues.put(CONTACTS_FIELD_CONTACT_NUMBER, contact.contactPhoneNumber)

        database.insert(CONTACTS_TABLE_NAME, null, contentValues)
        return true
    }

    @Throws(SQLiteConstraintException::class)
    fun deleteContact(contactPhoneNumber : String) : Boolean{
        val database = writableDatabase
        val selection = CONTACTS_FIELD_CONTACT_NUMBER + "LIKE ?"
        val selectionArguments = arrayOf(contactPhoneNumber)
        database.delete(CONTACTS_TABLE_NAME,selection, selectionArguments)
        return true
    }

    fun retriveSingleContact(phoneNumber : String) : ArrayList<Contact> {
        val contacts = ArrayList<Contact>()
        val database = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME +
                    "WHERE " + CONTACTS_FIELD_CONTACT_NUMBER + " = " + phoneNumber, null)
        } catch (e : SQLiteException) {
            onCreate(database)
            return ArrayList()
        }

        var name : String
        var address : String
        var phoneNumber : String
        if(cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                name = cursor.getString(cursor.getColumnIndex(CONTACTS_FIELD_CONTACT_NAME))
                address = cursor.getString(cursor.getColumnIndex(CONTACTS_FIELD_CONTACT_ADDRESS))
                phoneNumber = cursor.getString(cursor.getColumnIndex(CONTACTS_FIELD_CONTACT_NUMBER))

                contacts.add(Contact(phoneNumber, name, address))
                cursor.moveToNext()
            }
        }
        return contacts
    }

    fun retriveAllContact() : ArrayList<Contact> {
        val contacts = ArrayList<Contact>()
        val database = writableDatabase
        var cursor: Cursor? = null
        try {
            cursor = database.rawQuery("SELECT * FROM " + CONTACTS_TABLE_NAME ,null)
        } catch (e : SQLiteException) {
            onCreate(database)
            return ArrayList()
        }

        var name : String
        var address : String
        var phoneNumber : String
        if(cursor!!.moveToFirst()) {
            while (!cursor.isAfterLast) {
                name = cursor.getString(cursor.getColumnIndex(CONTACTS_FIELD_CONTACT_NAME))
                address = cursor.getString(cursor.getColumnIndex(CONTACTS_FIELD_CONTACT_ADDRESS))
                phoneNumber = cursor.getString(cursor.getColumnIndex(CONTACTS_FIELD_CONTACT_NUMBER))

                contacts.add(Contact(phoneNumber, name, address))
                cursor.moveToNext()
            }
        }
        return contacts
    }

}
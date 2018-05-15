package com.aaronhoskins.sqlitedbkotlin

object ContactDatabaseConstants {
    val CONTACTS_DATABASE_NAME : String = "contactsDatabase"
    val CONTACTS_DATABASE_VERSION : Int = 1
    val CONTACTS_TABLE_NAME : String = "contactsTable"
    val CONTACTS_FIELD_CONTACT_NAME : String = "contactName"
    val CONTACTS_FIELD_CONTACT_ADDRESS : String = "contactAddress"
    val CONTACTS_FIELD_CONTACT_NUMBER : String = "contactNumber"

    val CREATE_QUERY : String = "CREATE TABLE " + CONTACTS_TABLE_NAME + " (" +
            CONTACTS_FIELD_CONTACT_NAME + " TEXT, " +
            CONTACTS_FIELD_CONTACT_ADDRESS + " TEXT, " +
            CONTACTS_FIELD_CONTACT_NUMBER + " TEXT PRIMARY KEY)"

    val DROP_QUERY : String = "DROP TABLE IF EXIST " + CONTACTS_TABLE_NAME
}
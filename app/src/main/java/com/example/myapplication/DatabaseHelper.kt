package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.provider.BaseColumns._ID
import com.example.myapplication.DatabaseContainer.PersonTable.Companion.HEIGHT_COLUMN
import com.example.myapplication.DatabaseContainer.PersonTable.Companion.LASTNAME_COLUMN
import com.example.myapplication.DatabaseContainer.PersonTable.Companion.NAME_COLUMN
import com.example.myapplication.DatabaseContainer.PersonTable.Companion.TABLE_NAME
import com.example.myapplication.DatabaseContainer.PersonTable.Companion.WEIGHT_COLUMN

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {

        val personTable = "CREATE TABLE " +
                TABLE_NAME + " ( " +
                BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME_COLUMN + " TEXT, " +
                LASTNAME_COLUMN + " TEXT, " +
                HEIGHT_COLUMN + " TEXT, " +
                WEIGHT_COLUMN + " TEXT " + " ) "

        db!!.execSQL(personTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

    }

    // SQL Insert Data Function

    fun insertData(name: String, last_name: String, height: String, weight: String): Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(NAME_COLUMN, name)
        contentValues.put(LASTNAME_COLUMN, last_name)
        contentValues.put(HEIGHT_COLUMN, height)
        contentValues.put(WEIGHT_COLUMN, weight)
        val insert_data = db.insert(TABLE_NAME, null, contentValues)
        db.close()

        return !insert_data.equals(-1)
    }

    fun readData () : Cursor
    {
        val db : SQLiteDatabase = this.writableDatabase
        val read : Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME",null)
        return read
    }
    fun deleteData( id:String ) : Boolean
    {

        val db : SQLiteDatabase = this.writableDatabase
        val delete_data = db.delete(TABLE_NAME, "$_ID=?", arrayOf(id))

        return !delete_data.equals(-1)

    }



    companion object {

        private const val DATABASE_NAME = "Person.db"
        private const val DATABASE_VERSION = 1

    }
}
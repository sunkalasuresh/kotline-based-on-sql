package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var name : EditText
    lateinit var txt : TextView
    lateinit var lastname : EditText
    lateinit var height : EditText
    lateinit var weight : EditText
    lateinit var btn : Button
    lateinit var btnr : Button
    lateinit var btn2 : Button
    lateinit var id : EditText
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.EdtName)
        lastname = findViewById(R.id.EdtLastName)
        height = findViewById(R.id.EdtHeight)
        weight = findViewById(R.id.EdtWeight)
        btn = findViewById(R.id.insertButton)
        btnr = findViewById(R.id.resultText)
        txt = findViewById(R.id.textv)
        btn2 = findViewById(R.id.deleteButton)
        id = findViewById(R.id.EdtName)

        databaseHelper = DatabaseHelper(this)

        btn.setOnClickListener {

            insertFunction()
        }
        btnr.setOnClickListener {

            readDataFunction()
        }
        btn2.setOnClickListener {

            deleteDataFunction()
        }
    }



    private fun insertFunction() {

        val strName = name.text.toString()
        val strLastName = lastname.text.toString()
        val strHeight = height.text.toString()
        val strWeight = weight.text.toString()

        val result : Boolean = databaseHelper.insertData(strName,strLastName,strHeight,strWeight)

        when {
            result -> Toast.makeText(applicationContext, "Data Inserted Successfully...",Toast.LENGTH_LONG).show()
            else -> Toast.makeText(applicationContext, "Failed to insert data...",Toast.LENGTH_LONG).show()
        }

    }


    private fun readDataFunction() {

        val data = databaseHelper.readData()
        val stringBuffer = StringBuffer()

        if( data !=null && data.count >0)
        {
            while (data.moveToNext())
            {
                stringBuffer.append("ID: ${data.getString(0)}\n")
                stringBuffer.append("Name: ${data.getString(1)}\n")
                stringBuffer.append("LastName: ${data.getString(2)}\n")
                stringBuffer.append("Height: ${data.getString(3)}\n")
                stringBuffer.append("Weight: ${data.getString(4)}\n\n")
            }
            txt.text = stringBuffer.toString()
            Toast.makeText(applicationContext,"Data Retrieved....", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(applicationContext,"No Data...", Toast.LENGTH_LONG).show()

        }

    }

    private fun deleteDataFunction() {

        val strId = id.text.toString()
        val result : Boolean = databaseHelper.deleteData(strId)

        when {
            result -> Toast.makeText(applicationContext, "Data Deleted Successfully...", Toast.LENGTH_LONG).show()
            else -> Toast.makeText(applicationContext, "Failed to delete data...", Toast.LENGTH_LONG).show()
        }

    }
}
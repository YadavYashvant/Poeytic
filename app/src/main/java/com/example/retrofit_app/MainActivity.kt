package com.example.retrofit_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val BASE_URL = "https://poetrydb.org/author,title/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPoetryData();
    }

    private fun getPoetryData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<PoetryApiDataItem>?> {
            override fun onResponse(
                call: Call<List<PoetryApiDataItem>?>,
                response: Response<List<PoetryApiDataItem>?>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                for(myData in responseBody) {
                    myStringBuilder.append(myData.lines)
                    myStringBuilder.append("\n\n\n\n\n")
                }
                val txtmain = findViewById<TextView>(R.id.txtmain)
                txtmain.text = myStringBuilder

            }

            override fun onFailure(call: Call<List<PoetryApiDataItem>?>, t: Throwable) {
                Toast.makeText(applicationContext,"Error Loading poetry!",Toast.LENGTH_SHORT).show()
            }
        })
    }
}
package com.example.quotesapp
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quotesapp.api.CatJson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//const val BASE_URL = "https://cat-fact.herokuapp.com"
class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var textViewTimestamp: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var relativeLayout: RelativeLayout

    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.tv_textView)
        textViewTimestamp = findViewById(R.id.tv_timeStamp)
        progressBar = findViewById(R.id.progressBar)
        relativeLayout = findViewById(R.id.layout_generate_new_fact)
        fetchRandomCatFact()

        relativeLayout.setOnClickListener{
            fetchRandomCatFact()
        }
    }
    private fun fetchRandomCatFact() {
        textView.visibility = View.INVISIBLE
        textViewTimestamp.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        RetrofitInstanse.api.getCatFacts().enqueue(object : Callback<CatJson> {
            override fun onResponse(call: Call<CatJson>, response: Response<CatJson>) {
                if (response.isSuccessful) {
                    val catFact = response.body()
                    textView.visibility = View.VISIBLE
                    textViewTimestamp.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE

                    textView.text = catFact?.text ?: "No fact available"
                    textViewTimestamp.text = catFact?.createdAt ?: "No fact available"
                } else {
                    Toast.makeText(this@MainActivity, "Failed to get response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CatJson>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to call API", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
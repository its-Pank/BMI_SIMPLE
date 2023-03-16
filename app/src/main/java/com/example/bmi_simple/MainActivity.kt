package com.example.bmi_simple

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.bmi_simple.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var isClear: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
        inflater.inflate(R.menu.menu_items,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item1 -> {
                val intent = Intent(this@MainActivity, AboutDeveloper::class.java)
                startActivity(intent)
            }

            R.id.item2 -> {
                val intent = Intent(this@MainActivity, BMICHART::class.java)
                startActivity(intent)
            }
            R.id.item3 -> {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.news-medical.net/health/What-is-Body-Mass-Index-(BMI).aspx")
                )
                startActivity(intent)
            }

//            R.id.item4->{
//                val intent=Intent(Intent.ACTION_)
//                startActivity(intent) }
//
//            }
            return super.onOptionsItemSelected(item)
        }
    }



    @SuppressLint("SetTextI18n")
    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_calculate -> {
                if (binding.height.text!!.isEmpty() && binding.weight.text!!.isEmpty()) {
                    Toast.makeText(this, "Enter the height & Weight", Toast.LENGTH_SHORT).show()
                } else if (binding.weight.text!!.isEmpty()) {
                    Toast.makeText(this, "Enter the weight", Toast.LENGTH_SHORT).show()
                } else if (binding.height.text!!.isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "please enter height  ",
                        Toast.LENGTH_LONG
                    ).show()
                }

                if (isClear) {
                    isClear = false
                    binding.btnCalculate.text = "Calculate"
                    //binding.BMI.setText("")
                    binding.BMI.text = ""
                    binding.weight.text?.clear()
                    binding.height.text?.clear()
                    Toast.makeText(this, "Clear ", Toast.LENGTH_SHORT).show()
                } else {

                    //Toast.makeText(this@MainActivity, "hello", Toast.LENGTH_LONG).show()

                    // Check if the height EditText and Weight EditText are not empty
                    if (binding.height.text.toString()
                            .isNotEmpty() && binding.weight.text.toString().isNotEmpty()
                    ) {
                        if (!isClear) {
                            // initialize the variable
                            isClear = true
                            binding.btnCalculate.text = "Clear"
                            val height = (binding.height.text.toString()).toDouble()
                            val weight = (binding.weight.text.toString()).toDouble()

                            if (height == 0.0 || weight == 0.0) {
                                Toast.makeText(
                                    this,
                                    "Invalid height or weight ",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {
                                val heightinmetre = height.toFloat() / 100
                                val total = weight.toFloat() / (heightinmetre * heightinmetre)
                                val BMI = (total * 100).roundToInt() / 100.0


                                // update the status text as per the bmi conditions
                                if (BMI < 18.5) {
                                    // Toast.makeText(this@MainActivity, R.string.under_weight, Toast.LENGTH_LONG).show()
                                    binding.BMI.text= " Your BMI Value :- $BMI \n Health Status:- You are Under Weight"
                                } else if (BMI >= 18.5 && BMI < 24.9) {

                                    // Toast.makeText(this@MainActivity, R.string.Healthy, Toast.LENGTH_LONG).show()
                                    binding.BMI.text= " Your BMI Value :- $BMI \n Health Status:- You are Healthy"
                                } else if (BMI >= 24.9 && BMI < 30) {
                                    // Toast.makeText(this@MainActivity, R.string.over_weight, Toast.LENGTH_LONG).show()
                                    binding.BMI.text= " Your BMI Value :- $BMI \n Health Status:- You are Over Weight"
                                } else {
                                    //Toast.makeText(this@MainActivity, R.string.Suffering_from_Obesity, Toast.LENGTH_LONG).show()
                                    binding.BMI.text= " Your BMI Value :- $BMI \n Health Status:- Suffering from Obesity"

                                }
                            }
                        }

                    }

                }

            }

        }
    }
}
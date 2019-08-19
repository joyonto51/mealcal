package com.itsd.mealcal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.first_layout.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClickListener()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.about -> {
                startActivity(Intent(this, AboutActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    fun onClickListener(){

        hasFine.setOnClickListener {
            if (hasFine.isChecked){
                fineRangeText.visibility = View.VISIBLE
                fineRange.visibility = View.VISIBLE
                fineRateText.visibility = View.VISIBLE
                fineRate.visibility = View.VISIBLE
            }
            else{
                fineRangeText.visibility = View.INVISIBLE
                fineRange.visibility = View.INVISIBLE
                fineRateText.visibility = View.INVISIBLE
                fineRate.visibility = View.INVISIBLE
            }
        }

        gobtn.setOnClickListener {
            val mealRate = mealRate.text
            val extraCost = extraCost.text
            val fineRange = fineRange.text
            val fineRate = fineRate.text

            if(mealRate.isEmpty() || extraCost.isEmpty()){
                toast("Please, give meal rate & extra cost")
            }

            else if (hasFine.isChecked && (fineRange.isEmpty() || fineRate.isEmpty())){
                toast("Please, give fine range & fine rate.")
            }

            else{
                println("=====>>> ${mealRate}, ${extraCost}, ${fineRange}, ${fineRate}")

                val countGuestMeal = guestMealCheckBox.isChecked
                val hasFine = hasFine.isChecked

                val SecondIntent = Intent(this, Main2Activity::class.java)

                SecondIntent.putExtra("meal_rate", mealRate.toString())
                SecondIntent.putExtra("total_cost", extraCost.toString())

                SecondIntent.putExtra("has_fine", hasFine)
                SecondIntent.putExtra("count_guest_meal", countGuestMeal)

                if(hasFine){
                    SecondIntent.putExtra("fine_range", fineRange.toString())
                    SecondIntent.putExtra("fine_rate", fineRate.toString())
                }

                startActivity(SecondIntent)
            }
        }
    }

    fun toast(message:String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }
}

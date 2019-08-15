package com.itsd.mealcal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gobtn.setOnClickListener {
            val mealRate = mealRate.text
            val totalCost = totalCost.text
            val fineRange = fineRange.text
            val fineRate = fineRate.text

            if(mealRate.isEmpty() || totalCost.isEmpty() || fineRange.isEmpty() || fineRate.isEmpty()){
                Toast.makeText(this, "Please, give all input.", Toast.LENGTH_SHORT).show()
            }
            else{
                println("=====>>> ${mealRate}, ${totalCost}, ${fineRange}, ${fineRate}")

                val countGuestMeal = guestMealCheckBox.isChecked

                val SecondIntent = Intent(this, Main2Activity::class.java)

                SecondIntent.putExtra("meal_rate", mealRate.toString())
                SecondIntent.putExtra("total_cost", totalCost.toString())
                SecondIntent.putExtra("fine_range", fineRange.toString())
                SecondIntent.putExtra("fine_rate", fineRate.toString())
                SecondIntent.putExtra("count_guest_meal", countGuestMeal)

                startActivity(SecondIntent)
            }
        }
    }
}

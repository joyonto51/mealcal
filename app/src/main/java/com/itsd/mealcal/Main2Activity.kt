package com.itsd.mealcal

import android.net.ParseException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.NumberFormatException

class Main2Activity : AppCompatActivity() {
    var mealRate = 0F
    var totalCost = 0
    var fineRange = 0
    var fineRate = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val meal_rate = intent.getStringExtra("meal_rate")
        val total_cost = intent.getStringExtra("total_cost")
        val fine_range = intent.getStringExtra("fine_range")
        val fine_rate = intent.getStringExtra("fine_rate")
        val countGuestMeal = intent.getBooleanExtra("count_guest_meal", false)

        mealRatetxt.text = getString(R.string.mealRateText, meal_rate)
        totalCostTxt.text = getString(R.string.totalCostText, total_cost)
        fineRangeTxt.text = getString(R.string.fineRangeText, fine_range)
        fineRateTxt.text = getString(R.string.fineRateText, fine_rate)

        mealRate = meal_rate!!.toFloat()
        totalCost = total_cost!!.toInt()
        fineRange = fine_range!!.toInt()
        fineRate = fine_rate!!.toFloat()

        println("=====>>> ${mealRate}, ${totalCost}, ${fineRange}, ${fineRate}")

        calculate.setOnClickListener {
            try {
                val totalBalance = totalBalance.text.toString().toInt()
                val totalMeal = totalMeal.text.toString().toInt()
                val guestMeal = guestMealTk.text.toString().toInt()


                val totalSpend = totalCost + (mealRate * totalMeal) + guestMeal
                val remainingBlance = totalBalance - totalSpend
                var fine = 0
                if (remainingBlance < 0 && (remainingBlance*-1) >= fineRange ){
                    fine = ((remainingBlance/fineRange).toInt() * fineRate.toInt()) + -1
                }

                val totalDue = remainingBlance + fine

                println("=====>> ${totalSpend}, ${remainingBlance}, ${fine}, ${totalDue}")

                totalSpendBalance.text = getString(R.string.total_spend, totalSpend.toString())
                remainingBalance.text = getString(R.string.remaining_balance, remainingBlance.toString())
                fineBalance.text = getString(R.string.fine_balance, fine.toString())
                dueBalance.text = getString(R.string.due_balance, totalDue.toString())
            }
           catch (e:NumberFormatException){
               Toast.makeText(this, "Please, give all input", Toast.LENGTH_SHORT).show()
           }

        }

    }
}
package com.itsd.mealcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val has_fine = intent.getBooleanExtra("has_fine", false)
        val countGuestMeal = intent.getBooleanExtra("count_guest_meal", false)

        if (has_fine){
            val fine_range = intent.getStringExtra("fine_range")
            val fine_rate = intent.getStringExtra("fine_rate")

            fineRangeTxt.text = getString(R.string.fineRangeText, fine_range)
            fineRateTxt.text = getString(R.string.fineRateText, fine_rate)

            fineRateTxt.visibility = View.VISIBLE
            fineRangeTxt.visibility = View.VISIBLE

//            headerLayout.maxHeight = 50

            fineRange = fine_range!!.toInt()
            fineRate = fine_rate!!.toFloat()

        }

        mealRatetxt.text = getString(R.string.mealRateText, meal_rate)
        totalCostTxt.text = getString(R.string.extraCostText, total_cost)

        mealRate = meal_rate!!.toFloat()
        totalCost = total_cost!!.toInt()

        if (countGuestMeal){
            countGuestMealText.visibility = View.VISIBLE
            guestMealTk.visibility = View.VISIBLE
        }

        calculate.setOnClickListener {
            var guestMeal = 0

            try {
                val totalBalance = totalBalance.text.toString().toInt()
                val totalMeal = totalMeal.text.toString().toInt()

                if (countGuestMeal){
                    if(guestMealTk.text.isNotEmpty()){
                        guestMeal = guestMealTk.text.toString().toInt()
                        calculate(totalBalance, totalMeal, guestMeal)
                    }
                    else {
                        makeOutputUnVisible()
                        makeFineOutputUnVisivble()
                        toast("Please, give guest meal charge")
                    }
                }
                else{
                    calculate(totalBalance, totalMeal, guestMeal)
                }

            }
           catch (e:NumberFormatException){
               makeOutputUnVisible()
               makeFineOutputUnVisivble()
               toast("Please, give total balance & meal")
           }

        }

    }

    fun calculate(totalBalance:Int, totalMeal: Int, guestMeal: Int){
        var fine = 0F

        // ============================ Calculation ============================ \\

        val totalSpend = totalCost + (mealRate * totalMeal) + guestMeal
        val remainingBlance = totalBalance - totalSpend

        if (remainingBlance < 0 && (remainingBlance*-1) >= fineRange ){
            fine = ((remainingBlance/fineRange).toInt() * fineRate) * -1
        }

        val totalDue = remainingBlance*-1 + fine

        // ============================      End     =========================== \\


        totalSpendBalance.text = totalSpend.toString()
        remainingBalance.text = remainingBlance.toString()

        makeOutputVisible()

        if (fine > 0){
            fineBalance.text = fine.toString()
            dueBalance.text = totalDue.toString()

            makeFineOutputVisivble()
        }
        else{
            makeFineOutputUnVisivble()
        }
    }

    fun toast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    fun makeOutputVisible(){
        totalSpendBalance.visibility = View.VISIBLE
        remainingBalance.visibility = View.VISIBLE
        totalSpendBalanceText.visibility = View.VISIBLE
        remainingBalanceText.visibility = View.VISIBLE
    }

    fun makeOutputUnVisible(){
        totalSpendBalance.visibility = View.INVISIBLE
        remainingBalance.visibility = View.INVISIBLE
        totalSpendBalanceText.visibility = View.INVISIBLE
        remainingBalanceText.visibility = View.INVISIBLE
    }

    fun makeFineOutputVisivble(){
        fineBalance.visibility = View.VISIBLE
        dueBalance.visibility = View.VISIBLE
        fineBalanceText.visibility = View.VISIBLE
        dueBalanceText.visibility = View.VISIBLE
    }

    fun makeFineOutputUnVisivble(){
        fineBalanceText.visibility = View.INVISIBLE
        dueBalanceText.visibility = View.INVISIBLE
        fineBalance.visibility = View.INVISIBLE
        dueBalance.visibility = View.INVISIBLE
    }
}
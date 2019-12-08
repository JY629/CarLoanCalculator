package com.example.carloancalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonCalculate.setOnClickListener{
            calculateLoan()
        }
    }

    private fun calculateLoan(){
        //Getting input data from the user
        val carPrice=editTextCarPrice.text.toString().toInt()
        val downPayment=editTextDownPayment.text.toString().toInt()
        val loanPeriod=editTextLoanPeriod.text.toString().toInt()
        val interestRate=editTextInterestRate.text.toString().toFloat()

        //calculation for Car loan, Interest and Monthly payment
        val carLoan=carPrice-downPayment
        val interest=carLoan*interestRate*loanPeriod
        val monthPay=(carLoan+interest)/loanPeriod/12


        //Create an Explicit Intent
        val intent = Intent(this, ResultActivity::class.java)
        //TODO: passing data using putExtra method
        //format : putExtra(TAG, value)
        intent.putExtra(LOAN, carLoan)
        intent.putExtra(INTEREST, interest)
        intent.putExtra(MONTHLY_PAYMENT, monthPay)

        startActivity(intent)
    }

    fun resetInput(view : View){
        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextLoanPeriod.setText("")
        editTextInterestRate.setText("")
    }

    companion object {
        const val LOAN = "carLoan"
        const val INTEREST = "interest"
        const val MONTHLY_PAYMENT = "payment"
    }
}


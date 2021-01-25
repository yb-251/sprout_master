package com.sprout.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sprout.R
import kotlinx.android.synthetic.main.activity_interest.*

class InterestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)


        btn_next_step.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {

                when (p0!!.id) {
                    R.id.btn_beauty_makeup -> {
                        Toast.makeText(this@InterestActivity, "111", Toast.LENGTH_SHORT).show()
                    }
                }

                startActivity(Intent(this@InterestActivity, InterestListActivity::class.java))

            }

        })


    }
}
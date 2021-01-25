package com.sprout.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sprout.R
import kotlinx.android.synthetic.main.activity_interest_list.*

class InterestListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest_list)

        btn_interestlist_next_step.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, EnterPageActivity::class.java))
        })
    }
}
package com.sprout.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.sprout.R
import kotlinx.android.synthetic.main.activity_sex.*


class SexActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sex)


        rgSex.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_boy -> {
                    //显示 填写好友邀请码（选填） "  下一步
                    et_invitation.visibility = View.VISIBLE
                    btn_next_step.visibility = View.VISIBLE
                    //隐藏 选择性别后继续
                    btn_check_sex.visibility = View.GONE
                }
                R.id.rb_girl -> {
                    //显示 填写好友邀请码（选填） "  下一步
                    et_invitation.visibility = View.VISIBLE
                    btn_next_step.visibility = View.VISIBLE
                    //隐藏 选择性别后继续
                    btn_check_sex.visibility = View.GONE
                }
            }
        })
        btn_next_step.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, InterestActivity::class.java))
        })
    }


}
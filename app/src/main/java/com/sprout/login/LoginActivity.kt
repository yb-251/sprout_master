package com.sprout.login

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.king.view.splitedittext.SplitEditText
import com.sprout.MainActivity
import com.sprout.R
import com.sprout.app.Constants
import com.sprout.utils.SpUtils
import com.sprout.utils.Util
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.acticity_writelogin.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sendlogin.*
import java.util.concurrent.TimeUnit

/**
 * 视频资源要添加res文件夹下创建raw文件夹
 * 需要在onRestart()方法里重新加载视频，防止退出返回时视频黑屏
 * 我这样写简单粗暴而已，当然，也可优雅的以自己看播放控件的VideoView处理方法，去处理资源释放和播放显示的问题。
 * 记得修改布局控件<com.daqie.videobackground.CustomVideoView...引用的包名></com.daqie.videobackground.CustomVideoView...引用的包名>，不然会报错哦
 * android:screenOrientation="portrait" 习惯性的把横竖屏切换也设置一下
 * android:theme="@style/Theme.AppCompat.Light.NoActionBar" ActionBar也可以设置成不显示的状态，可以根据自己喜好和项目需求
 */
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    //创建播放视频的控件对象
    private var videoview: CustomVideoView? = null
    private var mText2Tv: TextView? = null
    private var mText1Tv: TextView? = null
    private var clickableSpan: ClickableSpan? = null
    private var clickableSpan2: ClickableSpan? = null
    private val clickableSpan3: ClickableSpan? = null
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //透明状态栏
        Util.makeStatusBarTransparent(this)
        //加载数据
        initView()
    }

    private fun initView() {
        //加载视频资源控件
        videoview = findViewById(R.id.videoview) as CustomVideoView

        //设置播放加载路径
//        videoview!!.setVideoPath(
//            Environment.getExternalStorageDirectory().toString() + "/Pictures/boy.mp4"
//        )
        //videoview!!.setVideoPath( "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4" );
        videoview!!.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.login_bg))
        //播放
        videoview!!.start()
        //循环播放
        videoview!!.setOnCompletionListener { videoview!!.start() }
        mText2Tv = findViewById(R.id.tv_text2) as TextView
        mText1Tv = findViewById(R.id.tv_text1) as TextView

        //富文本
        Spannable()

        //其他电话号码 登录
        elseLogin()

        //输入验证码
        verify()

        //登录成功
        login()

        //获取验证码
        btn_verify




        iv_wb.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(Intent(this@LoginActivity,LoginOkActivity::class.java))
            }
        })
    }

    private fun login() {

        btn_login.setOnClickListener(object: View.OnClickListener {
            override fun onClick(p0: View?) {
                    val string1 = SpUtils.instance!!.getString(Constants.token)
                    if (string1 != "") {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        startActivity(Intent(this@LoginActivity, LoginOkActivity::class.java))
                    }

                //点击 获取验证码
                getVerify()

            }



        })
    }

    private fun elseLogin() {
        tv_elselogin.setOnClickListener(View.OnClickListener {
            //隐藏电话
            ll_login.visibility = View.GONE
            tv_text1.visibility = View.GONE
            tv_text2.bottom.and(30)
            //显示
            cl_loginverify.visibility = View.VISIBLE

        })
    }

    private fun getVerify() {

        btn_verify.setOnClickListener(View.OnClickListener {
            cl_loginverify.visibility = View.VISIBLE
            cl_loginsend.visibility = View.GONE
            //倒计时
            initTime()
        })

    }

    private fun initTime() {
        disposable =
            Observable.intervalRange(0, 6, 0, 1, TimeUnit.SECONDS) //起始值，发送总数量，初始延迟，固定延迟
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()) //两种写法    1. lambda表达式写法：
                .subscribe { aLong ->
                    val time = 5 - aLong
                    btn_verify.setBackgroundResource(R.drawable.phone_verify)
                    tv_anew_verify.alpha = 0.5f
                    tv_anew_verify.setText("倒计时：$time")
                    if (time == 0L) {
                        tv_anew_verify.alpha = 1f
                        tv_anew_verify.setBackgroundResource(R.drawable.phone_login)
                        tv_anew_verify.setText("获取短信验证码")
                    }
                }

        tv_anew_verify.setOnClickListener(View.OnClickListener {
            tv_anew_verify.setBackgroundResource(R.drawable.phone_verify)
            initTime()
        })
    }

    private fun verify() {
        //设置监听
        splitEditText.setOnTextInputListener(object : SplitEditText.OnTextInputListener {
            override fun onTextInputChanged(text: String, length: Int) {
                //TODO 文本输入改变
            }

            override fun onTextInputCompleted(text: String) {
                //TODO 文本输入完成
            }

        })
    }

    private fun Spannable() {

        val string = mText2Tv!!.text.toString()
        val start = string.indexOf(" ")
        val end = string.indexOf("|")
        val span = SpannableStringBuilder(string)

        //设置EZ的背景色

        span.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF9800")),
            start + 1,
            end,
            Spanned.SPAN_INCLUSIVE_INCLUSIVE
        )
        //监听
        clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                (view as? TextView)?.highlightColor = Color.TRANSPARENT//去除背景色
                Toast.makeText(this@LoginActivity, "用户协议", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) { //设置显示样式
                ds.isUnderlineText = false //不要默认下划线
                ds.isUnderlineText = false//去除下划线

            }
        }

        span.setSpan(clickableSpan, start + 1, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        span.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF9800")),
            end + 1,
            span.length,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )

        //设置EZ前景色，也就是字体颜色
        mText2Tv!!.movementMethod = LinkMovementMethod.getInstance()
        mText2Tv!!.text = span

        val string2 = mText1Tv!!.text.toString()
        val start2 = string2.indexOf(" ")
        val span2 = SpannableStringBuilder(string2)
        span2.setSpan(
            ForegroundColorSpan(Color.parseColor("#0099EE")),
            start2 + 1,
            span2.length,
            Spanned.SPAN_EXCLUSIVE_INCLUSIVE
        )
        //设置EZ的背景色
        clickableSpan2 = object : ClickableSpan() {
            override fun onClick(view: View) {
                (view as? TextView)?.highlightColor = Color.TRANSPARENT//去除背景色
                Toast.makeText(this@LoginActivity, " 中国移动认证", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) { //设置显示样式
                ds.isUnderlineText = false //不要默认下划线
                ds.isUnderlineText = false//去除下划线

            }
        }
        span2.setSpan(clickableSpan2, start2 + 1, span2.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        mText1Tv!!.movementMethod = LinkMovementMethod.getInstance()
        mText1Tv!!.text = span2
    }

    //返回重启加载
    override fun onRestart() {
        initView()
        super.onRestart()
    }

    //防止锁屏或者切出的时候，音乐在播放
    override fun onStop() {
        videoview!!.stopPlayback()
        super.onStop()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
            }
            else -> {
            }
        }
    }

    //取消订阅的方法
    private fun cancelCallback() {
        if (disposable != null && !disposable!!.isDisposed()) {
            disposable!!.dispose()
        }
    }
}
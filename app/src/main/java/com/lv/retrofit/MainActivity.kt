package com.lv.retrofit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lv.retrofit.api.UserApi
import com.lv.retrofit.model.UpdateBean
import com.lv.retrofit.model.User
import com.lv.retrofit.proxy.ImitationRetrofit
import com.lv.retrofit.proxy.interceptor.ProtocolInterceptor
import com.wumart.warehouse.util.io_main
import okhttp3.OkHttpClient
import rx.Subscriber
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DLog.init()
        val builder = OkHttpClient.Builder()
                .addInterceptor(ProtocolInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false)
        val userApi = ImitationRetrofit.Builder()
                .baseUrl("http://10.13.3.19:16000/wmapp-server/")
                .httpClient(builder.build())
                .build()
                .create(UserApi::class.java)
        userApi.user(User(1,"asdf"))
                .io_main()
                .subscribe(object : Subscriber<UpdateBean>(){
                    override fun onNext(t: UpdateBean?) {
                        Toast.makeText(this@MainActivity,"成功了",Toast.LENGTH_SHORT).show()
                        DLog.d(t)
                    }

                    override fun onCompleted() {
                        DLog.d("onCompleted")
                    }

                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                        Toast.makeText(this@MainActivity,e?.message,Toast.LENGTH_SHORT).show()
                    }

                })
//http://10.13.3.19:16000/wmapp-server/app/getNewest
        /*  Thread{
              val loadUser = userApi.loadUser("test")
              println(loadUser)
          }.start()*/
    }
}

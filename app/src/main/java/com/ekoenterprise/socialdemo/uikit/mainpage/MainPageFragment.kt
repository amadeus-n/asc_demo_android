package com.ekoenterprise.socialdemo.uikit.mainpage

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ekoapp.ekosdk.EkoClient
import com.ekoapp.ekosdk.uikit.chat.home.fragment.EkoChatHomePageFragment
import com.ekoenterprise.socialdemo.R
import com.ekoapp.ekosdk.uikit.community.home.fragments.EkoCommunityHomePageFragment
import com.ekoapp.ekosdk.uikit.community.profile.fragment.EkoUserProfilePageFragment
import com.ekoenterprise.socialdemo.custom.homepage.HomePageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_news_feed.*
import kotlinx.android.synthetic.main.activity_news_feed_home.*
import java.util.*

class MainPageFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_feed_home)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val fragment = getHomePage()
        initView(resources.getString(R.string.uuid), resources.getString(R.string.username), fragment)
    }

    private fun initView(user: String, username: String, fragment: Fragment) {
        EkoClient.registerDevice(user).displayName(username).build().submit()
            .subscribeOn(Schedulers.io())
            .observeOn((AndroidSchedulers.mainThread()))
            .doOnComplete {
                addFragment(fragment)
            }
            .doOnError {
                Toast.makeText(this, "Could not register user" + it.message, Toast.LENGTH_SHORT)
                    .show()
            }
            .subscribe()
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val titleView = findViewById<TextView>(R.id.titleName)
            titleView.visibility = View.VISIBLE
            when (item.itemId) {
                R.id.home -> {
                    titleView.text = "Home"
                    val fragment = getHomePage()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.globalFeed -> {
                    titleView.text = "Community"
                    val fragment = getNewsFeed()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.explore -> {
                    titleView.text = ""
                    titleView.visibility = View.GONE
                    val fragment = getChat()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profile -> {
                    titleView.text = "Profile"
                    val fragment = getProfile()
                    addFragment(fragment)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    private fun getHomePage(): Fragment {
        return HomePageFragment()
    }

    private fun getNewsFeed(): Fragment {
        return EkoCommunityHomePageFragment.Builder()
            .build(this)
    }

    private fun getProfile(): Fragment {
        return EkoUserProfilePageFragment.Builder()
            .userId(EkoClient.getUserId())
            .build(this)
    }

    private fun getChat(): Fragment {
        return EkoChatHomePageFragment.Builder()
            .build(this)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.design_bottom_sheet_slide_in,
                R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .commit()
    }

}


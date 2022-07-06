package com.example.mycameraapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mycameraapp.databinding.ActivityMainBinding
import com.example.mycameraapp.login.LoginActivity
import com.example.mycameraapp.session.SessionManager
import com.example.mycameraapp.util.BalloonUtils
import com.skydoves.balloon.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var toggle: ActionBarDrawerToggle

    private var isFullScreen = true
    private var isMeasureClick = false

    val measuresItems by lazy {
        measuresBalloons()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setSupportActionBar(binding.appBarMain.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeButtonEnabled(true)

        /*binding.appBarMain.fab.setOnClickListener { view ->
            *//*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*//*
        }*/
        val drawerLayout: DrawerLayout = binding.drawerLayout
        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navView.setNavigationItemSelectedListener {
            // close drawerlayout when
            binding.drawerLayout.close()

            when (it.itemId) {
                R.id.nav_logout -> logout(this)
            }

            true

        }

        // call function here below
        clickFullScreenImage()
        clickMeasureIcon()


    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun goToLoginActivity() {
        val intent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }


    private fun logout(context: Context) {
        val builder = androidx.appcompat.app.AlertDialog.Builder(context)
            .setIcon(R.drawable.ic_logout)
            .setTitle(context.getString(R.string.menu_logout))
            .setMessage(context.getString(R.string.want_logout_text))
        builder.setPositiveButton(context.getString(R.string.yes)) { _, _ ->
            doOnLogoutAndGoToMainActivity()


        }
        builder.setNegativeButton(getString(R.string.cancel), null)
        builder.create().show()
    }


    private fun clickFullScreenImage() {
        binding.fullScreenImage.setOnClickListener {
            if (isFullScreen) {
                hideBottomRlElementsAndActionBar()
                isFullScreen = false
            } else {
                showBottomRlElementsAndActionBar()
                isFullScreen = true
            }

        }
    }

    private fun hideBottomRlElementsAndActionBar() {
        binding.bottomRl.visibility = View.INVISIBLE
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.hide()
        binding.fab.visibility = View.GONE
        binding.fullScreenImage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_fullscreen_exit_24))
//        binding.fullScreenImage.


        /*val rparams: RelativeLayout.LayoutParams = binding.bottomRl
            .getLayoutParams() as RelativeLayout.LayoutParams
        rparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        binding.fullScreenImage.layoutParams = rparams*/
//        (binding.bottomRl as ViewGroup).addView(binding.fullScreenImage, rparams)
    }

    private fun showBottomRlElementsAndActionBar() {
        binding.bottomRl.visibility = View.VISIBLE
        supportActionBar?.show()
        binding.fab.visibility = View.VISIBLE

        binding.fullScreenImage.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_fullscreen_24))


        /*val rparams: RelativeLayout.LayoutParams = binding.bottomRl
            .getLayoutParams() as RelativeLayout.LayoutParams
        rparams.addRule(0)
        binding.fullScreenImage.layoutParams = rparams*/
    }


    private fun doOnLogoutAndGoToMainActivity() {
        val sessionManager = SessionManager.getInstance(applicationContext)
        sessionManager!!.deleteAll()
        Toast.makeText(this, "you have successfully logged out", Toast.LENGTH_SHORT).show()
        goToLoginActivity()

    }


    private fun showArrowDialogForMeasure() {
        binding.measure.showAlignTop(measuresItems, 0, 0)
    }

    private fun clickMeasureIcon() {
        binding.measure.setOnClickListener {

            showArrowDialogForMeasure()
        }
    }


    private fun measuresBalloons(): Balloon {
        return BalloonUtils.getAllBalloon(
            applicationContext,
            this,
            "Clear All",
            R.drawable.ic_baseline_close_24,
            object : OnBalloonClickListener {
                override fun onBalloonClick(view: View) {

                }
            }
        )
    }

}
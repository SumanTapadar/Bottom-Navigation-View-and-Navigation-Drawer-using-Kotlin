package com.example.basicdesign

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.basicdesign.ui.acountActivity
import com.example.basicdesign.ui.dashActivity
import com.example.basicdesign.ui.homeActivity
import com.example.basicdesign.ui.logoutActivity
import com.example.basicdesign.ui.searchActivity
import com.example.basicdesign.ui.settingActivity
import com.example.basicdesign.ui.shareActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var drawerlayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left,0, systemBars.right, 0)
            insets
        }
        val bottom_nav=findViewById<BottomNavigationView>(R.id.bottomNavigatio_id)
        drawerlayout=findViewById(R.id.main)
        val nav=findViewById<NavigationView>(R.id.navigatioView_id)
        val toolbar=findViewById<Toolbar>(R.id.toolbar_id)
        setSupportActionBar(toolbar)
        replacewithfragments(homeActivity())

val toggle=ActionBarDrawerToggle(this,drawerlayout,toolbar,R.string.open_nav,R.string.close_nav)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()


       bottom_nav.setOnItemSelectedListener {
           when(it.itemId){
               R.id.home_id->{replacewithfragments(homeActivity())}
                   R.id.search_id->{replacewithfragments(searchActivity())}
                   R.id.dash_id->{
                       replacewithfragments(dashActivity())
                   }
                   R.id.account_id->{
                       replacewithfragments(acountActivity())

                   }
               else->{

               }
           }
           true
       }
        nav.setNavigationItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.setting_id->{
                    replacewithfragments(settingActivity())
                }
                R.id.logout_id->{
                    replacewithfragments(logoutActivity())
                }
                R.id.share_id->{
                    replacewithfragments(shareActivity())
                }
            }
            drawerlayout.closeDrawer(GravityCompat.START)
            true


        }





    }

    private fun replacewithfragments(frag:Fragment) {
        val fragmanger=supportFragmentManager
        val fragTransition=fragmanger.beginTransaction()
        fragTransition.replace(R.id.framelayout_id,frag)
        fragTransition.commit()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
if(drawerlayout.isDrawerOpen(GravityCompat.START)){
    drawerlayout.closeDrawer(GravityCompat.START)
}else{
    super.onBackPressed()
}
    }
}
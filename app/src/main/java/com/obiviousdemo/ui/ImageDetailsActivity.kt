package com.obiviousdemo.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.obiviousdemo.R
import com.obiviousdemo.data.model.NasaPicModelItem
import com.obiviousdemo.utils.Constants
import com.obiviousdemo.utils.ZoomOutPageTransformer

class ImageDetailsActivity : AppCompatActivity() {

    private lateinit var itemListFromIntent: ArrayList<NasaPicModelItem>
    private var positionFromIntent: Int = 0
    private lateinit var toolbar: Toolbar
    private var NUM_PAGES: Int = 0
    private lateinit var viewPager: ViewPager2


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        toolbar = findViewById(R.id.toolbar_actionbar) as Toolbar
        viewPager = findViewById(R.id.pager) as ViewPager2
        viewPager.setPageTransformer(ZoomOutPageTransformer())
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Detail Page")
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_24));
        getIntentValues()
        onClickEvents()
        pagerAdapter()

    }

    private fun getIntentValues() {
        val intent = getIntent().extras
        itemListFromIntent =
            intent?.getSerializable(Constants.INTENT_LIST) as ArrayList<NasaPicModelItem>
        positionFromIntent = intent!!.getInt(Constants.INTENT_ITEM_POSITION)
        if (itemListFromIntent.size > 0) {
            NUM_PAGES = itemListFromIntent.size
        }
    }

    private fun onClickEvents() {
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            super.onBackPressed()
        })
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private fun pagerAdapter() {
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
        viewPager.currentItem = positionFromIntent
        viewPager.offscreenPageLimit= ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
    }

    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES
        override fun createFragment(position: Int) =  DataFragment.newInstance(itemListFromIntent, position)

    }

}
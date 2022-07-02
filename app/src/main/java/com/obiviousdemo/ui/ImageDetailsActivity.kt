package com.obiviousdemo.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.obiviousdemo.R
import com.obiviousdemo.data.model.NasaPicModelItem
import com.obiviousdemo.utils.Constants

class ImageDetailsActivity : AppCompatActivity() {

    lateinit var itemListFromIntent: ArrayList<NasaPicModelItem>
    var positionFromIntent: Int = 0
    lateinit var toolbar: Toolbar

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_details)
        toolbar = findViewById(R.id.toolbar_actionbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Detail Page")
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_24));
        getIntentValues()
        onClickEvents()

    }

    private fun getIntentValues() {
        val intent = getIntent().extras
        itemListFromIntent =
            intent?.getSerializable(Constants.INTENT_LIST) as ArrayList<NasaPicModelItem>
        positionFromIntent = intent!!.getInt(Constants.INTENT_ITEM_POSITION)

//        Log.v("ImageDetailsActivity", "ArrayList ::: " + itemListFromIntent.toString())
//        Log.v("ImageDetailsActivity", "ArrayList size::: " + itemListFromIntent.size.toString())
//        Log.v("ImageDetailsActivity", "ArrayList position::: " + positionFromIntent)

    }

    private fun onClickEvents() {
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            onBackPressed()
        })
    }
}
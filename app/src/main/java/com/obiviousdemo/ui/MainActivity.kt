package com.obiviousdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.obiviousdemo.MyApplicationClass
import com.obiviousdemo.R
import com.obiviousdemo.data.model.NasaPicModelItem
import com.obiviousdemo.data.repository.DataRepository
import com.obiviousdemo.ui.adapter.NasaPicRecycleAdapter
import com.obiviousdemo.viewmodel.NasaPicViewModel
import com.obiviousdemo.viewmodel.NasaPicViewModelFactory

@BindView(R.id.recycle_nasa_pic_list)
lateinit var recycleNadaPicList: RecyclerView

lateinit var nasaPicViewModel: NasaPicViewModel
lateinit var repository: DataRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bind = ButterKnife.bind(this)
        repository = (application as MyApplicationClass).dataRepository
        nasaPicViewModel = ViewModelProvider(
            this,
            NasaPicViewModelFactory(repository)
        ).get(NasaPicViewModel::class.java)

        setRecycleView()

    }

    fun setRecycleView() {
        recycleNadaPicList = findViewById(R.id.recycle_nasa_pic_list)
        nasaPicViewModel.nasaPicList.observe(this, {
            val itemList: ArrayList<NasaPicModelItem> = it
            if (itemList.size > 0) {
                itemList.sortByDescending { it.date }
                if (recycleNadaPicList.adapter == null) {
                    recycleNadaPicList.adapter = NasaPicRecycleAdapter(itemList, this)
                    recycleNadaPicList.layoutManager = LinearLayoutManager(this)
                } else {
                    recycleNadaPicList.adapter!!.notifyDataSetChanged()
                }
            }
        })

    }
}
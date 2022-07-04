package com.obiviousdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.obiviousdemo.MyApplicationClass
import com.obiviousdemo.R
import com.obiviousdemo.data.model.NasaPicModel
import com.obiviousdemo.data.model.NasaPicModelItem
import com.obiviousdemo.data.repository.DataRepository
import com.obiviousdemo.ui.adapter.NasaPicRecycleAdapter
import com.obiviousdemo.ui.interfaces.GenericListClickListner
import com.obiviousdemo.utils.Constants
import com.obiviousdemo.utils.Helper
import com.obiviousdemo.viewmodel.NasaPicViewModel

class MainActivity : AppCompatActivity(), GenericListClickListner {

    private lateinit var recycleNadaPicList: RecyclerView
    private lateinit var nasaPicViewModel: NasaPicViewModel
    lateinit var repository: DataRepository
    private lateinit var itemPicList: ArrayList<NasaPicModelItem>
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar_actionbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Home")
        repository = (application as MyApplicationClass).dataRepository
        /*nasaPicViewModel = ViewModelProvider(
            this,
            NasaPicViewModelFactory(repository)
        ).get(NasaPicViewModel::class.java)*/

        nasaPicViewModel = NasaPicViewModel(getJsonDataFromAsset(),repository)
        setRecycleView()

    }

    private fun getJsonDataFromAsset(): String? =
        Helper.getJsonFromAssets(this, "data.json")

    /**
     * Observes liveData and set list to recycleView adapter
     */
    fun setRecycleView() {
        recycleNadaPicList = findViewById(R.id.recycle_nasa_pic_list)
        nasaPicViewModel.nasaPicList.observe(this) {
            itemPicList = it
            if (itemPicList.size > 0) {
                itemPicList.sortByDescending { it.date }
                if (recycleNadaPicList.adapter == null) {
                    recycleNadaPicList.adapter = NasaPicRecycleAdapter(itemPicList, this)
                    recycleNadaPicList.layoutManager = LinearLayoutManager(this)
                } else {
                    recycleNadaPicList.adapter?.notifyDataSetChanged()
                }
            }
        }
    }


    /***
     * @param obj: Object from list as per position from adapter
     * @param action : action value passed from adapter
     * @param position : position of item in the list
     */
    override fun itemClicked(obj: Any?, position: Int, action: Int, additional: Any?) {
        val itemClicked: NasaPicModelItem = obj as NasaPicModelItem
        if (itemClicked != null) {
            if (action == 1) {
                val intent = Intent(this, ImageDetailsActivity::class.java)
                intent.putExtra(Constants.INTENT_ITEM_POSITION, position)
                intent.putExtra(Constants.INTENT_LIST, itemPicList)
                startActivity(intent)
            }
        }
    }
}
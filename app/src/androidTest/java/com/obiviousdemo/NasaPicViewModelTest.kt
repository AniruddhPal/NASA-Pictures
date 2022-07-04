package com.obiviousdemo

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.obiviousdemo.data.repository.DataRepository
import com.obiviousdemo.utils.Helper
import com.obiviousdemo.viewmodel.NasaPicViewModel
import junit.framework.TestCase
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NasaPicViewModelTest : TestCase() {

    private lateinit var viewModel: NasaPicViewModel
    private lateinit var repository: DataRepository

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        repository = DataRepository()
        viewModel = NasaPicViewModel(Helper.getJsonFromAssets(context, "data.json"), repository)

    }

    @Test
    fun fetchingDataFromFile() {
        val result = viewModel.nasaPicList.getOrAwaitValue()
        assertTrue(!result.isEmpty())
    }
}
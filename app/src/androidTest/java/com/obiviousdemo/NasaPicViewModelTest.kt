package com.obiviousdemo

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.obiviousdemo.data.repository.DataRepository
import com.obiviousdemo.viewmodel.NasaPicViewModel
import junit.framework.TestCase
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NasaPicViewModelTest(@get:Rule val instantTaskExecutorRule: InstantTaskExecutorRule) : TestCase() {

    private lateinit var viewModel: NasaPicViewModel
    private lateinit var repository: DataRepository

    @Before
    override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        repository = DataRepository(context)
        viewModel = NasaPicViewModel(repository)


    }

    @Test
    fun fetchingDataFromFile() {
        val result = viewModel.nasaPicList.getOrAwaitValue()
        assertTrue(!result.isEmpty())
    }
}
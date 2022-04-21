package com.example.pushnotification.viewmodeltest

import com.example.pushnotification.repository.FRepository
import com.example.pushnotification.viewmodel.FViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ViewModelTest {
    @MockK
    private lateinit var repository: FRepository
    private lateinit var viewmodel:FViewModel
   @Before
   fun SetUp(){
       MockKAnnotations.init(this)
viewmodel= FViewModel(repository)   }
    @Test
    fun postData_is_Not_Null(){
        coEvery { repository.postData() }returns Unit
        runBlocking {
            viewmodel.postData()
        }
        coVerify { repository.postData() }
     }
}
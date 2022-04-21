package com.example.pushnotification.repositorytest

import com.example.pushnotification.repository.FRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import org.junit.Before
import org.junit.Test

class RepositoryTest {

    lateinit var repository:FRepository

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
        repository= FRepository()
    }
    @Test
    fun postData_is_Not_Null(){
        val post=repository.postData()
        assertThat(post).isNotNull()
    }
}
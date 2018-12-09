package com.dj.api

import com.dj.api.Repository.PreorderEntity
import com.dj.api.Repository.PreorderRepository
import com.dj.api.Service.PreorderService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.junit.Assert.*
import org.springframework.boot.test.mock.mockito.MockBean
import org.mockito.Mockito
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@RunWith(SpringRunner::class)
class PreorderServiceTests {

    @MockBean
    lateinit var preorderRepository: PreorderRepository

    private val preorders = listOf(
            PreorderEntity("7", regularPrice = 70),
            PreorderEntity("4", regularPrice = 40),
            PreorderEntity("1", regularPrice = 10),
            PreorderEntity("2", regularPrice = 20),
            PreorderEntity("8", regularPrice = 80)
    )

    @Test
    fun givenEmptyList_whenGetFirstPage_thenReturnEmptyList() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(emptyList())

        val result = PreorderService(preorderRepository).findPreorders(PageRequest.of(0, 3))

        assertEquals(result, listOf<PreorderEntity>())
    }

    @Test
    fun givenTwoPages_whenGetFirstPage_thenReturnThreeElements() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(preorders)

        val result = PreorderService(preorderRepository).findPreorders(PageRequest.of(0, 3))

        assertEquals(result, listOf(preorders[0], preorders[1], preorders[2]))
    }

    @Test
    fun givenTwoPages_whenGetLastPage_thenReturnTwoElements() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(preorders)

        val result = PreorderService(preorderRepository).findPreorders(PageRequest.of(1, 3))

        assertEquals(result, listOf(preorders[3], preorders[4]))
    }

    @Test
    fun givenTwoPages_whenGetFirstPage_thenReturnCheapest() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(preorders)

        val result = PreorderService(preorderRepository).findPreorders(
                PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "regularPrice"))
        )

        assertEquals(result, listOf(preorders[2], preorders[3], preorders[1]))
    }

}

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
import java.math.BigDecimal

@RunWith(SpringRunner::class)
class PreorderServiceTests {

    @MockBean
    lateinit var preorderRepository: PreorderRepository

    private val preorders = listOf(
            PreorderEntity(7, regularPrice = BigDecimal(70)),
            PreorderEntity(4, regularPrice = BigDecimal(40)),
            PreorderEntity(1, regularPrice = BigDecimal(10)),
            PreorderEntity(2, regularPrice = BigDecimal(20)),
            PreorderEntity(8, regularPrice = BigDecimal(80))
    )

    @Test
    fun givenEmptyList_whenGetFirstPage_thenReturnEmptyList() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(emptyList())

        val result = PreorderService(preorderRepository).findPreorders(PageRequest.of(0, 3))

        assertEquals(result.content, listOf<PreorderEntity>())
    }

    @Test
    fun givenTwoPages_whenGetFirstPage_thenReturnThreeElements() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(preorders)

        val result = PreorderService(preorderRepository).findPreorders(PageRequest.of(0, 3))

        assertEquals(result.content, listOf(preorders[0], preorders[1], preorders[2]))
    }

    @Test
    fun givenTwoPages_whenGetLastPage_thenReturnTwoElements() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(preorders)

        val result = PreorderService(preorderRepository).findPreorders(PageRequest.of(1, 3))

        assertEquals(result.content, listOf(preorders[3], preorders[4]))
    }

    @Test
    fun givenTwoPages_whenGetFirstPage_thenReturnCheapest() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(preorders)

        val result = PreorderService(preorderRepository).findPreorders(
                PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "regularPrice"))
        )

        assertEquals(result.content, listOf(preorders[2], preorders[3], preorders[1]))
    }

    @Test
    fun givenList_whenGetById_thenReturnPreorder() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(preorders)

        val result = PreorderService(preorderRepository).findPreorder(1)

        assertEquals(result, preorders[2])
    }

    @Test
    fun givenList_whenGetNonExisting_thenReturnNull() {
        Mockito.`when`(preorderRepository.findPreorders())
                .thenReturn(preorders)

        val result = PreorderService(preorderRepository).findPreorder(111)

        assertNull(result)
    }

}

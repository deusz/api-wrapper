package com.dj.api.Service

import com.dj.api.Repository.PreorderEntity
import com.dj.api.Repository.PreorderRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import sun.text.normalizer.UCharacter.getDirection
import kotlin.reflect.full.memberProperties


@Service
class PreorderService(val repository: PreorderRepository) {

    fun findPreorders(pageable: Pageable): List<PreorderEntity>? {
        val list = repository.findPreorders()

        pageable.sort.forEach { order ->
            if (order.isAscending)
                list.sortedBy { it -> it::class.memberProperties.find { it.name == order.property }.toString() }
            else
                list.sortedByDescending { it -> it::class.memberProperties.find { it.name == order.property }.toString() }
        }

        return list.slice(IntRange(pageable.offset.toInt(), pageable.offset.toInt() + pageable.pageSize))
    }

    fun findPreorder(id: Int): PreorderEntity? =
            repository.findPreorder(id)
}


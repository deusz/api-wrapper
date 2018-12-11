package com.dj.api.Service

import com.dj.api.Model.PreorderEntity
import com.dj.api.Repository.PreorderRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.math.max
import kotlin.math.min

@Service
class PreorderService(val repository: PreorderRepository) {

    fun findPreorders(pageable: Pageable): Page<PreorderEntity> {
        var list = repository.findPreorders()

        pageable.sort.forEach { order ->
            val comparator: Comparator<PreorderEntity>? = when (order.property) {
                "regularPrice" -> compareBy { it.regularPrice }
                "name" -> compareBy { it.name }
                else -> null
            }
            if (comparator != null)
                list = list.sortedWith(if (order.isAscending) comparator else comparator.reversed())
        }

        val range = IntRange(
                min(max(list.size -1, 0), pageable.offset.toInt()),
                min(list.size -1, pageable.offset.toInt() + pageable.pageSize - 1)
        )

        return PageImpl(list.slice(range), pageable, list.size.toLong())
    }

    fun findPreorder(id: Int): PreorderEntity? =
            repository.findPreorders().find { it.id == id }
}


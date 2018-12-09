package com.dj.api.Service

import com.dj.api.Repository.PreorderEntity
import com.dj.api.Repository.PreorderRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.math.max
import kotlin.math.min

@Service
class PreorderService(val repository: PreorderRepository) {

    fun findPreorders(pageable: Pageable): List<PreorderEntity>? {
        var list = repository.findPreorders()

        pageable.sort.forEach { order ->
            val comparator: Comparator<PreorderEntity>? = when (order.property) {
                "regularPrice" -> compareBy { it.regularPrice }
                else -> null
            }
            if (comparator != null)
                list = list.sortedWith(if (order.isAscending) comparator else comparator.reversed())
        }

        val range = IntRange(
                min(max(list.size -1, 0), pageable.offset.toInt()),
                min(list.size -1, pageable.offset.toInt() + pageable.pageSize - 1)
        )
        return list.slice(range)
    }

    fun findPreorder(id: Int): PreorderEntity? =
            repository.findPreorder(id)
}


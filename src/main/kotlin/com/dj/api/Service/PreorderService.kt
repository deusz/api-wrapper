package com.dj.api.Service

import com.dj.api.Repository.PreorderEntity
import com.dj.api.Repository.PreorderRepository
import org.springframework.stereotype.Service

@Service
class PreorderService(val repository: PreorderRepository) {

    fun findPreorders(page: Int?, sortBy: String?, sortDir: String?): List<PreorderEntity>? =
            repository.findPreorders(page, 12, sortBy, sortDir)

    fun findPreorder(id: Int): PreorderEntity? =
            repository.findPreorder(id)
}

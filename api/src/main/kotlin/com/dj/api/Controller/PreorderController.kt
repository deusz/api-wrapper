package com.dj.api.Controller

import com.dj.api.Repository.PreorderEntity
import com.dj.api.Service.PreorderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resource
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.notFound
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping(name = "preorders", path = ["/api/v2/preorders"])
class PreorderController(
        val assembler: PreorderResourceAssembler,
        val preorderService: PreorderService
) {

    @RequestMapping(method = [RequestMethod.GET])
    fun getPreorders(@PageableDefault pageable: Pageable, pageAssembler: PagedResourcesAssembler<PreorderResource> ): PagedResources<Resource<PreorderResource>> =
            pageAssembler.toResource(
                    preorderService.findPreorders(pageable).map { assembler.toResource(it) }
            )

    @RequestMapping("{entityId}", method = [RequestMethod.GET])
    fun getPreorder(@PathVariable("entityId") id: Int): ResponseEntity<PreorderDetailsResource?> =
            when (val preorder = preorderService.findPreorder(id)) {
                is PreorderEntity -> ok(assembler.toDetailsResource(preorder))
                else -> notFound().build()
            }
}

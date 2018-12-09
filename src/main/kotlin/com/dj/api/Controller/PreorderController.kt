package com.dj.api.Controller

import com.dj.api.Repository.PreorderEntity
import com.dj.api.Service.PreorderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.hateoas.Resources
import org.springframework.http.ResponseEntity.ok

data class Pag(val page: Int?, val sortBy: String?)

@RestController
//@RequestMapping(name = "preorders", path = arrayOf("/api/v2/preorders"))
class PreorderController(val assembler: PreorderResourceAssembler, val preorderService: PreorderService) {

    @GetMapping
    @RequestMapping("/api/v2/preorders")
    fun getPreorders(
            @RequestParam("page") page: Int?,
            @RequestParam("sort.by") sortBy: String?,
            @RequestParam("sort.dir") sortDir: String?
    ): ResponseEntity<Resources<PreorderResource>> {

        val resources = assembler.toResources(preorderService.findPreorders(page, sortBy, sortDir)!!.asIterable(), page, sortBy, sortDir)

        return ok(resources)
    }

    @GetMapping
    @RequestMapping("/api/v2/preorders/{id}")
    fun getPreorder(@PathVariable("id") id: String): ResponseEntity<PreorderResource> =
            ok(assembler.toResource(PreorderEntity(id)))

}

package com.dj.api.Controller

import com.dj.api.Model.Preorder
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.hateoas.Resources
import org.springframework.http.ResponseEntity.ok

data class Pag(val page: Int?, val sortBy: String?)

@RestController
//@RequestMapping(name = "preorders", path = arrayOf("/api/v2/preorders"))
class PreorderController(val assembler: PreorderResourceAssembler) {

    @GetMapping
    @RequestMapping("/api/v2/preorders")
    fun getPreorders(
            @RequestParam("page") page: Int?,
            @RequestParam("sort.by") sortBy: String?,
            @RequestParam("sort.dir") sortDir: String?
    ): ResponseEntity<Resources<PreorderResource>> {

        val resources = assembler.toResources(arrayOf(Preorder("1222", 1)).asIterable(), page, sortBy, sortDir)

        return ok(resources)
    }
}

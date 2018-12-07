package com.dj.api.Controller

import com.dj.api.Model.Preorder
import org.springframework.hateoas.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Resources
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity.ok


@RestController
//@RequestMapping(name = "preorders", path = arrayOf("/api/v2/preorders"))
class PreorderController(val preorderResourceAssembler: PreorderResourceAssembler) {


    @GetMapping
    @RequestMapping("/api/v2/preorders")
    fun getPreorders(): ResponseEntity<Resources<PreorderResource>> {

        val resources = preorderResourceAssembler.toResources(arrayOf(Preorder("1222", 1)).asIterable())

        resources.add(linkTo(methodOn(PreorderController::class.java).getPreorders()).withSelfRel())

        return ok(resources)

//        val r = ResponseEntity.ok(Resource(arrayOf(Preorder("1222",1))))
//
//        r.add(linkTo(methodOn(GreetingController::class.java).greeting(`var`)).withSelfRel());
//
//        return r
    }
}

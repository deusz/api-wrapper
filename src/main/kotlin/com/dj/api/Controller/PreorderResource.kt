package com.dj.api.Controller

import com.dj.api.Model.Preorder
import org.springframework.hateoas.ResourceSupport

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.Resources
import org.springframework.hateoas.core.Relation
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Relation(collectionRelation = "preorders")
data class PreorderResource(val name: String) : ResourceSupport()

@Component
class PreorderResourceAssembler : ResourceAssembler<Preorder, PreorderResource> {

    fun toResources(products: Iterable<Preorder>): Resources<PreorderResource> = Resources(products.map { toResource(it) })

    override fun toResource(preorder: Preorder): PreorderResource {
        val resource = PreorderResource(name = preorder.id)

//        resource.add(linkTo(methodOn(PreorderController::class.java).getPreorders()).withSelfRel())

        return resource
    }

    fun toPreorder(resource: PreorderResource): Preorder = Preorder(
            id = resource.name,
            price = 2)
}
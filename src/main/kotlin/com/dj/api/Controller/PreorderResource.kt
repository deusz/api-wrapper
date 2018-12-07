package com.dj.api.Controller

import com.dj.api.Model.Preorder
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.ResourceSupport

import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.Resources
import org.springframework.hateoas.core.Relation
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Relation(collectionRelation = "preorders")
data class PreorderResource(val name: String) : ResourceSupport()

@Component
class PreorderResourceAssembler : ResourceAssembler<Preorder, PreorderResource> {

    fun toResources(preorder: Iterable<Preorder>, page: Int?, sortBy: String?, sortDir: String?): Resources<PreorderResource> {
        val resources = Resources(preorder.map { toResource(it) })

        resources.add(
                linkTo(methodOn(PreorderController::class.java).getPreorders(page, sortBy, sortDir)).withSelfRel())

        return resources
    }

    override fun toResource(preorder: Preorder): PreorderResource {
        val resource = PreorderResource(name = preorder.id)

//        resource.add(linkTo(methodOn(PreorderController::class.java).getPreorders()).withSelfRel())

        return resource
    }

    fun toPreorder(resource: PreorderResource): Preorder = Preorder(
            id = resource.name,
            price = 2)
}
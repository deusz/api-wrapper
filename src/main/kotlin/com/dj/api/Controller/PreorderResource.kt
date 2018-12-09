package com.dj.api.Controller

import com.dj.api.Repository.PreorderEntity
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.Resources
import org.springframework.hateoas.core.Relation
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component

@Relation(collectionRelation = "preorders")
data class PreorderResource(val name: String) : ResourceSupport()

@Component
class PreorderResourceAssembler : ResourceAssembler<PreorderEntity, PreorderResource> {

    fun toResources(preorder: Iterable<PreorderEntity>, page: Int?, sortBy: String?, sortDir: String?): Resources<PreorderResource> {
        val resources = Resources(preorder.map { toResource(it) })

        if (page != null && page > 1) {
            resources.add(
                    linkTo(methodOn(PreorderController::class.java).getPreorders(page - 1, sortBy, sortDir)).withSelfRel())
        }
        resources.add(
                linkTo(methodOn(PreorderController::class.java).getPreorders(page, sortBy, sortDir)).withSelfRel())
        resources.add(
                linkTo(methodOn(PreorderController::class.java).getPreorders(page?.plus(1), sortBy, sortDir)).withRel("next"))

        return resources
    }

    override fun toResource(preorder: PreorderEntity): PreorderResource {
        val resource = PreorderResource(name = preorder.id)

        resource.add(linkTo(methodOn(PreorderController::class.java).getPreorder(preorder.id)).withSelfRel())

        return resource
    }

}
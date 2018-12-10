package com.dj.api.Controller

import com.dj.api.Repository.PreorderEntity
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.Resources
import org.springframework.hateoas.core.Relation
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component
import java.math.BigDecimal

//@ Relation(collectionRelation = "preorders")
data class PreorderResource(
        val id: Int,
        val name: String?,
        val imageUrl: String?,
        val regularPrice: BigDecimal?,
        val minPrice: BigDecimal?
): ResourceSupport()

data class PreorderDetailsResource(
        val id: Int,
        val name: String?,
        val imageUrl: String?,
        val description: String?,
        val regularPrice: BigDecimal?,
        val minPrice: BigDecimal?
): ResourceSupport()

@Component
class PreorderResourceAssembler : ResourceAssembler<PreorderEntity, PreorderResource> {

    override fun toResource(preorder: PreorderEntity): PreorderResource {
        val resource = PreorderResource(
                id = preorder.id,
                name = preorder.name,
                imageUrl = preorder.imageUrl,
                regularPrice = preorder.regularPrice,
                minPrice = preorder.minPrice
        )

        resource.add(linkTo(methodOn(PreorderController::class.java).getPreorder(preorder.id)).withSelfRel())

        return resource
    }

    fun toDetailsResource(preorder: PreorderEntity): PreorderDetailsResource {
        val resource = PreorderDetailsResource(
                id = preorder.id,
                name = preorder.name,
                imageUrl = preorder.imageUrl,
                regularPrice = preorder.regularPrice,
                minPrice = preorder.minPrice,
                description = preorder.description
        )

        resource.add(linkTo(methodOn(PreorderController::class.java).getPreorder(preorder.id)).withSelfRel())

        return resource
    }

}
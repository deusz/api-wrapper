package com.dj.api.Controller

import com.dj.api.Repository.PreorderEntity
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.ResourceAssembler
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.stereotype.Component
import java.math.BigDecimal

data class PreorderResource(
        val entityId: Int,
        val name: String?,
        val imageUrl: String?,
        val regularPrice: BigDecimal?,
        val minPrice: BigDecimal?
): ResourceSupport()

data class PreorderDetailsResource(
        val entityId: Int,
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
                entityId = preorder.id,
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
                entityId = preorder.id,
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
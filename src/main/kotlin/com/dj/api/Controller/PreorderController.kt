package com.dj.api.Controller

import com.dj.api.Model.Preorder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(name = "preorders", path = arrayOf("/api/v2/preorders"))
class PreorderController {

    @GetMapping
    fun getPreorders(): Array<Preorder> {
        return arrayOf(Preorder("1222"))
    }
}

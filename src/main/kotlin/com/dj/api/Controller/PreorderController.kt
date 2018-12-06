package com.dj.api.Controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(name = "preorders", path = arrayOf("/api/v2/preorders"))
class PreorderController {

    @GetMapping
    fun create(@RequestParam params: Map<String, String>): Map<String, String> {
        return params
    }
}

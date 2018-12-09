package com.dj.api.Repository

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalTime
import java.time.ZonedDateTime

class PreorderEntity(
        @JsonProperty("entity_id") val id: String,
        @JsonProperty("activation_details") val activationDetails: String? = null,
        @JsonProperty("custom_bg") val customBg: String? = null,
        @JsonProperty("custom_bg_left") val customBgLeft: String? = null,
        @JsonProperty("custom_bg_right") val customBgRight: String? = null,
        @JsonProperty("description") val description: String? = null,
        @JsonProperty("frontpage_image") val frontpageImage: String? = null,
        @JsonProperty("image") val image: String? = null,
        @JsonProperty("is_active") val isActive: String? = null,
        @JsonProperty("is_indie_valley") val isIndieValley: String? = null,
        @JsonProperty("is_ingame") val isIngame: String? = null,
        @JsonProperty("is_steamgift") val isSteamgift: String? = null,
        @JsonProperty("metacritic_score") val metacriticScore: String? = null,
        @JsonProperty("name") val name: String? = null,
        @JsonProperty("news_from_date") val newsFromDate: ZonedDateTime? = null,
        @JsonProperty("news_to_date") val newsToDate: ZonedDateTime? = null,
        @JsonProperty("note") val note: String? = null,
        @JsonProperty("origin_score") val originScore: String? = null,
        @JsonProperty("platform") val platform: String? = null,
        @JsonProperty("preorder_from_date") val preorderFromDate: ZonedDateTime? = null,
        @JsonProperty("preorder_to_date") val preorderToDate: ZonedDateTime? = null,
        @JsonProperty("promote_from_date") val promoteFromDate: ZonedDateTime? = null,
        @JsonProperty("promote_image") val promoteImage: String? = null,
        @JsonProperty("promote_to_date") val promoteToDate: ZonedDateTime? = null,
        @JsonProperty("region") val region: String? = null,
        @JsonProperty("regular_price") val regularPrice: String? = null,
        @JsonProperty("release_date") val releaseDate: ZonedDateTime? = null,
        @JsonProperty("steam_app_id") val steamAppId: String? = null,
        @JsonProperty("steam_score") val steamScore: String? = null,
        @JsonProperty("share_url") val shareUrl: String? = null,
        @JsonProperty("min_price") val minPrice: String? = null
)
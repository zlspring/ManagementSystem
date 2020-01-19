package com.aohui.btcorg.model.http

import com.aohui.btcorg.model.dto.AccountInfoDto
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class SignUpRequest(
        var username:String? = null,
        var password:String? = null
)
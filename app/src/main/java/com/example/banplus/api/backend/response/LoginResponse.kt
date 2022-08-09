package com.example.banplus.api.backend.response

import com.google.gson.annotations.SerializedName


data class LoginResponse (

    @SerializedName("detail"  ) var detail  : String? = null,
    @SerializedName("refresh" ) var refresh : String? = null,
    @SerializedName("access"  ) var access  : String? = null

)
package com.jackpan.stockcomputer.model

data class UserData(
    var phoneNumber : String?,
    var creationTimestamp : Long?,
    var lastSignInTimestamp : Long?,
    var uid : String?
    )
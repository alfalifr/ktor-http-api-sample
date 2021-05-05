package com.jetbrains.handson.httpapi.data

import com.jetbrains.handson.httpapi.data.model.Customer

object StaticData {
    val custsData = mutableListOf<Customer>(
        Customer("1", "halo", "bro", "eyo"),
        Customer("2", "ok", "lah", "eya"),
    )
}
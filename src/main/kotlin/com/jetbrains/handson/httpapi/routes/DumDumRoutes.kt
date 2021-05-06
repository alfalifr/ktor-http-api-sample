package com.jetbrains.handson.httpapi.routes

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.dummyRoutes() {
    route("/dummy") {
        post("/oe") {
            val str = call.receiveText()
            call.respondText(str)
        }
    }
}
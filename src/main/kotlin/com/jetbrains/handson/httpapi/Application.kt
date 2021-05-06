package com.jetbrains.handson.httpapi

import com.jetbrains.handson.httpapi.routes.customerRouting
import com.jetbrains.handson.httpapi.routes.dummyRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*



fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(ContentNegotiation){
        json()
    }
    registerCustomerRoutes()
    dummyRoutes()
}

fun Application.registerCustomerRoutes(){
    routing {
        customerRouting()
    }
}

fun Application.defaultRoutes(){
    routing {
        get("/"){
            call.respondText("halo bro")
        }
    }
}

fun Application.dummyRoutes(){
    routing {
        this.dummyRoutes()
    }
}

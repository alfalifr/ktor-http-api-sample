package com.jetbrains.handson.httpapi.routes

import com.jetbrains.handson.httpapi.data.StaticData.custsData
import com.jetbrains.handson.httpapi.data.model.Customer
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.customerRouting() {
    route("/customer") {
        get {
            if(custsData.isNotEmpty())
                call.respond(custsData)
            else
                call.respondText("Customers Not Found", status = HttpStatusCode.NotFound)
        }
        get("{id}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Expecting for customer id",
                status = HttpStatusCode.BadRequest,
            )
            val cust = custsData.find { it.id == id } ?: return@get call.respondText(
                "No customer with id '$id' found.",
                status = HttpStatusCode.NotFound,
            )
            call.respond(cust)
        }
        post {
            try {
                val cust = call.receive<Customer>()
                custsData += cust
                call.respondText(
                    "Customer '$cust' is added.",
                    status = HttpStatusCode.Created
                )
            } catch (t: Throwable){
                call.respondText(
                    "Error, t= $t",
                    status = HttpStatusCode.BadRequest
                )
            }
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Expecting for a customer ID.",
                status = HttpStatusCode.BadRequest,
            )

            if(custsData.removeIf { it.id == id }){
                call.respondText("Customer with id '$id' deleted.", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("No customer with id '$id' found.", status = HttpStatusCode.NotFound)
            }
        }
    }
}
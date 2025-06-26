package com.myapp

import com.github.pambrose.common.json.toJsonElement
import com.vapi4k.dsl.call.VapiApiImpl.Companion.vapiApi
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.runBlocking

object CallCustomer {
  @JvmStatic
  fun main(args: Array<String>) {
    val callResp =
      vapiApi()
        .phone {
          outboundCall {
            serverPath = "/callCustomer"
            // Change this number appropriately
            phoneNumber = "+14155551212"
          }
        }

    runBlocking {
      println("Call status: ${callResp.status}")
      println("Call response: ${callResp.bodyAsText().toJsonElement()}")
    }
  }
}
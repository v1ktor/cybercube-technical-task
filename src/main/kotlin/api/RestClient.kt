package com.cybercube.api

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json

class RestClient {
    fun init(config: RestConfig): HttpClient {
        val client = HttpClient {
            expectSuccess = false

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.BODY
            }

            defaultRequest {
                url(config.baseUrl)
                headers.appendIfNameAbsent(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
        }

        return client;
    }
}

fun givenRestClient(config: RestConfig = RestConfig()): HttpClient {
    return RestClient().init(config)
}

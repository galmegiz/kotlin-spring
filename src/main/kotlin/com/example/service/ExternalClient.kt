package com.example.service

interface ExternalClient {
    fun requestEx(request: String): String
    fun request(request: String): String
    fun timeout(param: String): String
}
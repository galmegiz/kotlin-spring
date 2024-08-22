package com.example.service

interface ExternalClient {
    fun requestEx(request: String): String
}
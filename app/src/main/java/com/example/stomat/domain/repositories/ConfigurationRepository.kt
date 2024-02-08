package com.example.stomat.domain.repositories

interface ConfigurationRepository {
    suspend fun fetchConfig(): String
}
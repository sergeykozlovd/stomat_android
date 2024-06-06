package com.examples.stomat.domain.repositories

interface ConfigurationRepository {
    suspend fun fetchConfig(): String
}
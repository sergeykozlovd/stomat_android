package com.example.stomat.data.implementations

import com.example.stomat.domain.repositories.ConfigurationRepository

class ConfigurationRepositoryImpl: ConfigurationRepository {
    override suspend fun fetchConfig():String {
        //get data from roomDataSource
        return "data from fetchConfig"
    }
}
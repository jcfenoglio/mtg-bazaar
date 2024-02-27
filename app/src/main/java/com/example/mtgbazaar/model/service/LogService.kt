package com.example.mtgbazaar.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}
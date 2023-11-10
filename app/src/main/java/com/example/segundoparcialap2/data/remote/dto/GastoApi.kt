package com.example.segundoparcialap2.data.remote.dto

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GastoApi {
    @GET("api/Gastos")
    suspend fun getGasto(): List<GastoDto>

    @GET("api/Gastos/{id}")
    suspend fun getGastoById(@Path("id") gastoId: Int): GastoDto

    @POST("api/Gastos")
    suspend fun postGasto(@Body gasto: GastoDto): Response<GastoDto>

    @DELETE("api/Gastos/{id}")
    suspend fun deleteGasto(@Path("id") gastoId: Int): Response<GastoDto>

    @PUT("api/Gasto/{id}")
    suspend fun putGasto(@Path("id") gastoId: Int, @Body gastoDto: GastoDto): Response<Unit>
}
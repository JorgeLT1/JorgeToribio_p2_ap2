package com.example.segundoparcialap2.data.repository

import com.example.segundoparcialap2.data.remote.dto.GastoApi
import com.example.segundoparcialap2.data.remote.dto.GastoDto
import com.example.segundoparcialap2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GastoRepository @Inject constructor(private val api: GastoApi) {

    fun getGasto(): Flow<Resource<List<GastoDto>>> = flow {
        try {
            emit(Resource.Loading())
            val gasto = api.getGasto()
            emit(Resource.Success(gasto))
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    fun getGastoById(id: Int): Flow<Resource<GastoDto>> = flow {
        try {
            emit(Resource.Loading())
            val gastoId = api.getGastoById(id)
            emit(Resource.Success(gastoId))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postGasto(gasto: GastoDto) = api.postGasto(gasto)
    suspend fun deleteGasto(id: Int, gasto: GastoDto) = api.deleteGasto(id, gasto)
}
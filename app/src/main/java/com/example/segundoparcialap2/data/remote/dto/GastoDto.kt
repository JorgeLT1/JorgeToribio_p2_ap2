package com.example.segundoparcialap2.data.remote.dto

data class GastoDto(
    val idGasto: Int?=null,
    val fecha: String = "",
    val suplidor:String = "",
    val nfc: String = "",
    val concepto: String = "",
    val descuento: Int?= null,
    val itbis: Int?= null,
    val monto: Int?= null,
)
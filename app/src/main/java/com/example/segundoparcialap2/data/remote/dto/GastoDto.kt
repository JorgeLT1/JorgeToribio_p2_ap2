package com.example.segundoparcialap2.data.remote.dto

data class GastoDto(
    val idGasto: Int?=null,
    var fecha: String = "",
    var ncf: String = "",
    var idSuplidor: Int?= null,
    var suplidor:String = "",
    var concepto: String = "",
    var descuento: Int?= 0,
    var itbis: Int?= null,
    var monto: Int?= null,
)
package com.example.segundoparcialap2.data.remote.dto

data class GastoDto(
    val idGasto: Int?=null,
    var fecha: String = "",
    var idSuplidor: Int?= null,
    var suplidor:String = "",
    var nfc: String = "",
    var concepto: String = "",
    var descuento: Int?= null,
    var itbis: Int?= null,
    var monto: Int?= null,
)
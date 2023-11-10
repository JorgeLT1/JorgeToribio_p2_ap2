package com.example.segundoparcialap2.ui.theme.Gasto

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.segundoparcialap2.data.remote.dto.GastoDto
import com.example.segundoparcialap2.data.repository.GastoRepository
import com.example.segundoparcialap2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class GastoListState(
    val isLoading: Boolean = false,
    val gasto: List<GastoDto> = emptyList(),
    val error: String = "",
)

@HiltViewModel
class GastoViewModel @Inject constructor(
    private val gastoRepository: GastoRepository
) : ViewModel() {
    var idGasto by mutableStateOf(0)
    var suplidor by mutableStateOf("")
    var ncf by mutableStateOf("")
    var concepto by mutableStateOf("")
    var descuento by mutableStateOf(1)
    var itbis by mutableStateOf(0)
    var monto by mutableStateOf(0)
    var fecha by mutableStateOf("")
    var idSuplidor by mutableStateOf(0)

    var verificarSuplidor by mutableStateOf(true)
    var verificarNfc by mutableStateOf(true)
    var verificarConcepto by mutableStateOf(true)
    var verificarItbis by mutableStateOf(true)
    var verificarMonto by mutableStateOf(true)
    var verificarFecha by mutableStateOf(true)
    var verficarIdSuplidor by mutableStateOf(true)

    private val _uiState = MutableStateFlow(GastoListState())
    val uiState: StateFlow<GastoListState> = _uiState.asStateFlow()
    fun validar(): Boolean {

        if(suplidor== "")
        {
            verificarSuplidor = false
        }
        else
        {
            verificarSuplidor = true
        }

        if(ncf == "")
        {
            verificarNfc = false
        }
        else
        {
            verificarNfc= true
        }

        if(concepto == "")
        {
            verificarConcepto = false
        }
        else
        {
            verificarConcepto = true
        }


        if(monto < 0)
        {
            verificarMonto = false
        }
        else
        {
            verificarMonto = true
        }

        if (fecha == "")
        {
            verificarFecha = false
        }
        else
        {
            verificarFecha = true
        }
        if(idSuplidor < 0)
        {
            verficarIdSuplidor = false
        }
        else
        {
            verficarIdSuplidor = true
        }

        return !(suplidor == "" || ncf == "" || concepto == "" || monto < 0 || fecha == "")
    }

    fun cargar() {
        gastoRepository.getGasto().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }

                is Resource.Success -> {
                    _uiState.update { it.copy(gasto = result.data ?: emptyList()) }
                }

                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        cargar()
    }

    fun save() {
        viewModelScope.launch {
            val gasto = GastoDto(
                idSuplidor = idSuplidor,
                suplidor = suplidor,
                ncf = ncf,
                concepto = concepto,
                descuento = descuento,
                itbis = itbis,
                monto = monto,
                fecha = fecha
            )
            gastoRepository.postGasto(gasto)
            limpiar()
            cargar()
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            gastoRepository.deleteGastos(id)
            cargar()
        }
    }

//    fun put() {
//        viewModelScope.launch {
//            val gasto = GastoDto(
//                idGasto = idGasto,
//                idSuplidor = idSuplidor,
//                suplidor = suplidor,
//                ncf = ncf,
//                concepto = concepto,
//                itbis = itbis,
//                monto = monto,
//                fecha = fecha
//            )
//            gastoRepository.putGasto(idGasto, gasto)
//            limpiar()
//            cargar()
//        }
//    }

    fun limpiar() {
        idSuplidor = 0
        suplidor = ""
        ncf = ""
        concepto = ""
        itbis = 0
        monto = 0
        fecha = ""
        idGasto = 0
    }


    private val _isMessageShown = MutableSharedFlow<Boolean>()
    val isMessageShownFlow = _isMessageShown.asSharedFlow()
    fun setMessageShown() {
        viewModelScope.launch {
            _isMessageShown.emit(true)
        }
    }

}
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
): ViewModel(){
    var suplidor by mutableStateOf("")
    var nfc by mutableStateOf("")
    var concepto by mutableStateOf("")
    var descuento by mutableStateOf(0)
    var itbis by mutableStateOf(0)
    var monto by mutableStateOf(0)
    var fecha by mutableStateOf("")
    private val _uiState = MutableStateFlow(GastoListState())
    val uiState: StateFlow<GastoListState> = _uiState.asStateFlow()

    init {
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

    fun save(){
        viewModelScope.launch {
            val gasto = GastoDto(
                suplidor = suplidor,
                nfc = nfc,
                concepto = concepto,
                descuento = descuento,
                itbis = itbis,
                monto = monto,
                fecha = fecha
            )
            gastoRepository.postGasto(gasto)
        }
    }


    fun delete(gastoId: Int, gastoDto: GastoDto) {
        viewModelScope.launch {
            gastoRepository.deleteGasto(gastoId, gastoDto)
        }
    }

    fun limpiar()
    {
        suplidor = ""
        nfc = ""
        concepto = ""
        descuento = 0
        itbis = 0
        monto = 0
        fecha  = ""
    }





}
package com.example.segundoparcialap2.ui.theme.Gasto

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.segundoparcialap2.data.remote.dto.GastoDto

@Composable
fun ConsultaGasto(gastos: List<GastoDto>)
{
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        LazyColumn(modifier = Modifier.fillMaxWidth())
        {
            items(gastos) { gasto ->
                Card (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
                {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    )
                    {
                      Text(text = "ID: " + gasto.idGasto, style = MaterialTheme.typography.titleMedium)
                      Text(text = gasto.fecha, style = MaterialTheme.typography.titleMedium)
                      Text(text = gasto.suplidor, style = MaterialTheme.typography.titleMedium)
                      Text(text = gasto.concepto, style = MaterialTheme.typography.titleMedium)
                      Text(text = "NCF: " + gasto.nfc, style = MaterialTheme.typography.titleMedium)
                      Text(text = "Itbis: " + gasto.itbis.toString(), style = MaterialTheme.typography.titleMedium)
                      Text(text = gasto.monto.toString(), style = MaterialTheme.typography.titleMedium)  
                    }
                }
            }
        }
    }
}

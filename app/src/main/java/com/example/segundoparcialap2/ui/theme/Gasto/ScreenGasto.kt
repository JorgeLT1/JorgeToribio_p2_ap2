package com.example.segundoparcialap2.ui.theme.Gasto

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.segundoparcialap2.R
import com.example.segundoparcialap2.data.remote.dto.GastoDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RegistroGasto(viewModel: GastoViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                snackbarHostState.showSnackbar(
                    message = "Gasto guardado.",
                    duration = SnackbarDuration.Short
                )
            }
        }

    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
    {
        Text(
            text = "Registro de gastos",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = viewModel.fecha,
                    onValueChange = { viewModel.fecha = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Fecha") },
                    singleLine = true
                )
            }
        }

        Row {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            {
                OutlinedTextField(
                    value = viewModel.idSuplidor.toString(),
                    onValueChange = {
                        val newValue = it.toIntOrNull()
                        if (newValue != null) {
                            viewModel.idSuplidor = newValue
                        }
                    },
                    label = { Text(text = "IdSuplidor") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            {
                OutlinedTextField(
                    value = viewModel.suplidor,
                    onValueChange = { viewModel.suplidor = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Suplidor") },
                    singleLine = true
                )
            }

        }

        Row {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            {
                OutlinedTextField(
                    value = viewModel.ncf,
                    onValueChange = { viewModel.ncf = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "NCF") },
                    singleLine = true
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            {
                OutlinedTextField(
                    value = viewModel.concepto,
                    onValueChange = { viewModel.concepto = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = "Concepto") },
                    singleLine = true
                )
            }

        }

        Row {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            {
                OutlinedTextField(
                    value = viewModel.descuento.toString(),
                    onValueChange = {
                        val newValue = it.toIntOrNull()
                        if (newValue != null) {
                            viewModel.descuento = newValue
                        }
                    },
                    label = { Text(text = "Descuento") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            {
                OutlinedTextField(
                    value = viewModel.itbis.toString(),
                    onValueChange = {
                        val newValue = it.toIntOrNull()
                        if (newValue != null) {
                            viewModel.itbis = newValue
                        }
                    },
                    label = { Text(text = "Itbis") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    )
                )
            }

        }

        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.monto.toString(),
                    onValueChange = {
                        val newValue = it.toIntOrNull()
                        if (newValue != null) {
                            viewModel.monto = newValue
                        }
                    },
                    label = { Text(text = "Monto") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number,

                        )
                )
            }
        }

        Row {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            {
                val keyboardController =
                    LocalSoftwareKeyboardController.current
                Button(
                    onClick = {
                        keyboardController?.hide()
                        if (viewModel.validar()) {
                            viewModel.save()
                            viewModel.setMessageShown()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)

                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "Guardar")
                    Text(text = "Guardar")
                }
            }
        }

        uiState.gasto?.let { gasto -> ConsultaGasto(gasto) }
    }


}


@Composable
fun ConsultaGasto(gastos: List<GastoDto>, viewModel: GastoViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn(modifier = Modifier.fillMaxWidth())
        {
            items(gastos) { gasto ->
                val formatoOriginal = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val formatoNuevo = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val fechaParseada = formatoOriginal.parse(gasto.fecha)
                val fechaFormateada = formatoNuevo.format(fechaParseada)
                val formattedAmount = NumberFormat.getInstance().format(gasto.monto)

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    shape = RoundedCornerShape(corner = CornerSize(16.dp)),
                    elevation = CardDefaults.elevatedCardElevation()
                ) {
                    Column(modifier = Modifier.padding(16.dp))
                    {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Id: " + gasto.idGasto,
                                    style = MaterialTheme.typography.titleMedium
                                )

                            }
                            Text(
                                text = fechaFormateada,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = gasto.suplidor,
                            style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = gasto.concepto,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "NCF: " + gasto.ncf,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.weight(1f)
                            )
                            {
                                Text(
                                    text = "Itbis: " + gasto.itbis.toString(),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                            Text(
                                text = "$" + formattedAmount,
                                style = MaterialTheme.typography.titleMedium.copy(fontSize = 20.sp),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                        Divider(
                            color = Color.Black,
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.padding(2.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        )
                        {

                            Button(
                                onClick = {
                                },
                                modifier = Modifier
                                    .width(150.dp)
                                    .padding(8.dp),
                                shape = MaterialTheme.shapes.medium,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Green,
                                    contentColor = Color.Black
                                )
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Modificar"
                                    )
                                    Text(
                                        text = "Modificar",
                                        modifier = Modifier.padding(top = 3.dp),
                                    )
                                }
                            }

                            OutlinedButton(
                                onClick = {
                                    gasto.idGasto?.let { viewModel.delete(it) }
                                },
                                modifier = Modifier.width(150.dp),
                                shape = MaterialTheme.shapes.medium,
                                border = BorderStroke(1.dp, Color.Red),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Eliminar",
                                    tint = Color.Red
                                )
                                Text(text = "Eliminar", color = Color.Red)
                            }
                        }
                    }
                }
            }
        }
    }
}

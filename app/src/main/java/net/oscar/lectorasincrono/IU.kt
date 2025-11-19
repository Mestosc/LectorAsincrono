package net.oscar.lectorasincrono

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.floor

@Composable
fun IU(myViewModel: MyViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Spacer(modifier = Modifier.padding(10.dp))
        Progresser(myViewModel)
        MostrarPorcentaje(myViewModel)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            MostrarEstado(myViewModel)
            BotonCarga(myViewModel)
        }
    }
}
@Composable
fun MostrarPorcentaje(myViewModel: MyViewModel) {
    val porcentaje = myViewModel.progresoFlow.collectAsState().value*100
    Text(text = "${floor(porcentaje)} %")
}
@Composable
fun MostrarEstado(myViewModel: MyViewModel) {
    val estado = myViewModel.currentState.collectAsState().value
    val nombreEstado = estado.toString()[0].uppercase() + estado.toString().substring(1).lowercase()
        Text(text = nombreEstado)

}
@Composable
fun Progresser(myViewModel: MyViewModel) {
    val progress = myViewModel.progresoFlow.collectAsState()
    LinearProgressIndicator(
        progress = { progress.value },
        color = Color.Red,
        trackColor = Color.DarkGray
    )
}
@Composable
fun BotonCarga(myViewModel: MyViewModel) {
    Button(onClick = {
        myViewModel.simularLectura() }) { Text(text = "Load") }
}
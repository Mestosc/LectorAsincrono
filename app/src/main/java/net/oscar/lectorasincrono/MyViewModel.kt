package net.oscar.lectorasincrono

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

class MyViewModel : ViewModel() {
    var currentState: MutableStateFlow<Estados> = MutableStateFlow(Estados.IDLE(this))
    var progresoFlow: MutableStateFlow<Float> = MutableStateFlow(0.0f);
    var delay = 0
    init {
        stateStart()
    }

    fun <T:Estados> changeState(newState: KClass<T>) {
        if (newState != currentState.value::class) {
            currentState.value.onExit()
            currentState.value = newState.constructors.first().call(this)
            stateStart()
        }
    }
    fun stateStart() {
        currentState.value.onEnter()
    }
    fun simularLectura() {
        changeState(Estados.CARGANDO::class)
        viewModelScope.launch {
            for (i in 1..10) {
                progresoFlow.value = progresoFlow.value.plus(0.10f).coerceAtMost(1.0f)
                if (progresoFlow.value >= 1.0f) {
                    changeState(Estados.FINALIZADO::class)
                    break
                }
                delay(delay.toLong())
            }

        }
    }

}
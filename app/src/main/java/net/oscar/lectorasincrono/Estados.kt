package net.oscar.lectorasincrono

import android.util.Log
import kotlin.reflect.KClass


/**
 * Representacion de los estados de una maquina de estados
 * @param myViewModel El modelo Vista que gestiona los estados y la informacion de la aplicacion
 */
sealed class Estados(val myViewModel: MyViewModel) {
    abstract fun onEnter();
    abstract fun onExit();
    val stateTag = "stateTAG"

    override fun toString(): String = this::class.simpleName ?: "Estado"

    /**
     * Estado IDLE el estado primario cuando no estamos haciendo nada
     */
    class IDLE(myViewModel: MyViewModel) : Estados(myViewModel) {
        override fun onEnter() {
            Log.d(stateTag,"Estado $this")
        }

        override fun onExit() {

        }
    }

    /**
     * Estado Cargando es el estado en el que estamos cuando estamos cargando un archivo ``ficticio``
     */
    class CARGANDO(myViewModel: MyViewModel) : Estados(myViewModel) {
        override fun onEnter() {
            Log.d(stateTag,"Estado $this")
            myViewModel.progresoFlow.value = 0f
            myViewModel.delay = (1..5).random() * 1000
        }

        override fun onExit() {

        }
    }

    class FINALIZADO(myViewModel: MyViewModel) : Estados(myViewModel) {
        override fun onEnter() {
            Log.d(stateTag,"Estado $this")
        }

        override fun onExit() {

        }
    }
    class ERROR(myViewModel: MyViewModel) : Estados(myViewModel) {
        override fun onEnter() {
            Log.d(stateTag,"Estado $this")
        }

        override fun onExit() {

        }
    }

}
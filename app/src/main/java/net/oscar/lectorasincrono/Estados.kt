package net.oscar.lectorasincrono

import android.util.Log


/**
 * Representacion de los estados de una maquina de estados
 * @param myViewModel El modelo Vista que gestiona los estados y la informacion de la aplicacion
 */
sealed class Estados(val myViewModel: MyViewModel) {
    abstract fun on_enter();
    abstract fun on_exit();
    val stateTag = "stateTAG"

    /**
     * Estado IDLE el estado primario cuando no estamos haciendo nada
     */
    class IDLE(myViewModel: MyViewModel) : Estados(myViewModel) {
        override fun on_enter() {
            Log.d(stateTag,"Estado IDLE")
        }

        override fun on_exit() {

        }
    }

    /**
     * Estado Cargando es el estado en el que estamos cuando estamos cargando un archivo ``ficticio``
     */
    class CARGANDO(myViewModel: MyViewModel) : Estados(myViewModel) {
        override fun on_enter() {
            Log.d(stateTag,"Estado Cargando")
            myViewModel.progresoFlow.value = 0f
            myViewModel.delay = (1..5).random() * 1000
        }

        override fun on_exit() {

        }
    }

    class FINALIZADO(myViewModel: MyViewModel) : Estados(myViewModel) {
        override fun on_enter() {
            Log.d(stateTag,"Estado Finalizando")
        }

        override fun on_exit() {

        }
    }
    class ERROR(myViewModel: MyViewModel) : Estados(myViewModel) {
        override fun on_enter() {
            Log.d(stateTag,"Estado ERROR")
        }

        override fun on_exit() {

        }
    }

}
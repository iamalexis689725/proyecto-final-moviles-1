import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmovilespedidosyachofer.models.Restaurante
import com.example.appmovilespedidosyachofer.models.Restaurantes
import com.example.appmovilespedidosyachofer.repositories.RestauranteRepository

class RestauranteViewModel: ViewModel() {
    private val _listaRestaurantes = MutableLiveData<Restaurantes>().apply {
        value = arrayListOf()
    }
    val listaRestaurantes: LiveData<Restaurantes> = _listaRestaurantes

    private val _restauranteActual = MutableLiveData<Restaurante?>()
    val restauranteActual: LiveData<Restaurante?> = _restauranteActual

    fun obtenerListaRestaurantes(token: String) {
        RestauranteRepository.obtenerListaRestaurantes(
            token,
            onSuccess = {
                _listaRestaurantes.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun obtenerRestauranteById(token: String, id: Int) {
        RestauranteRepository.obtenerRestauranteById(
            token,
            id,
            onSuccess = {
                _restauranteActual.value = it
            },
            onError = {
                it.printStackTrace()
                _restauranteActual.value = null
            }
        )
    }
}

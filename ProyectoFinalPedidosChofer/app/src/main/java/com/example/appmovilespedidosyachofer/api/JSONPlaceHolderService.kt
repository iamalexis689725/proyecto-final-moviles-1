import com.example.appmovilespedidosyachofer.models.Restaurante
import com.example.appmovilespedidosyachofer.models.Restaurantes
import com.example.appmovilespedidosyachofer.models.misPedidosFree
import com.example.appmovilespedidosyacliente.models.Token
import com.example.appmovilespedidosyacliente.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface JSONPlaceHolderService {
    @POST("api/users")
    fun crearUsuario(@Body usuario: Usuario): Call<Usuario>

    @POST("api/users/login")
    fun inicioSesionUsuario(@Body credentials: Map<String, String>): Call<Token>

    @GET("api/orders/free")
    fun verPedidosLibres(@Header("Authorization") token: String): Call<misPedidosFree>

    @GET("api/restaurants")
    fun obtenerRestaurantes(@Header("Authorization") token: String): Call<Restaurantes>

    @GET("api/restaurants/{id}")
    fun obtenerRestauranteById(@Header("Authorization") token: String, @Path("id") id: Int): Call<Restaurante>

}
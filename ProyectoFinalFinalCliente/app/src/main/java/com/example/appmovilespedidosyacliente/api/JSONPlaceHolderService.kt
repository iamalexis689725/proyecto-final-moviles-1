import com.example.appmovilespedidosyacliente.models.Restaurante
import com.example.appmovilespedidosyacliente.models.Restaurantes
import com.example.appmovilespedidosyacliente.models.Token
import com.example.appmovilespedidosyacliente.models.Usuario
import com.example.appmovilespedidosyacliente.models.Pedido
import com.example.appmovilespedidosyacliente.models.PedidosDelUsuarios
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

    @GET("api/restaurants")
    fun obtenerRestaurantes(@Header("Authorization") token: String): Call<Restaurantes>

    @GET("api/restaurants/{id}")
    fun obtenerRestauranteById(@Header("Authorization") token: String, @Path("id") id: Int): Call<Restaurante>

    @POST("api/orders")
    fun crearPedido(@Header("Authorization") token: String, @Body pedido: Pedido): Call<Pedido>

    @GET("api/orders")
    fun verPedidosUsuario(@Header("Authorization") token: String): Call<PedidosDelUsuarios>
}
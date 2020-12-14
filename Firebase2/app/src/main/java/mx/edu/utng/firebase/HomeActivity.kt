package mx.edu.utng.firebase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*

enum class ProviderType{
    BASIC,
    GOOGLE
}

class HomeActivity : AppCompatActivity() {

    //creacion de referencia de constante privada
    //instancia conectada a bd que se define remoto desde la consola de firebase
    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //setup
        //recupermos los datos que pasaremos con intent.extras
       val bundle: Bundle? =intent.extras
        //se guardan en constantes en caso de que sean nulos y estas se envias a nuestro setup
       val email:String? = bundle?.getString("email")
       val provider:String? = bundle?.getString("provider")
        setup(email?: "", provider?: "")

        //Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    private fun setup(email: String, provider:String){
        title ="Inicio"
        //
        tvEmail.text = email
        tvProveedor.text = provider

        btnLogOut.setOnClickListener(View.OnClickListener {
            //Borrado de datos
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        })


        //boton guaradr
        btnGuardar.setOnClickListener {
            //se llama  la instancia db y se define una estructura de datos asignada a la app
            //se crea una coleccion llamada user y creamos un documento  asociado a nuestra app
            //tendremos un documento por cada usuario
            //set para guardar datos
            db.collection("users").document(email).set(
                hashMapOf("provider" to provider,
                "address" to tvAdress.text.toString(),
                "phone" to tvTelefono.text.toString())
            )

        }

        //boton recuperar
        btnRecuperar.setOnClickListener {
            db.collection("users").document(email).get().addOnSuccessListener {
                tvAdress.setText(it.get("address") as String?)
                tvTelefono.setText(it.get("phone") as String?)
            }
        }

        //boton eliminar
        btnEliminar.setOnClickListener {
            db.collection("users").document(email).delete()
        }
    }
}
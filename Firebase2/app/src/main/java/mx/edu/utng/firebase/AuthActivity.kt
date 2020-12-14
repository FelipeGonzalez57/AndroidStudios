package mx.edu.utng.firebase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    private val GOOGLE_SIGN_IN =100

    override fun onCreate(savedInstanceState: Bundle?) {
        //Splash
        Thread.sleep((2000))
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //Llamamos a firebaseAnalitycs y lo guardaremos en una constante
        val analytics =FirebaseAnalytics.getInstance(this)
        //creamos un budle y lo instanciamos en la constante
        val  bundle = Bundle();
        bundle.putString("message", "Integración de Firebase completa")
        //llamaremos a log event  y le pasaremos parametros
        analytics.logEvent("InitScreen",bundle)

        //Setup
        setup()
        session()
    }

    override fun onStart() {
        super.onStart()

        authLayaut.visibility = View.VISIBLE
    }

    private fun session(){
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)

        if(email != null && provider!= null){
            authLayaut.visibility = View.INVISIBLE
            showHome(email,ProviderType.valueOf(provider))
        }
    }

    //configuracion de la pantalla
    private fun setup(){
        //Cambiar el titulo de la pantalla llamando a l propiedad title y igualandola a auteticacion
        title = "Autenticación"

        //Acceder al boton de registro
        btnLogOut.setOnClickListener(View.OnClickListener {
            if(etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){
                //addon complete nos ayudara a saber si la operacion fue exitosa o no
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString()).addOnCompleteListener(){
                    //validacion para conocer  ssi existe algun error
                    if (it.isSuccessful){
                        //Le pasamos el email y el provedor en caso de que sea exitoso el registro y el acceder
                        showHome(it.result?.user?.email ?:"",ProviderType.BASIC)
                    }else{
                        //funcion que nos alertara si el registro da un error y nos alertara
                        showAlert()
                    }
                }
            }
        })
        //Boton de login
        btnLogin.setOnClickListener(View.OnClickListener {
            if(etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString()).addOnCompleteListener(){
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?:"",ProviderType.BASIC)
                    }else{
                        showAlert()
                    }
                }
            }
        })

        //boton de google
        btnGoogle.setOnClickListener {
            //Configuraciones
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
    }


    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType){
        //pasaremos de pantalla
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            //datos que le pasaremos parametros
            //nombres de los parametro constantes recomendacion
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        //de esta forma  realizaremos la navegacion
        startActivity(homeIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GOOGLE_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                if(account!=null){
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    //Le pasamos una licencia de firebase para que la detecte
                    FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(){
                        if (it.isSuccessful){
                            showHome(account.email ?: "", ProviderType.GOOGLE)
                        }else{
                            showAlert()
                        }
                    }
                }
            }catch (e:ApiException){
                showAlert()
            }

        }
    }
}
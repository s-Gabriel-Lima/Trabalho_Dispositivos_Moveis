package trabalho_dispositivos_moveis.com.br

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import trabalho_dispositivos_moveis.com.br.databinding.ActivityUserBinding


class user : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding: ActivityUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        recuperar_nome()
        recuperar_email()


        //
        window.statusBarColor = Color.parseColor("#193938")
        binding.logout.setOnClickListener {
            val logout = Intent(this, Login::class.java)
            startActivity(logout)
        }

    }

    private fun recuperar_email() {
        val email = intent.getStringExtra("email")
        val nome_usuario = intent.getStringExtra("nome_usuario")
        binding.emailUsuario.setText(email)
        binding.nomeUsuario.setText(nome_usuario)
    }


    private fun recuperar_nome() {
        val email_consulta = intent.getStringExtra("email")
        val usuarios_ref = db.collection("usuarios")

        usuarios_ref.whereEqualTo("email", email_consulta)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    val nomeUsuario = document.getString("nome_usuario")
                    if (nomeUsuario != null) {

                        binding.nomeUsuario.setText(nomeUsuario)
                    } else {
                        Log.d("db", "Nome de usuário não encontrado para $email_consulta")

                    }
                }
            }

    }
}

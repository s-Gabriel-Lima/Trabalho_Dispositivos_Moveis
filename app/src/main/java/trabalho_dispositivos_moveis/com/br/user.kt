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
            val logout = Intent(this,Login::class.java)
            startActivity(logout)
        }

    }

    private fun recuperar_email(){
        val email = intent.getStringExtra("email")
        val nome_usuario = intent.getStringExtra("nome_usuario")
        binding.emailUsuario.setText(email)
        binding.nomeUsuario.setText(nome_usuario)
    }





   private fun recuperar_nome(){
       val email = intent.getStringExtra("email")

       db.collection("usuarios").document("nome_usuario").addSnapshotListener { value, error ->
           if (value != null) {
               binding.nomeUsuario.text = value.getString("nome_usuario")
           }
           Log.d("db","${value}")
       }


    }


}

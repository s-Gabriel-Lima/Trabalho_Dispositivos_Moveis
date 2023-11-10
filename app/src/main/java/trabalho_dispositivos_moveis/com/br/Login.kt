package trabalho_dispositivos_moveis.com.br

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import trabalho_dispositivos_moveis.com.br.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#193938")
        binding.entrar.setOnClickListener{view ->
            val email = binding.emailLogin.text.toString()
            val senha = binding.senhaLogin.text.toString()

            if(email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view, "Preencha todos os campos", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{autenticacao ->
                    if(autenticacao.isSuccessful){
                        navegartelaprincipal()
                    }
                }

            }

        }
        binding.criarCadastro.setOnClickListener {
            navegartelaCadastro()
        }

    }
    private fun navegartelaprincipal(){
        val intent = Intent(this,MainActivity::class.java) //AO INVES DE MAINACTIVITY COLOCAR A ACTIVITY DA TELA DE USUÁRIO QUE AINDA NÃO EXISTE NESSE BRANCH DO PROJETO
        startActivity(intent)
        finish()


    }

    private fun navegartelaCadastro(){
        val intent = Intent(this,cadastro::class.java) //AO INVES DE MAINACTIVITY COLOCAR A ACTIVITY DA TELA DE USUÁRIO QUE AINDA NÃO EXISTE NESSE BRANCH DO PROJETO
        startActivity(intent)
        finish()


    }
}
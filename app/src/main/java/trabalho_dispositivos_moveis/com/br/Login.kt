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
    public var email_correto = ""
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
                        email_correto = email
                        navegartelaprincipal()
                    }else{
                        val snackbar = Snackbar.make(view,"Email ou senha inválidos!",Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()

                    }
                }

            }

        }
        binding.criarCadastro.setOnClickListener {
            navegartelaCadastro()
        }

    }
    private fun navegartelaprincipal(){
        val intent = Intent(this,user::class.java) //AO INVES DE MAINACTIVITY COLOCAR A ACTIVITY DA TELA DE USUÁRIO QUE AINDA NÃO EXISTE NESSE BRANCH DO PROJETO
        intent.putExtra("email", email_correto.toString())
        startActivity(intent)
        finish()


    }

    private fun navegartelaCadastro(){
        val intent = Intent(this,cadastro::class.java) //AO INVES DE MAINACTIVITY COLOCAR A ACTIVITY DA TELA DE USUÁRIO QUE AINDA NÃO EXISTE NESSE BRANCH DO PROJETO
        startActivity(intent)
        finish()


    }
}
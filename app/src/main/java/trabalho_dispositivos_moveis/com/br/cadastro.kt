package trabalho_dispositivos_moveis.com.br

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import trabalho_dispositivos_moveis.com.br.databinding.ActivityCadastroBinding

class cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textButton.setOnClickListener{view ->
            val email = binding.email.text.toString()
            val email_confirmado = binding.emailConfirmado.text.toString()
            val senha = binding.senha.text.toString()
            val senha_confirmada = binding.senhaConfirmada.text.toString()

            if(email.isEmpty() || email_confirmado.isEmpty() || senha.isEmpty() || senha_confirmada.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
            }else{
                if(email != email_confirmado){
                    val snackbar = Snackbar.make(view,"Informe o mesmo e-mail nos campos 'e-mail' e 'Confirmar e-mail' ",Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                }else{
                    if(senha != senha_confirmada){
                        val snackbar = Snackbar.make(view,"As senhas não coincidem",Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                    }else{
                        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener{ cadastro ->
                            if(cadastro.isSuccessful){
                                val snackbar = Snackbar.make(view,"Usuário Cadastrado com Sucesso",Snackbar.LENGTH_SHORT)
                                snackbar.setBackgroundTint(Color.BLUE)
                                binding.email.setText("")
                                binding.emailConfirmado.setText("")
                                binding.senha.setText("")
                                binding.senhaConfirmada.setText("")
                                binding.nome.setText("")
                            }
                        }
                    }
                }
            }

        }


    }
}
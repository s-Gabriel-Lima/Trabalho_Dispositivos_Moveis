package trabalho_dispositivos_moveis.com.br

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import trabalho_dispositivos_moveis.com.br.databinding.ActivityCadastroBinding

class cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#193938")


        binding.redirecionaLogin.setOnClickListener{
            navegartelaLogin()
        }

        binding.textButton.setOnClickListener{view ->

            val  nome_usuario = binding.nome.text.toString()
            val email = binding.email.text.toString()
            val email_confirmado = binding.emailConfirmado.text.toString()
            val senha = binding.senha.text.toString()
            val senha_confirmada = binding.senhaConfirmada.text.toString()

            if(email.isEmpty() || email_confirmado.isEmpty() || senha.isEmpty() || senha_confirmada.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                if(email != email_confirmado){
                    val snackbar = Snackbar.make(view,"Informe o mesmo e-mail nos campos 'e-mail' e 'Confirmar e-mail' ",Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }else{
                    if(senha != senha_confirmada){
                        val snackbar = Snackbar.make(view,"As senhas não coincidem",Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()

                    }else {
                        if(senha.length <8){
                            val snackbar = Snackbar.make(view,"a senha deve conter ao menos 8 caracteres",Snackbar.LENGTH_SHORT)
                            snackbar.setBackgroundTint(Color.RED)
                            snackbar.show()

                        }else{


                        //salva_db()
                        val usuariosmap = hashMapOf(
                            "nome_usuario" to nome_usuario,
                            "email" to email
                        )

                        db.collection("usuarios").document(email_confirmado).set(usuariosmap)
                            .addOnCompleteListener {
                                Log.d("db", "sucesso")
                            }


                        //
                        auth.createUserWithEmailAndPassword(email, senha)
                            .addOnCompleteListener { cadastro ->
                                if (cadastro.isSuccessful) {
                                    val snackbar = Snackbar.make(
                                        view,
                                        "Usuário Cadastrado com Sucesso",
                                        Snackbar.LENGTH_SHORT
                                    )
                                    snackbar.setBackgroundTint(Color.BLUE)
                                    snackbar.show()
                                    binding.email.setText("")
                                    binding.emailConfirmado.setText("")
                                    binding.senha.setText("")
                                    binding.senhaConfirmada.setText("")
                                    binding.nome.setText("")
                                    navegartelaLogin()
                                } else {
                                    val snackbar = Snackbar.make(
                                        view,
                                        "Erro ao salvar!",
                                        Snackbar.LENGTH_SHORT
                                    )
                                    snackbar.setBackgroundTint(Color.RED)
                                    snackbar.show()
                                }
                            }
                    }
                    }
                }
            }

        }


    }

    private fun navegartelaLogin(){
        val intent = Intent(this,Login::class.java) //AO INVES DE MAINACTIVITY COLOCAR A ACTIVITY DA TELA DE USUÁRIO QUE AINDA NÃO EXISTE NESSE BRANCH DO PROJETO
        startActivity(intent)
        finish()


    }



}
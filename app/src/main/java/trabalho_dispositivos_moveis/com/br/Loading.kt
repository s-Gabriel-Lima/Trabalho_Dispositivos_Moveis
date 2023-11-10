package trabalho_dispositivos_moveis.com.br

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity

class Loading : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = Color.parseColor("#FFFFFF")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent= Intent(this,Login::class.java)
            startActivity(intent)
            finish()
        },1500)
    }
    private fun navegartelaLogin(){
        val intent = Intent(this,Login::class.java) //AO INVES DE MAINACTIVITY COLOCAR A ACTIVITY DA TELA DE USUÁRIO QUE AINDA NÃO EXISTE NESSE BRANCH DO PROJETO
        startActivity(intent)
        finish()


    }
}
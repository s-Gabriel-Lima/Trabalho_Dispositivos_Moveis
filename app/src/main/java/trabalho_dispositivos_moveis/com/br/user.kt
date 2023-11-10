package trabalho_dispositivos_moveis.com.br

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import trabalho_dispositivos_moveis.com.br.databinding.ActivityUserBinding

class user : AppCompatActivity() {

private lateinit var binding: ActivityUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#193938")




        binding.logout.setOnClickListener {
            val logout = Intent(this,Login::class.java)
            startActivity(logout)
        }

    }

}
package trabalho_dispositivos_moveis.com.br

import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import trabalho_dispositivos_moveis.com.br.databinding.ActivityUserBinding

class user : AppCompatActivity() {

private lateinit var binding: ActivityUserBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logout.setOnClickListener {
            val logout = Intent(this,Login::class.java)
            startActivity(logout)
        }

    }

}
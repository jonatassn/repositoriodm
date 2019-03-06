package br.edu.ifpr.jonatas.vikings

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ivLogoVikings.setOnClickListener {  }
        ivRagnar.setOnClickListener { verPerfil("PerfilRagnarActivity") }
        ivLagertha.setOnClickListener { verPerfil("PerfilLagerthaActivity") }
        ivBjorn.setOnClickListener { verPerfil("PerfilBjornActivity") }
        ivRollo.setOnClickListener { verPerfil("PerfilRolloActivity") }
        ivAthelstan.setOnClickListener { verPerfil("PerfilAthelstanActivity") }
        ivFloki.setOnClickListener { verPerfil("PerfilFlokiActivity") }
    }

    fun verPerfil(perfil : String) {
        var intent : Intent
        val bundle = Bundle()
        bundle.putString("origem", "menu")
        if(perfil == "PerfilRagnarActivity") {
            intent = Intent(this, PerfilRagnarActivity::class.java)
        }
        else if(perfil == "PerfilLagerthaActivity") {
            intent = Intent(this, PerfilLagerthaActivity::class.java)
        }
        else if(perfil == "PerfilBjornActivity") {
            intent = Intent(this, PerfilBjornActivity::class.java)
        }
        else if(perfil == "PerfilRolloActivity") {
            intent = Intent(this, PerfilRolloActivity::class.java)
        }
        else if(perfil == "PerfilAthelstanActivity") {
            intent = Intent(this, PerfilAthelstanActivity::class.java)
        }
        else{
            intent = Intent(this, PerfilFlokiActivity::class.java)
        }

        intent.putExtras(bundle)
        startActivity(intent)
    }

}

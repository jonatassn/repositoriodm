package br.edu.ifpr.jonatas.vikings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_perfil_athelstan.*

class PerfilAthelstanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_athelstan)
        if(savedInstanceState == null) {
            if(intent.extras.getString("origem") == "menu") {
                lb1.visibility = View.INVISIBLE
            }
            else {
                lb1.visibility = View.VISIBLE
                tvWyf.text = intent.extras.getString("origem")
            }
        }
        else {
            tvWyf.text = savedInstanceState.getString("origem")
        }

        tvAmigo1.setOnClickListener { verPerfil("PerfilRagnarActivity") }
        tvAmigo2.setOnClickListener { verPerfil("PerfilBjornActivity") }
    }

    fun verPerfil(perfil : String) {
        var intent : Intent
        val bundle = Bundle()
        bundle.putString("origem", "Athelstan")
        if(perfil == "PerfilRagnarActivity") {
            intent = Intent(this, PerfilRagnarActivity::class.java)
        }
        else {
            intent = Intent(this, PerfilBjornActivity::class.java)
        }

        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("origem", tvWyf.text.toString())
    }
}

package br.edu.ifpr.jonatas.vikings

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_perfil_athelstan.*

class PerfilAthelstanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("atividade", "Entrando na Activity Athelstan")
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_perfil_athelstan)
        }
        else {
            setContentView(R.layout.activity_perfil_athelstan_landscape)
        }
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
            if(savedInstanceState.getString("origem").isEmpty()) {
                lb1.visibility = View.INVISIBLE
            }
            else {
                tvWyf.text = savedInstanceState.getString("origem")
            }
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
        Log.d("atividade", "Saindo da Activity Athelstan")

        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("origem", tvWyf.text.toString())
    }
}

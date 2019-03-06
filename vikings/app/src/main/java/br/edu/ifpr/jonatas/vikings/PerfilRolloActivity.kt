package br.edu.ifpr.jonatas.vikings

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_perfil_rollo.*

class PerfilRolloActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("atividade", "Entrando na Activity Rollo")
        if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_perfil_rollo)
        }
        else {
            setContentView(R.layout.activity_perfil_rollo_landscape)
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

        tvAmigo1.setOnClickListener { verPerfil() }
    }

    fun verPerfil() {
        var intent : Intent
        val bundle = Bundle()
        bundle.putString("origem", "Rollo")
        intent = Intent(this, PerfilBjornActivity::class.java)

        intent.putExtras(bundle)
        Log.d("atividade", "Saindo da Activity Rollo")
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("origem", tvWyf.text.toString())
    }
}

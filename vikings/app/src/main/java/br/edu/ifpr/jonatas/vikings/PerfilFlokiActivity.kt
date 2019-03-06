package br.edu.ifpr.jonatas.vikings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_perfil_floki.*

class PerfilFlokiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_floki)
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

        tvAmigo1.setOnClickListener { verPerfil() }
    }

    fun verPerfil() {
        var intent : Intent
        val bundle = Bundle()
        bundle.putString("origem", "Floki")
        intent = Intent(this, PerfilRagnarActivity::class.java)


        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("origem", tvWyf.text.toString())
    }
}

package br.edu.ifpr.jonatas.arco

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checaStatus(btLab1)
        checaStatus(btLab2)
        checaStatus(btLab3)
        checaStatus(btLab4)
        checaStatus(btLab5)

        btLab1.setOnClickListener { onClickBtLab1(btLab1) }
        btLab2.setOnClickListener { onClickBtLab1(btLab2) }
        btLab3.setOnClickListener { onClickBtLab1(btLab3) }
        btLab4.setOnClickListener { onClickBtLab1(btLab4) }
        btLab5.setOnClickListener { onClickBtLab1(btLab5) }

    }

    fun onClickBtLab1(botao : Button) {
        val token = botao.text.toString().split("-")
        if(token[2] == " OFF") {
            botao.text = "${token[0]}-${token[1]}- ON"
        }
        else {
            botao.text = "${token[0]}-${token[1]}- OFF"
        }
        checaStatus(botao)
        getAPIStatus(token[1])
    }

    fun checaStatus(botao : Button) {
        val token = botao.text.toString().split("-")
        if(token[2] == " OFF") {
            botao.setBackgroundColor(Color.RED)
        }
        else {
            botao.setBackgroundColor(Color.GREEN)
        }
    }

    fun getAPIStatus(id : String) {
        FuelManager.instance.basePath = "https://jsonplaceholder.typicode.com/todos/"
        Fuel.get(id).responseJson {request, response, result ->
            Log.d("json",result.get().content)
            tvConteudo.text = result.get().content
        }
    }
}



//https://jsonplaceholder.typicode.com/todos/1
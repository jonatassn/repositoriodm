package jonatas.ifpr.edu.br.interfacesgraficas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        button11.setOnClickListener {
            val intent = Intent(this, Main5Activity::class.java)
            startActivity(intent)
        }
    }
}

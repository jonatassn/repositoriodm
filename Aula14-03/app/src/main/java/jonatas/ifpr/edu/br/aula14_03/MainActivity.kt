package jonatas.ifpr.edu.br.aula14_03

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button3.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 1)
            }
            else {
                onClickBtIr()
            }
        }
        button4.setOnClickListener {

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1)
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onClickBtIr()
            }
    }

    fun onClickBtIr() {
            //            val uri = Uri.parse("tel:${textView2.text}")
            //            val intent = Intent(Intent.ACTION_CALL, uri)
            //            startActivity(intent)
            val uri: Uri
            if(textView2.text.contains("http://")) {
                uri = Uri.parse("${textView2.text}")
            }
            else {
                uri = Uri.parse("http://${textView2.text}")
            }

            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
    }

}

package com.example.saeha.lastadaptertest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.saeha.lastadaptertest.databinding.ItemMainBinding
import com.example.saeha.lastadaptertest.databinding.ItemMainStringBinding
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = arrayListOf(
            ItemData("경호",1,  R.color.colorPrimary),
            ItemData("민수", 40,  R.color.colorPrimaryDark),
            ItemData("진우", 50,  R.color.colorPrimary),
            "사라",
            "기홍")


        rcyMain.layoutManager = LinearLayoutManager(this@MainActivity)

        LastAdapter(users,BR.itemData) // variable name itemData로 통일

            // String
            .map<String, ItemMainStringBinding>(R.layout.item_main_string){
                onBind { it ->
                    val position = it.adapterPosition
                    val data = users[position] as String
                    it.binding.apply {

                    }
                }
                onClick {  }
                onLongClick {  }
            }

            // ItemData 객체에 관한 부분
            .map<ItemData,ItemMainBinding>(R.layout.item_main){
                onBind {

                }
                onClick {
                    val position = it.adapterPosition
                    Toast.makeText(this@MainActivity,"position"+position.toString(),Toast.LENGTH_LONG).show()
                }
                onLongClick {  }
            }
            .into(rcyMain)


    }
}

package com.example.saeha.lastadaptertest

import android.graphics.Color
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

                    val position = it.adapterPosition

                    // 객체가 가진 값에 따라서 UI 에 반영 하는 방법

                    it.binding.apply {
                       val obob = users[position] as ItemData
                       val getNumber = obob.number.toInt()
                        //미출석/퇴실 상태 반영
                        if ( getNumber == -1) {
                            it.binding.textView.setTextColor(Color.parseColor("#333333"))
                        } else {
                            it.binding.textView.setTextColor(Color.parseColor("#ffffff"))
                        }

//                        //체크박스
//                        it.binding.cbAttendanceChoiceStudent.setOnCheckedChangeListener { buttonView, isChecked ->
//                            nonAttendanceList[positionNum].checked = isChecked
//                            changeBlockState(1)  //check가 있으면 그 결과에 따라서 리스트 블럭
//                            changeAllCheckState(1, 0)
//                        }
                    }

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

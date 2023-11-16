package com.example.colortilesviewsk

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import java.util.Random

class MainActivity : AppCompatActivity() {

    lateinit var tiles: MutableList<MutableList<View>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tiles = mutableListOf()

        val main_linear: LinearLayout = findViewById(R.id.main_linear)

        for (linear in main_linear.children) {
            val list = mutableListOf<View>()
            for (child in (linear as LinearLayout).children) {
                list.add(child)
            }
            tiles.add(list)
        }
        initField()
    }

    fun changeColor(view: View) {
        val brightColor = resources.getColor(R.color.bright)
        val darkColor = resources.getColor(R.color.dark)
        val drawable = view.background as ColorDrawable
        if (drawable.color == brightColor) {
            view.setBackgroundColor(darkColor)
        } else {
            view.setBackgroundColor(brightColor)
        }
    }

    fun onClick(v: View) {
        var x = 0
        var y = 0

        loop@ for (i in 0 until tiles.size) {
            for (j in 0 until tiles[i].size) {
                if (v == tiles[i][j]) {
                    y = i
                    x = j
                    break@loop
                }
            }
        }
        for (view in tiles[y]) {
            changeColor(view)
        }
        for (i in 0 until y) {
            changeColor(tiles[i][x])
        }
        for (i in y + 1 until tiles.size) {
            changeColor(tiles[i][x])
        }
        checkVictory()
    }

    fun checkVictory() {
        for (line in tiles) {
            for (view in line) {
                val darkColor = resources.getColor(R.color.dark)
                val drawable = view.background as ColorDrawable
                if (drawable.color != darkColor) {
                    return
                }
            }
        }
        Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
    }

    fun initField() {
        val random = Random()
        for (row in tiles) {
            for (view in row) {
                if (random.nextBoolean())
                    changeColor(view)
            }
        }
    }


}
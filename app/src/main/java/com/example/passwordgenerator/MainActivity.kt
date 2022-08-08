package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.random.Random

/*
    when you make the switches that put restrictions:
        - when a switch is pressed, make the respective array null
        - put if statement that says --> if null, then do i-- and then continue (keyword)
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val upperAlphabets:Array<Char> = arrayOf('A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z')
        val lowerAlphabets:Array<Char> = arrayOf('a','b','c','d','e','f','g','h','i','j','k',
        'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
        val symbols:Array<Char> = arrayOf('!','@','#','$','%','&','?')
        val nums:Array<Int> = arrayOf(1,2,3,4,5,6,7,8,9)
        val password:String = ""
        val passLength:Int = 10


    }
}
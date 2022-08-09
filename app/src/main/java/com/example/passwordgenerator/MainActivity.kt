package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
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

        val sampleUpperAlphabets:Array<Char> = arrayOf('A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z')    // sample array of uppercase alphabets
        val sampleLowerAlphabets:Array<Char> = arrayOf('a','b','c','d','e','f','g','h','i','j','k',
        'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')    // sample array of lowercase alphabets
        val sampleSymbols:Array<Char> = arrayOf('!','@','#','$','%','&','?')    // sample array of special characters
        val sampleNums:Array<Int> = arrayOf(1,2,3,4,5,6,7,8,9)      // sample array of numbers
        val passLength:Int = 10     // the length desired length of the password
        var password:String = ""    // the password that will be created/generated

        var upperAlphabets:Array<Char> = sampleUpperAlphabets   // uppercase alphabets array to be used in random generator
        var lowerAlphabets:Array<Char> = sampleLowerAlphabets   // lowercase alphabets array to be used in random generator
        var symbols:Array<Char> = sampleSymbols     // symbol array to be used in random generator
        var nums:Array<Int> = sampleNums    // nums array to be used in random generator

        generateButton.setOnClickListener {
            // generate the random password using the 'random' package and arrays
            password = ""
            var i:Int = 0
            while(i < passLength) {
                var arraySelect:Int = Random.nextInt(1,5)   // pick random number from 1-4 to decide which array to pick an element out of
                when(arraySelect) {
                    1 -> {
                        if(upperAlphabets.size == 0) {
                            i--
                            continue
                        }
                        var upperSelect = Random.nextInt(upperAlphabets.size)   // pick random index in 'upperAlphabets' array
                        password += upperAlphabets[upperSelect]
                    }
                    2 -> {
                        if(lowerAlphabets.size == 0) {
                            i--
                            continue
                        }
                        var lowerSelect = Random.nextInt(lowerAlphabets.size)   // pick random index in 'lowerAlphabets' array
                        password += lowerAlphabets[lowerSelect]
                    }
                    3 -> {
                        if(symbols.size == 0) {
                            i--
                            continue
                        }
                        var symbolSelect = Random.nextInt(symbols.size)   // pick random index in 'symbols' array
                        password += symbols[symbolSelect]
                    }
                    4 -> {
                        if(nums.size == 0) {
                            i--
                            continue
                        }
                        var numsSelect = Random.nextInt(nums.size)   // pick random index in 'nums' array
                        password += nums[numsSelect]
                    }
                }
                i++
            }
            passwordTextBox.text = password     // set text box on UI to the generated password
        }
    }
}
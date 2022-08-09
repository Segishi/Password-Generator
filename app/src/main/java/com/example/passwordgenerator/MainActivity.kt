package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sampleUpperAlphabets:Array<Char> = arrayOf('A','B','C','D','E','F','G','H','I','J',
            'K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z')    // sample array of uppercase alphabets
        val sampleLowerAlphabets:Array<Char> = arrayOf('a','b','c','d','e','f','g','h','i','j','k',
            'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')    // sample array of lowercase alphabets
        val sampleSymbols:Array<Char> = arrayOf('!','@','#','$','%','&','?')    // sample array of special characters
        val sampleNums:Array<Char> = arrayOf('1','2','3','4','5','6','7','8','9')      // sample array of numbers
        var passLength = 10     // the length desired length of the password
        var password = ""    // the password that will be created/generated

        var upperAlphabets:Array<Char> = sampleUpperAlphabets   // uppercase alphabets array to be used in random generator
        var lowerAlphabets:Array<Char> = sampleLowerAlphabets   // lowercase alphabets array to be used in random generator
        var symbols:Array<Char> = sampleSymbols     // symbol array to be used in random generator
        var nums:Array<Char> = sampleNums    // nums array to be used in random generator

        // hide the password with asterisks when checked, and reveal it when unchecked
        hidePasswordSwitch.setOnClickListener {
            var tempString = ""
            if(hidePasswordSwitch.isChecked) {
                for(i in passwordTextBox.text.toString())
                    tempString += "*"
            } else
                tempString = password
            passwordTextBox.text = tempString
        }

        /** common function to be used for the switch buttons
         * @param switch  Switch datatype that is to be checked if on or off (true or false)
         * @param array  Array<Char> Character array that is modified to either be assigned to 'sampleArray' or to an empty array
         * @param sampleArray Array<Char> Character array that is used to set 'array' if a condition is satisfied
         */
        fun commonSwitchFunction(switch: Switch, array:Array<Char>, sampleArray:Array<Char>):Array<Char> {
            var returnArray = array     // array to be returned
            if(switch.isChecked)
                returnArray = sampleArray
            else
                returnArray = arrayOf()
            return returnArray
        }

        // allow uppercase letters when checked, and disable when unchecked
        upperCaseSwitch.setOnClickListener {
            upperAlphabets = commonSwitchFunction(upperCaseSwitch,upperAlphabets,sampleUpperAlphabets)
        }

        // allow lowercase letters when checked, and disable when unchecked
        lowerCaseSwitch.setOnClickListener {
            lowerAlphabets = commonSwitchFunction(lowerCaseSwitch,lowerAlphabets,sampleLowerAlphabets)
        }

        // allow symbols when checked, and disable when unchecked
        symbolsSwitch.setOnClickListener {
            symbols = commonSwitchFunction(symbolsSwitch,symbols,sampleSymbols)
        }

        // allow digits/nums when checked, and disable when unchecked
        digitsSwitch.setOnClickListener {
            nums = commonSwitchFunction(digitsSwitch,nums,sampleNums)
        }

        // set the length of the password based on the inputted number in "passLengthTextBox" when the "SET" button is pressed
        setButton.setOnClickListener {
            if(Integer.parseInt(passwordLengthTextBox.text.toString()) in 1..12) {
                passLength = Integer.parseInt(passwordLengthTextBox.text.toString())
                passwordLengthTextBox.text.clear()
            } else {
                passwordTextBox.text = "Invalid Number!"
                passwordLengthTextBox.text.clear()
            }
        }

        // generate the random password using the 'random' package and arrays when the "GENERATE" button is pressed
        generateButton.setOnClickListener {
            password = ""
            var i = 0
            while(i < passLength) {
                when(Random.nextInt(1,5)) {   // pick random number from 1-4 to decide which array to pick an element out of
                    1 -> {
                        if(upperAlphabets.isEmpty()) continue
                        var upperSelect = Random.nextInt(upperAlphabets.size)   // pick random index in 'upperAlphabets' array
                        password += upperAlphabets[upperSelect]
                    }
                    2 -> {
                        if(lowerAlphabets.isEmpty()) continue
                        var lowerSelect = Random.nextInt(lowerAlphabets.size)   // pick random index in 'lowerAlphabets' array
                        password += lowerAlphabets[lowerSelect]
                    }
                    3 -> {
                        if(symbols.isEmpty()) continue
                        var symbolSelect = Random.nextInt(symbols.size)   // pick random index in 'symbols' array
                        password += symbols[symbolSelect]
                    }
                    4 -> {
                        if(nums.isEmpty()) continue
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
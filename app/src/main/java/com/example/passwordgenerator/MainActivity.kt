package com.example.passwordgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
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

    /**
     * generates the string 'password' with a random assortment of letters, numbers, and symbols; displays the password to the UI
     */
    fun generate(){
        password = ""
        var i = 0

        // make sure that at least one of the switches is on before generating
        var okayToGenerate = true
        if(!upperCaseSwitch.isChecked && !lowerCaseSwitch.isChecked && !digitsSwitch.isChecked && !symbolsSwitch.isChecked) {
            okayToGenerate = false
        }

        while(i < passLength && okayToGenerate) {
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
        // if password is supposed to be hidden, then don't update the display
        if(!hidePasswordSwitch.isChecked)
            passwordTextBox.text = password     // set text box on UI to the generated password
    }

    /**
     * common function to be used for the switch buttons
     * @param switch  Switch datatype that is to be checked if on or off (true or false)
     * @param array  Array<Char> Character array that is modified to either be assigned to 'sampleArray' or to an empty array
     * @param sampleArray Array<Char> Character array that is used to set 'array' if a condition is satisfied
     * @return  the modified Array<Char> Character array
     */
    fun commonSwitchFunction(switch: Switch, array:Array<Char>, sampleArray:Array<Char>):Array<Char> {
        var returnArray = array     // array to be returned
        if(switch.isChecked)
            returnArray = sampleArray
        else
            returnArray = arrayOf()
        return returnArray
    }

    // main function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        generate()

        // hide the password with asterisks when checked, and reveal it when unchecked
        hidePasswordSwitch.setOnClickListener {
            var tempString = ""
            if (hidePasswordSwitch.isChecked) {
                for (i in passwordTextBox.text.toString())
                    tempString += "*"
            } else
                tempString = password
            passwordTextBox.text = tempString
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
            try {
                if (Integer.parseInt(passwordLengthTextBox.text.toString()) in 1..12) {
                    passLength = Integer.parseInt(passwordLengthTextBox.text.toString())
                    passwordLengthTextBox.text.clear()
                } else {
                    if(!hidePasswordSwitch.isChecked) {
                        passwordTextBox.text = "Invalid Number!"
                        passwordLengthTextBox.text.clear()
                    } else {
                        passwordLengthTextBox.text.clear()
                    }
                }
            } catch(e:Exception){
                if(!hidePasswordSwitch.isChecked)
                    passwordTextBox.text = "Invalid!"
            }
        }

        // generate the random password using the 'random' package and arrays when the "GENERATE" button is pressed
        generateButton.setOnClickListener {
            generate()
        }
    }
}
package com.example.pinyinkeyboard

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener
import android.media.AudioManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputConnection


class PYKeyboard : InputMethodService(), OnKeyboardActionListener {
    private var kv: KeyboardView? = null
    private var keyboard: Keyboard? = null
    private var isCaps = false
    override fun onCreateInputView(): View {
        kv = layoutInflater.inflate(R.layout.keyboard, null) as KeyboardView
        keyboard = Keyboard(this, R.xml.qwerty)
        kv!!.keyboard = keyboard
        kv!!.setOnKeyboardActionListener(this)
        return kv!!
    }


    override fun onPress(i: Int) {}
    override fun onRelease(i: Int) {}
    override fun onKey(i: Int, ints: IntArray) {
        val ic = currentInputConnection
        playClick(i)
        when (i) {
            Keyboard.KEYCODE_DELETE -> ic.deleteSurroundingText(1, 0)
            Keyboard.KEYCODE_SHIFT -> {
                isCaps = !isCaps
                keyboard!!.isShifted = isCaps
                kv!!.invalidateAllKeys()
            }
            Keyboard.KEYCODE_DONE -> ic.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_ENTER
                )
            )


            else -> {
                var code = i.toChar()

                val vLetter: Char = 'v'

                val a1Letter: Char = '1'
                val a2Letter: Char = '2'
                val a3Letter: Char = '3'
                val a4Letter: Char = '4'

                val a5Letter: Char = '5'
                val a6Letter: Char = '6'
                val a7Letter: Char = '7'
                val a8Letter: Char = '8'

                val a9Letter: Char = '9'
                val a0Letter: Char = '0'
                val letter47: Char = '/'
                val letter46: Char = '.'

                val letter45: Char = '-'
                val letter44: Char = ','
                val letter43: Char = '+'
                val letter42: Char = '*'

                val letter41: Char = ')'
                val letter40: Char = '('
                val letter37: Char = '%'
                val letter38: Char = '&'

                val letter36: Char = '$'
                val letter35: Char = '#'
                val letter34: Char = '"'
                val letter33: Char = '!'



                when (code){
                    vLetter -> ic.commitText("ü", 1)

                    a1Letter -> ic.commitText("ā", 1)
                    a2Letter -> ic.commitText("á", 1)
                    a3Letter -> ic.commitText("ǎ", 1)
                    a4Letter -> ic.commitText("à", 1)

                    a5Letter -> ic.commitText("ē", 1)
                    a6Letter -> ic.commitText("é", 1)
                    a7Letter -> ic.commitText("ě", 1)
                    a8Letter -> ic.commitText("è", 1)

                    a9Letter -> ic.commitText("ō", 1)
                    a0Letter -> ic.commitText("ó", 1)
                    letter47 -> ic.commitText("ǒ", 1)
                    letter46 -> ic.commitText("ò", 1)

                    letter45 -> ic.commitText("ī", 1)
                    letter44 -> ic.commitText("í", 1)
                    letter43 -> ic.commitText("ǐ", 1)
                    letter42 -> ic.commitText("ì", 1)

                    letter41 -> ic.commitText("ū", 1)
                    letter40 -> ic.commitText("ú", 1)
                    letter37 -> ic.commitText("ǔ", 1)
                    letter38 -> ic.commitText("ù", 1)

                    letter36 -> ic.commitText("ǖ", 1)
                    letter35 -> ic.commitText("ǘ", 1)
                    letter34 -> ic.commitText("ǚ", 1)
                    letter33 -> ic.commitText("ǜ", 1)


                    else -> {
                        if (Character.isLetter(code) && isCaps) code =
                            Character.toUpperCase(code)
                        ic.commitText(code.toString(), 1)
                    }
                }


            }
        }

    }

    private fun playClick(i: Int) {
        val am =
            getSystemService(Context.AUDIO_SERVICE) as AudioManager
        when (i) {
            32 -> am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR)
            Keyboard.KEYCODE_DONE, 10 -> am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN)
            Keyboard.KEYCODE_DELETE -> am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE)
            else -> am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD)
        }
    }

    override fun onText(charSequence: CharSequence) {}
    override fun swipeLeft() {}
    override fun swipeRight() {}
    override fun swipeDown() {}
    override fun swipeUp() {}
}
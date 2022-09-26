package com.example.tekstospich

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class SecondActivity : AppCompatActivity() {
    private var textToSpeech: TextToSpeech? = null
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var imageButton: ImageButton
    private lateinit var textView:AppCompatTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        speechToText()
        textToSpeech()


    }


    private fun textToSpeech() {
    editText = findViewById(R.id.editText)
        button = findViewById(R.id.btnSpeech)
        textToSpeech = TextToSpeech(this, ) {status ->
            if (status != TextToSpeech.ERROR) {
            textToSpeech?.language = Locale.KOREA
            }
        }
        button.setOnClickListener {

            val text = editText.text.toString().trim()
            textToSpeech?.speak(text,TextToSpeech.QUEUE_FLUSH, null)
            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
        }

    }


    private fun speechToText() {
        val button1: ImageButton = findViewById(R.id.btnVoice)

        button1.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say something..")
            try {
                startActivityForResult(intent, 100)
            } catch (e: Exception) {
                toast(e.stackTraceToString())
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            if (resultCode == RESULT_OK || data != null) {
                val res: ArrayList<String> = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)!!
                textView.text = res[0]
            }
        }
    }



    private fun toast(speechText: String) {

    }

    private fun listener() = TextToSpeech.OnInitListener { status ->
        if (status == TextToSpeech.SUCCESS) {
            val res = textToSpeech?.setLanguage(Locale.US)
            if (res == TextToSpeech.LANG_MISSING_DATA || res == TextToSpeech.LANG_NOT_SUPPORTED) {
                toast("Language not supported")
            } else {
                button.isEnabled = true
                textToSpeech()
            }
        }
    }

    override fun onDestroy() {
        if (textToSpeech != null) {
            textToSpeech?.stop()
            textToSpeech?.shutdown()
        }
        super.onDestroy()
    }
}


import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.View
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.ToggleButton

class VoiceRecognitionActivity : Activity(), RecognitionListener {
    private var returnedText: TextView? = null
    private var toggleButton: ToggleButton? = null
    private var progressBar: ProgressBar? = null
    private var speech: SpeechRecognizer? = null
    private var recognizerIntent: Intent? = null
    private val LOG_TAG = "VoiceRecognitionActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_recognition)
        returnedText = findViewById<View>(R.id.textView1) as TextView
        progressBar = findViewById<View>(R.id.progressBar1) as ProgressBar
        toggleButton = findViewById<View>(R.id.toggleButton1) as ToggleButton
        progressBar!!.visibility = View.INVISIBLE
        speech = SpeechRecognizer.createSpeechRecognizer(this)
        speech.setRecognitionListener(this)
        recognizerIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        recognizerIntent!!.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en")
        recognizerIntent!!.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.packageName)
        recognizerIntent!!.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH
        )
        recognizerIntent!!.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.packageName)
        recognizerIntent!!.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
        toggleButton!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                progressBar!!.visibility = View.VISIBLE
                progressBar!!.isIndeterminate = true
                speech.startListening(recognizerIntent)
            } else {
                progressBar!!.isIndeterminate = false
                progressBar!!.visibility = View.INVISIBLE
                speech.stopListening()
            }
        }
    }

    public override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        if (speech != null) {
            speech!!.destroy()
            Log.i(LOG_TAG, "destroy")
        }
    }

    override fun onBeginningOfSpeech() {
        Log.i(LOG_TAG, "onBeginningOfSpeech")
        progressBar!!.isIndeterminate = false
        progressBar!!.max = 10
    }

    override fun onBufferReceived(buffer: ByteArray) {
        Log.i(LOG_TAG, "onBufferReceived: $buffer")
    }

    override fun onEndOfSpeech() {
        Log.i(LOG_TAG, "onEndOfSpeech")
        progressBar!!.isIndeterminate = true
        toggleButton!!.isChecked = false
    }

    override fun onError(errorCode: Int) {
        val errorMessage = getErrorText(errorCode)
        Log.d(LOG_TAG, "FAILED $errorMessage")
        returnedText!!.text = errorMessage
        toggleButton!!.isChecked = false
    }

    override fun onEvent(arg0: Int, arg1: Bundle) {
        Log.i(LOG_TAG, "onEvent")
    }

    override fun onPartialResults(arg0: Bundle) {
        Log.i(LOG_TAG, "onPartialResults")
    }

    override fun onReadyForSpeech(arg0: Bundle) {
        Log.i(LOG_TAG, "onReadyForSpeech")
    }

    override fun onResults(results: Bundle) {
        Log.i(LOG_TAG, "onResults")
        val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        var text = ""
        for (result in matches!!) text += """
     $result
     
     """.trimIndent()
        returnedText!!.text = text
    }

    override fun onRmsChanged(rmsdB: Float) {
        Log.i(LOG_TAG, "onRmsChanged: $rmsdB")
        progressBar!!.progress = rmsdB.toInt()
    }

    companion object {
        fun getErrorText(errorCode: Int): String {
            val message: String
            message = when (errorCode) {
                SpeechRecognizer.ERROR_AUDIO -> "Audio recording error"
                SpeechRecognizer.ERROR_CLIENT -> "Client side error"
                SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS -> "Insufficient permissions"
                SpeechRecognizer.ERROR_NETWORK -> "Network error"
                SpeechRecognizer.ERROR_NETWORK_TIMEOUT -> "Network timeout"
                SpeechRecognizer.ERROR_NO_MATCH -> "No match"
                SpeechRecognizer.ERROR_RECOGNIZER_BUSY -> "RecognitionService busy"
                SpeechRecognizer.ERROR_SERVER -> "error from server"
                SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "No speech input"
                else -> "Didn't understand, please try again."
            }
            return message
        }
    }
}
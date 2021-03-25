package com.example.notegenix

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_new_note.*

class NewNoteActivity : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)

        val back = findViewById<FloatingActionButton>(R.id.backButton)
        back.setOnClickListener {
            finish()
        }
    }

    fun submitData(view: View) {
        val replyIntent = Intent()
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            //viewModel.insertNote(Note(noteText))
            replyIntent.putExtra(EXTRA_REPLY, noteText)
            setResult(Activity.RESULT_OK, replyIntent)
            Toast.makeText(this, "$noteText inserted", Toast.LENGTH_LONG).show()
        }
        finish()
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}
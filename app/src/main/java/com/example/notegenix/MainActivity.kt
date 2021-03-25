package com.example.notegenix

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), INotesRVAdapter {
    private lateinit var viewModel: NoteViewModel
    private val newNoteActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {list->
            list?.let{
                adapter.updateList(it)  //update only when list is not null
            }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewNoteActivity::class.java)
            startActivityForResult(intent, newNoteActivityRequestCode)
        }
    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} deleted", Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newNoteActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewNoteActivity.EXTRA_REPLY)?.let { reply ->
                viewModel.insertNote(Note(reply))
            }
        } else {
            Toast.makeText(
                applicationContext,
                "Not saved",
                Toast.LENGTH_LONG
            ).show()
        }
    }

/*
    fun submitData(view: View) {
        val noteText = input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "$noteText inserted", Toast.LENGTH_LONG).show()
        }
    }
 */
}

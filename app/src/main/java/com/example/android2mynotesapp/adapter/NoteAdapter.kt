package com.example.android2mynotesapp.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android2mynotesapp.CustomOnItemClickListener
import com.example.android2mynotesapp.NoteAddUpdateActivity
import com.example.android2mynotesapp.R
import com.example.android2mynotesapp.databinding.ItemNoteBinding
import com.example.android2mynotesapp.entity.Note

class NoteAdapter(private val activity: Activity) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var listNotes = ArrayList<Note>()
        set(listNotes) {
            if (listNotes.size > 0) {
                this.listNotes.clear()
            }
            this.listNotes.addAll(listNotes)

            notifyDataSetChanged()
        }

    fun addItem(note: Note) {
        this.listNotes.add(note)
        notifyItemInserted(this.listNotes.size - 1)
    }

    fun updateItem(i: Int, note: Note) {
        this.listNotes[i] = note
        notifyItemChanged(i, note)
    }

    fun removeItem(i: Int) {
        this.listNotes.removeAt(i)
        notifyItemRemoved(i)
        notifyItemRangeChanged(i, this.listNotes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNotes[position])
    }

    override fun getItemCount(): Int = this.listNotes.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)
        fun bind(note: Note) {
            with(binding) {
                tvItemTitle.text = note.title
                tvItemDate.text = note.date
                tvItemDescription.text = note.description
                cvItemNote.setOnClickListener(
                    CustomOnItemClickListener(
                        adapterPosition,
                        object : CustomOnItemClickListener.OnItemClickCallback {
                            override fun onItemClicked(v: View?, i: Int) {
                                val intent =
                                    Intent(activity, NoteAddUpdateActivity::class.java).apply {
                                        putExtra(NoteAddUpdateActivity.EXTRA_POSITION, i)
                                        putExtra(NoteAddUpdateActivity.EXTRA_NOTE, i)
                                    }

                                activity.startActivityForResult(
                                    intent,
                                    NoteAddUpdateActivity.REQUEST_UPDATE
                                )
                            }
                        })
                )
            }
        }
    }
}
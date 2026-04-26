package com.example.notes.service;

import com.example.notes.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoteService {
    
    private Map<String, Note> database = new HashMap<>();

    // Synchronized ensures thread safety when multiple users add notes
    synchronized public Note addNote(Note note) {
        if (note.getId() == null) {
            note.setId(java.util.UUID.randomUUID().toString());
        }
        database.put(note.getId(), note);
        return note;
    }

    synchronized public List<Note> getAllNotes() {
        return new ArrayList<>(database.values());
    }
}

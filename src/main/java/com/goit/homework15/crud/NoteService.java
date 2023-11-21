package com.goit.homework15.crud;

import com.goit.homework15.entities.Note;
import com.goit.homework15.noteexceptions.NoSuchNoteException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteService {
    private final List<Note> notes;
    private static int id;

    {
        notes = new ArrayList<>();
    }

    public List<Note> listAll(){
        return notes;
    }

    public synchronized Note add(Note note){
        note.setId(++id);
        notes.add(note);
        return note;
    }

    public void deleteById(long id){
        Note noteToDelete = notes.stream()
                .filter(note -> note.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchNoteException::new);
        notes.remove(noteToDelete);
    }

    public void update(Note note){
        long id = note.getId();
        Note noteToUpdate = notes.stream()
                .filter(currentNote -> currentNote.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchNoteException::new);
        noteToUpdate.setContent(note.getContent());
        noteToUpdate.setTitle(note.getTitle());
    }

    public Note getBYId(long id){
        return notes.stream()
                .filter(currentNote -> currentNote.getId() == id)
                .findFirst()
                .orElseThrow(NoSuchNoteException::new);
    }

}

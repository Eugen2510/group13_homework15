package com.goit.homework15.noteexceptions;

public class NoSuchNoteException extends IllegalArgumentException{
    public NoSuchNoteException(){
        super("You such note exists");
    }
}

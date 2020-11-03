package com.taip.nextvision.NotesEngine;

import com.taip.nextvision.CommandEngine;

public class NotesEngine implements CommandEngine {
    @Override
    public String execute(String cmd) {
        return "";
    }

    public void createNote(String note) {}
    public void deleteNote(String note) {}
    public void modifyNote(String note) {}
    public String readNotes(){ return ""; }

}

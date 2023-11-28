package com.goit.homework15.controller;

import com.goit.homework15.crud.NoteService;
import com.goit.homework15.entity.Note;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public String getWelcomePage(){
        return "welcome_page";
    }

    @GetMapping("/list")
    public ModelAndView getNotes(){
        ModelAndView modelAndView = new ModelAndView("note_list");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createNote(@RequestParam(name = "noteTitle", required = false) String noteTitle,
                             @RequestParam (name = "noteContent", required = false) String noteContent) {
        Note note = noteService.createNote(noteTitle, noteContent);
        noteService.add(note);
        return getNotes();
    }

    @GetMapping("/create")
    public String showPageForCreate(){
        return "create_note";
    }

    @PostMapping("/delete")
    public ModelAndView deleteNote(@RequestParam(name = "id") long id){
        noteService.deleteById(id);
        return getNotes();
    }


    @RequestMapping("/edit/{id}")
    public ModelAndView editNote(@PathVariable(name = "id") long id,
                                 @RequestParam(name = "title", required = false) String title,
                                 @RequestParam(name = "content", required = false) String content,
                                 HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        if (request.getMethod().equals("GET")){
            modelAndView.setViewName("edit_note");
            modelAndView.addObject("note", noteService.getById(id));
            return modelAndView;
        }

        noteService.update(noteService.createNote(id, title, content));
        return getNotes();
    }
}
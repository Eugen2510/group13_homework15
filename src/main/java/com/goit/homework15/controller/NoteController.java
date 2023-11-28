package com.goit.homework15.controller;

import com.goit.homework15.crud.NoteService;
import com.goit.homework15.entity.Note;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RequiredArgsConstructor
@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public String getWelcomePage() {
        return "welcome_page";
    }

    @GetMapping("/list")
    public ModelAndView getNotes() {
        ModelAndView modelAndView = new ModelAndView("note_list");
        modelAndView.addObject("notes", noteService.listAll());
        return modelAndView;
    }

    @RequestMapping("/create")
    public String createNote(@RequestParam(name = "title", required = false) String title,
                             @RequestParam(name = "content", required = false) String content,
                             HttpServletRequest request) {
        if (request.getMethod().equals("GET")) {
            return "create_note";
        }
        Note note = noteService.createNote(title, content);
        noteService.add(note);
        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam(name = "id") long id) {
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @RequestMapping("/edit/{id}")
    public String editNote(@PathVariable(name = "id") long id,
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "content", required = false) String content,
                           HttpServletRequest request,
                           Model model) {
        if (request.getMethod().equals("GET")) {
            model.addAttribute("note", noteService.getById(id));
            return "edit_note";
        }
        noteService.update(noteService.createNote(id, title, content));
        model.addAttribute(noteService.listAll());
        return "redirect:/note/list";
    }
}

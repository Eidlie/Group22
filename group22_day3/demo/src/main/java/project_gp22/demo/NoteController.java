package project_gp22.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import project_gp22.demo.Note;
import project_gp22.demo.NoteRepository;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteRepository repo;

    //  list page
    @GetMapping
    @ResponseBody
    public List<Note> getAllNotes() {
        return repo.findAll();
    }
    @PostMapping
    public Note create(@RequestBody Note note) {
        return repo.save(note);
    }
    @GetMapping("/{id}")
    @ResponseBody
    public Note getNote(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Note update(@PathVariable Long id, @RequestBody Note newNote) {

        Note existing = repo.findById(id).orElseThrow();

        existing.setTitle(newNote.getTitle());
        existing.setContent(newNote.getContent());

        return repo.save(existing);
    }

    // go to edit page
    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, Model model) {
        Note note = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        model.addAttribute("note", note);
        return "edit";
    }

    // update note
    @PostMapping("/update/{id}")
    public String updateNote(@PathVariable Long id, @ModelAttribute Note formNote) {

        Note existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        existing.setTitle(formNote.getTitle());
        existing.setContent(formNote.getContent());

        repo.save(existing);

        return "redirect:/notes";
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
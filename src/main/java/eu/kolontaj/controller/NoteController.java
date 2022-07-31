package eu.kolontaj.controller;

import eu.kolontaj.dto.GuitarNotesDto;
import eu.kolontaj.dto.NotesRequestDto;
import eu.kolontaj.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<GuitarNotesDto> convert(@RequestBody NotesRequestDto notes) {
        GuitarNotesDto convert = noteService.convert(notes.getNotes());
        return ResponseEntity.ok(convert);
    }

}

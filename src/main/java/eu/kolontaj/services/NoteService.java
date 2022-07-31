package eu.kolontaj.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.kolontaj.dto.GuitarNotesDto;
import eu.kolontaj.model.GuitarNote;
import eu.kolontaj.model.GuitarString;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class NoteService {

    private final Map<String, GuitarNote> noteRules;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NoteService() {
        this.noteRules = fillNoteRules();
    }

    @PostConstruct
    private Map<String, GuitarNote> fillNoteRules() {
        Map<String, GuitarNote> rules = new HashMap<>();
        List<String> rulesFromFile = new ArrayList<>();
        try {
            rulesFromFile = objectMapper.readValue(new ClassPathResource("./rules.json").getFile(), List.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        rulesFromFile.stream()
                .map(rule -> rule.split(","))
                .forEach(notes -> rules.put(notes[0], getGuitarNoteFromRule(notes[1])));

        return rules;
    }

    private GuitarNote getGuitarNoteFromRule(String rule) {
        String[] stringAndFret = rule.split(":");
        return GuitarNote
                .builder()
                .string(GuitarString.lockup(Integer.parseInt(stringAndFret[0])))
                .fret(Integer.parseInt(stringAndFret[1]))
                .build();
    }

    public GuitarNotesDto convert(String notesString) {
        String[] notes = notesString.split(",");
        List<GuitarNote> guitarNotes = Stream.of(notes)
                .map(noteRules::get)
                .toList();

        return buildGuitarNotesDto(guitarNotes);
    }

    private GuitarNotesDto buildGuitarNotesDto(List<GuitarNote> guitarNotes) {
        List<StringBuilder> strings =
                IntStream.range(0, 6)
                        .mapToObj(i -> new StringBuilder())
                        .collect(Collectors.toList());

        for (GuitarNote note : guitarNotes) {
            for (int i = 0; i < 6; i++) {
                if (note.getString().getVal() - 1 == i) {
                    strings.get(i).append(String.format("-%S-", note.getFret()));
                } else {
                    strings.get(i).append("---");
                }
            }
        }

        return GuitarNotesDto
                .builder()
                .string1(strings.get(0).toString())
                .string2(strings.get(1).toString())
                .string3(strings.get(2).toString())
                .string4(strings.get(3).toString())
                .string5(strings.get(4).toString())
                .string6(strings.get(5).toString())
                .build();
    }

}

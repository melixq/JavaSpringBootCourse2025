package ru.kuznetsov.MySpringBoot2Dbase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kuznetsov.MySpringBoot2Dbase.entity.Discipline;
import ru.kuznetsov.MySpringBoot2Dbase.service.DisciplineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/disciplines")
public class DisciplineController {
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllDisciplines() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Discipline> disciplines = disciplineService.getAllDisciplines();
            response.put("success", true);
            response.put("data", disciplines);
            response.put("message", "Disciplines retrieved successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to retrieve disciplines");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getDiscipline(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Discipline discipline = disciplineService.getDisciplineById(id);
            if (discipline != null) {
                response.put("success", true);
                response.put("data", discipline);
                response.put("message", "Discipline found successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Discipline not found");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to retrieve discipline");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> saveDiscipline(@RequestBody Discipline discipline) {
        Map<String, Object> response = new HashMap<>();
        try {
            Discipline savedDiscipline = disciplineService.saveDiscipline(discipline);
            response.put("success", true);
            response.put("data", savedDiscipline);
            response.put("message", "Discipline created successfully");
            return ResponseEntity.status(201).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to create discipline");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateDiscipline(@PathVariable("id") int id, @RequestBody Discipline discipline) {
        Map<String, Object> response = new HashMap<>();
        try {
            discipline.setId(id);
            Discipline updatedDiscipline = disciplineService.saveDiscipline(discipline);
            response.put("success", true);
            response.put("data", updatedDiscipline);
            response.put("message", "Discipline updated successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to update discipline");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteDiscipline(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        try {
            disciplineService.deleteDisciplineById(id);
            response.put("success", true);
            response.put("message", "Discipline deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to delete discipline");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/semester/{semester}")
    public ResponseEntity<Map<String, Object>> getDisciplinesBySemester(@PathVariable("semester") int semester) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Discipline> disciplines = disciplineService.getDisciplinesBySemester(semester);
            response.put("success", true);
            response.put("data", disciplines);
            response.put("message", "Disciplines retrieved successfully by semester");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to retrieve disciplines by semester");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/credits/{credits}")
    public ResponseEntity<Map<String, Object>> getDisciplinesByCredits(@PathVariable("credits") int credits) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Discipline> disciplines = disciplineService.getDisciplinesByCredits(credits);
            response.put("success", true);
            response.put("data", disciplines);
            response.put("message", "Disciplines retrieved successfully by credits");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to retrieve disciplines by credits");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getDisciplinesByNameContaining(@RequestParam("name") String name) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Discipline> disciplines = disciplineService.getDisciplinesByNameContaining(name);
            response.put("success", true);
            response.put("data", disciplines);
            response.put("message", "Disciplines retrieved successfully by name search");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("error", e.getMessage());
            response.put("message", "Failed to search disciplines by name");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}

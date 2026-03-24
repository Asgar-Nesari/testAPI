package com.test.deploy.controller;

import com.test.deploy.dto.RecordDTO;
import com.test.deploy.service.RecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @PostMapping
    public ResponseEntity<RecordDTO.Response> create(@Valid @RequestBody RecordDTO.Request request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recordService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<RecordDTO.Response>> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String purpose) {

        if (title != null && !title.isBlank()) return ResponseEntity.ok(recordService.searchByTitle(title));
        if (purpose != null && !purpose.isBlank()) return ResponseEntity.ok(recordService.searchByPurpose(purpose));
        return ResponseEntity.ok(recordService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordDTO.Response> getById(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecordDTO.Response> update(
            @PathVariable Long id,
            @Valid @RequestBody RecordDTO.Request request) {
        return ResponseEntity.ok(recordService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recordService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

package com.test.deploy.service;

import com.test.deploy.dto.RecordDTO;
import com.test.deploy.exception.ResourceNotFoundException;
import com.test.deploy.model.RecordEntity;
import com.test.deploy.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;

    // ── CREATE ───────────────────────────────────────────────────────────────
    public RecordDTO.Response create(RecordDTO.Request request) {
        RecordEntity record = new RecordEntity();
        record.setTitle(request.getTitle());
        record.setPurpose(request.getPurpose());
        record.setDescription(request.getDescription());

        RecordEntity saved = recordRepository.save(record);
        return toResponse(saved);
    }

    // ── READ ALL ─────────────────────────────────────────────────────────────
    public List<RecordDTO.Response> getAll() {
        return recordRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ── READ BY ID ───────────────────────────────────────────────────────────
    public RecordDTO.Response getById(Long id) {
        RecordEntity record = findOrThrow(id);
        return toResponse(record);
    }

    // ── UPDATE ───────────────────────────────────────────────────────────────
    public RecordDTO.Response update(Long id, RecordDTO.Request request) {
        RecordEntity record = findOrThrow(id);

        record.setTitle(request.getTitle());
        record.setPurpose(request.getPurpose());
        record.setDescription(request.getDescription());

        RecordEntity updated = recordRepository.save(record);
        return toResponse(updated);
    }

    // ── DELETE ───────────────────────────────────────────────────────────────
    public void delete(Long id) {
        RecordEntity record = findOrThrow(id);
        recordRepository.delete(record);
    }

    // ── SEARCH BY TITLE ──────────────────────────────────────────────────────
    public List<RecordDTO.Response> searchByTitle(String title) {
        return recordRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ── SEARCH BY PURPOSE ────────────────────────────────────────────────────
    public List<RecordDTO.Response> searchByPurpose(String purpose) {
        return recordRepository.findByPurposeContainingIgnoreCase(purpose)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ── Private Helpers ──────────────────────────────────────────────────────
    private RecordEntity findOrThrow(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record", id));
    }

    private RecordDTO.Response toResponse(RecordEntity record) {
        RecordDTO.Response response = new RecordDTO.Response();
        response.setId(record.getId());
        response.setTitle(record.getTitle());
        response.setPurpose(record.getPurpose());
        response.setDescription(record.getDescription());
        response.setCreatedAt(record.getCreatedAt());
        response.setUpdatedAt(record.getUpdatedAt());
        return response;
    }


//    private Record findOrThrow(Long id) {
//        return recordRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Record", id));
//    }
//
//    private RecordDTO.Response toResponse(Record record) {
//        RecordDTO.Response res = new RecordDTO.Response();
//        res.setId(record.getId());
//        res.setTitle(record.getTitle());
//        res.setPurpose(record.getPurpose());
//        res.setDescription(record.getDescription());
//        res.setCreatedAt(record.getCreatedAt());
//        res.setUpdatedAt(record.getUpdatedAt());
//        return res;
//    }

}

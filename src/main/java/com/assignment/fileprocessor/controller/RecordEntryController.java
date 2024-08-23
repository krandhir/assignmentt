package com.assignment.fileprocessor.controller;

import com.assignment.fileprocessor.model.RecordEntry;
import com.assignment.fileprocessor.service.RecordEntryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Record entry controller.
 */
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/entries")
@Tag(name = "File Processor API", description = "API for managing records")
public class RecordEntryController {

  @Autowired
  private RecordEntryService recordEntryService;

  /**
   * Upload file response entity.
   *
   * @param file the file
   * @return the response entity
   */
  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
      recordEntryService.uploadCsvFile(file);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
    }
  }

  /**
   * Gets all entries.
   *
   * @return the all entries
   */
  @GetMapping
  public List<RecordEntry> getAllEntries() {
    return recordEntryService.fetchAllEntries();
  }

  /**
   * Gets entry by code.
   *
   * @param code the code
   * @return the entry by code
   */
  @GetMapping("/{code}")
  public ResponseEntity<RecordEntry> getEntryByCode(@PathVariable String code) {
    Optional<RecordEntry> entry = recordEntryService.fetchEntryByCode(code);
    return entry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  /**
   * Delete all entries response entity.
   *
   * @return the response entity
   */
  @DeleteMapping
  public ResponseEntity<String> deleteAllEntries() {
    recordEntryService.deleteAllEntries();
    return ResponseEntity.noContent().build();
  }
}

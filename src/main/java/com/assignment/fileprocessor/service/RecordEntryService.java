package com.assignment.fileprocessor.service;

import com.assignment.fileprocessor.Repository.RecordEntryRepository;
import com.assignment.fileprocessor.model.RecordEntry;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * The type Record entry service.
 */
@Service
public class RecordEntryService {

  @Autowired
  private RecordEntryRepository recordEntryRepository;

  /**
   * Upload csv file.
   *
   * @param file the file
   * @throws Exception the exception
   */
  public void uploadCsvFile(MultipartFile file) throws Exception {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
      String headerLine = reader.readLine();
      if (headerLine == null) {
        throw new Exception("CSV file is empty");
      }

      // Parse the header to get field indexes
      String[] headers = headerLine.split(",");
      Map<String, Integer> headerMap = new HashMap<>();
      for (int i = 0; i < headers.length; i++) {
        headerMap.put(headers[i].trim().replace("\"", ""), i);
      }

      String line;
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",", -1);

        RecordEntry entry = new RecordEntry();
        entry.setSource(removeQuotes(fields[headerMap.get("source")]));
        entry.setCodeListCode(removeQuotes(fields[headerMap.get("codeListCode")]));
        entry.setCode(removeQuotes(fields[headerMap.get("code")]));
        entry.setDisplayValue(removeQuotes(fields[headerMap.get("displayValue")]));
        entry.setLongDescription(removeQuotes(fields[headerMap.get("longDescription")]));
        entry.setFromDate(removeQuotes(fields[headerMap.get("fromDate")]));
        entry.setToDate(removeQuotes(fields[headerMap.get("toDate")]));
        entry.setSortingPriority(removeQuotes(fields[headerMap.get("sortingPriority")]));

        recordEntryRepository.save(entry);
      }
    }
  }

  /**
   * Fetch all entries list.
   *
   * @return the list
   */
  public List<RecordEntry> fetchAllEntries() {
    return recordEntryRepository.findAll();
  }

  /**
   * Fetch entry by code optional.
   *
   * @param code the code
   * @return the optional
   */
  public Optional<RecordEntry> fetchEntryByCode(String code) {
    return recordEntryRepository.findById(code);
  }

  /**
   * Delete all entries.
   */
  public void deleteAllEntries() {
    recordEntryRepository.deleteAll();
  }

  // Utility method to remove quotes from a string
  private String removeQuotes(String value) {
    if (value == null) {
      return null;
    }
    return value.replaceAll("^\"|\"$", "").trim();
  }
}

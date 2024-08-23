package com.assignment.fileprocessor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Record entry.
 */
@Schema(description = "RecordEntry entity")
@Getter
@Setter
@Entity(name = "RecordEntry")
public class RecordEntry {

  private String source;
  private String codeListCode;

  @Id
  private String code;

  private String displayValue;
  private String longDescription;
  private String fromDate;
  private String toDate;
  private String sortingPriority;

}

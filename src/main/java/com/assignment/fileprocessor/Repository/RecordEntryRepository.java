package com.assignment.fileprocessor.Repository;

import com.assignment.fileprocessor.model.RecordEntry;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Record entry repository.
 */
public interface RecordEntryRepository extends JpaRepository<RecordEntry, String> {

}

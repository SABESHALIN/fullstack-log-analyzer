package com.loganalyzer.log_api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Integer> {
	
	
    Page<LogEntry> findByMessageContainingIgnoreCase(String query, Pageable pageable);

}

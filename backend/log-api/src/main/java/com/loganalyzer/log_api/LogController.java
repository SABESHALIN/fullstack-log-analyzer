package com.loganalyzer.log_api; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") 
@CrossOrigin(origins = "*") 
public class LogController {

    @Autowired
    private LogEntryRepository logEntryRepository;

    
    @GetMapping("/logs/paginated")
    public Page<LogEntry> getPaginatedLogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String search) {
        
        Pageable pageable = PageRequest.of(page, size, Sort.by("eventTime").descending());
        
        return logEntryRepository.findByMessageContainingIgnoreCase(search, pageable);
    }

    
    @GetMapping("/stats")
    public Map<String, Long> getLogStats() {
        List<LogEntry> allLogs = logEntryRepository.findAll();

        Map<String, Long> stats = allLogs.stream()
                .collect(Collectors.groupingBy(LogEntry::getLog_level, Collectors.counting()));
        
        stats.put("totalEntries", (long) allLogs.size());
        
        stats.putIfAbsent("ERROR", 0L);
        stats.putIfAbsent("WARN", 0L);
        stats.putIfAbsent("INFO", 0L);
        stats.putIfAbsent("CRITICAL", 0L);
        
        return stats;
    }
}
package com.loganalyzer.log_api;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "log_data") 
public class LogEntry {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    private String eventTime; 
    private String log_level;  
    private String message;

    public LogEntry() {
    }

    public LogEntry(String timestamp, String logLevel, String message) {
        this.eventTime = timestamp;
        this.log_level = logLevel;
        this.message = message;
    }

   
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent_time() {
        return eventTime;
    }

    public void setEvent_time(String event_time) {
        this.eventTime = event_time;
    }

    public String getLog_level() {
        return log_level;
    }

    public void setLog_level(String log_level) {
        this.log_level = log_level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
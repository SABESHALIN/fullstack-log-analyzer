package com.loganalyzer.log_api;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogParser {

	
	public List<LogEntry> parse (String filePath){
		List<LogEntry> logEntries = new ArrayList<>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			String line;
			
			while((line = reader.readLine())!= null) {
				String[] parts = line.split(" ",4);
				
				if(parts.length >= 4) {
					String timestamp = parts[0]+" "+parts[1];
					String logLevel = parts[2];
					String message  = parts[3];
					
					logEntries.add(new LogEntry(timestamp, logLevel, message));
					
				}
				
			}
			
		}
		catch(IOException e) {
			System.out.println("ERROR: Failed to read the log file.");
			e.printStackTrace();
		}
		System.out.println("SUCCESS: Parsed "+logEntries.size() + " log entries from the file.");
		return logEntries;
	}
}

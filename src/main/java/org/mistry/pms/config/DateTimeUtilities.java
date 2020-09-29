package org.mistry.pms.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtilities {
	
	LocalDateTime dateTimeToFormat;
	DateTimeFormatter defaultFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  
		
	public DateTimeUtilities() {
		this.dateTimeToFormat = LocalDateTime.now();
	}
	
	public LocalDateTime getDateTimeToFormat() {
		return this.dateTimeToFormat;
	}
	
	public void setDateTimeToFormat(LocalDateTime dateTimeToFormat) {
		this.dateTimeToFormat = dateTimeToFormat;
	}

	public DateTimeFormatter getDefaultFormat() {
		return this.defaultFormat;
	}

	public void setDefaultFormat(DateTimeFormatter defaultFormat) {
		this.defaultFormat = defaultFormat;
	}
	
	public String defaultDateTime() {
		return dateTimeToFormat.format(defaultFormat);   
	}
}

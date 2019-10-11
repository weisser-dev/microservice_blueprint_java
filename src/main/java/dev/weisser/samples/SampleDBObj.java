package dev.weisser.samples;

import java.util.Date;
import java.util.UUID;

public class SampleDBObj
{
    private Date timestamp;
    private String uid;
    private String name;
    private String text;
    private String description;

    public SampleDBObj(String name, String text, String description) {
    	this.timestamp = new Date();
    	this.uid = UUID.randomUUID().toString();
    	this.name = name;
    	this.text = text;
    	this.description = description;

    }
    
    public SampleDBObj(Date timestamp, String uid, String name, String text, String description) {
    	this.timestamp = timestamp;
    	this.uid = uid;
    	this.name = name;
    	this.text = text;
    	this.description = description;

    }

	public String toCSVLine() {
		return this.timestamp + "," + this.uid + "," + this.name + "," + this.text + "," + this.description;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public String getDescription() {
		return description;
	}
	
}
	
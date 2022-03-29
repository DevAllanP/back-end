package fr.insys.commerce.exceptions;

import java.sql.Timestamp;

public class ErrorResponse {
	private int status;
	private String message;
	
	public boolean getError() {
		return true;
	}

	public String getTimestamp() {
		return new Timestamp(System.currentTimeMillis()).toString();
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
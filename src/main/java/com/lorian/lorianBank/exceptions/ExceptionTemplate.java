package com.lorian.lorianBank.exceptions;

import java.time.Instant;
import java.util.Objects;

public class ExceptionTemplate {
	
	// Template para o ResponseEntityExceptionHandler
	
	private Instant timestamp;
	private Integer status;
	private String error;
	private String path;

	public ExceptionTemplate() {}
	
	public ExceptionTemplate(Instant timestamp, Integer status, String error, String path) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "ExceptionTemplate [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", path="
				+ path + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(error, path, status, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExceptionTemplate other = (ExceptionTemplate) obj;
		return Objects.equals(error, other.error) && Objects.equals(path, other.path)
				&& Objects.equals(status, other.status) && Objects.equals(timestamp, other.timestamp);
	}
	
}

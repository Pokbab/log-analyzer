package com.baron.model;

import java.time.LocalDateTime;

/**
 * 각 Log의 데이터를 담는 모델클래스
 */
public class LogModel {
	private StatusType statusType;
	private APIServiceType apiServiceType;
	private String apiKey;
	private String query;
	private BrowserType browserType;
	private LocalDateTime callTime;

	public StatusType getStatusType() {
		return statusType;
	}

	public void setStatusType(StatusType statusType) {
		this.statusType = statusType;
	}

	public APIServiceType getApiServiceType() {
		return apiServiceType;
	}

	public void setApiServiceType(APIServiceType apiServiceType) {
		this.apiServiceType = apiServiceType;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public BrowserType getBrowserType() {
		return browserType;
	}

	public void setBrowserType(BrowserType browserType) {
		this.browserType = browserType;
	}

	public LocalDateTime getCallTime() {
		return callTime;
	}

	public void setCallTime(LocalDateTime callTime) {
		this.callTime = callTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiKey == null) ? 0 : apiKey.hashCode());
		result = prime * result
				+ ((apiServiceType == null) ? 0 : apiServiceType.hashCode());
		result = prime * result
				+ ((browserType == null) ? 0 : browserType.hashCode());
		result = prime * result
				+ ((callTime == null) ? 0 : callTime.hashCode());
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		result = prime * result
				+ ((statusType == null) ? 0 : statusType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogModel other = (LogModel) obj;
		if (apiKey == null) {
			if (other.apiKey != null)
				return false;
		} else if (!apiKey.equals(other.apiKey))
			return false;
		if (apiServiceType != other.apiServiceType)
			return false;
		if (browserType != other.browserType)
			return false;
		if (callTime == null) {
			if (other.callTime != null)
				return false;
		} else if (!callTime.equals(other.callTime))
			return false;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		if (statusType != other.statusType)
			return false;
		return true;
	}
}

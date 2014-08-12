package com.baron.model;

import java.time.LocalDateTime;
import java.util.Map;

public class OutputModel {
	private String mostCallApikey;
	private Map<StatusType, Integer> statusCodeResultMap;
	private Map<APIServiceType, Integer> requestCountPerApiServiceMap;
	private LocalDateTime peakHours;
	private Map<BrowserType, Float> ratePerUsedBrowserMap;

	public String getMostCallApikey() {
		return mostCallApikey;
	}

	public void setMostCallApikey(String mostCallApikey) {
		this.mostCallApikey = mostCallApikey;
	}

	public Map<StatusType, Integer> getStatusCodeResultMap() {
		return statusCodeResultMap;
	}

	public void setStatusCodeResultMap(
			Map<StatusType, Integer> statusCodeResultMap) {
		this.statusCodeResultMap = statusCodeResultMap;
	}

	public Map<APIServiceType, Integer> getRequestCountPerApiServiceMap() {
		return requestCountPerApiServiceMap;
	}

	public void setRequestCountPerApiServiceMap(
			Map<APIServiceType, Integer> requestCountPerApiServiceMap) {
		this.requestCountPerApiServiceMap = requestCountPerApiServiceMap;
	}

	public LocalDateTime getPeakHours() {
		return peakHours;
	}

	public void setPeakHours(LocalDateTime peakHours) {
		this.peakHours = peakHours;
	}

	public Map<BrowserType, Float> getRatePerUsedBrowserMap() {
		return ratePerUsedBrowserMap;
	}

	public void setRatePerUsedBrowserMap(
			Map<BrowserType, Float> ratePerUsedBrowserMap) {
		this.ratePerUsedBrowserMap = ratePerUsedBrowserMap;
	}
}

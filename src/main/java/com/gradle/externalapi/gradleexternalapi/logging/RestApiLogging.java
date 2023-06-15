package com.gradle.externalapi.gradleexternalapi.logging;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Layer based logging class - Rest API Client
 *
 */
@Component
@Slf4j
public class RestApiLogging {
	
	/**
	 * Info logs
	 */
	public void log00001IS (String message) {
		log.info("[00001IRA]" + message);
	}
	
	/**
	 * Error logs
	 */
	public void log00001ES (String message, Throwable exception) {
		log.error("[00001ERA]" + message);
	}
	
	/**
	 * Debug logs
	 */
	public void log00001DS (String message, Object...args) {
		log.debug("[00001DRA]" + message, args);
	}
	
	/**
	 * Warning logs
	 */
	public void log00001WS (String message) {
		log.warn("[00001WRA]" + message);
	}

}

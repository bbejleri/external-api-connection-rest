package com.gradle.externalapi.gradleexternalapi.logging;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Layer based logging class - Controller
 *
 */
@Component
@Slf4j
public class ServiceLogging {
	
	/**
	 * Info logs
	 */
	public void log00001IS (String message) {
		log.info("[00001IS]" + message);
	}
	
	/**
	 * Error logs
	 */
	public void log00001ES (String message, Throwable exception) {
		log.error("[00001ES]" + message);
	}
	
	/**
	 * Debug logs
	 */
	public void log00001DS (String message, Object...args) {
		log.debug("[00001DS]" + message, args);
	}
	
	/**
	 * Warning logs
	 */
	public void log00001WS (String message) {
		log.info("[00001WS]" + message);
	}
}

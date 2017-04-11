package org.armstrhb.prototype.behavior.service;

import java.util.concurrent.CompletableFuture;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.armstrhb.prototype.behavior.model.input.Input;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import reactor.bus.Event;
import reactor.bus.EventBus;

@Configuration
public class FeedService {
	private static final Logger log = Logger.getLogger(FeedService.class);
	private static final long DELAY_MINIMUM = 250;
	private static final long DELAY_MAXIMUM = 1250;
	
	@Autowired
	EventBus eventBus;
	
	private boolean stillActive;
	
	public void kickOff() {
		log.info("kicking off feed service");
		stillActive = true;
		
		while (stillActive) {
			randomizeMessage();
		}
		
		log.info("feed service finished");
	}
	
	private void randomizeMessage() {
		try {
			long sleepInterval = DELAY_MINIMUM + (long)(DELAY_MAXIMUM * Math.random());
			log.debug("delaying response " + sleepInterval + "ms");
			  
			Thread.sleep(sleepInterval);
		} catch (Exception e) {
			log.error("exception received", e);
		}
		
		try {
			eventBus.notify("sendMessage", Event.wrap(new Input(getRandomMessage())));
		} catch (IllegalArgumentException iae) {
			//we're shutting down
		} catch (Exception e) {
			log.error("exception encountered", e);
		}
	}
	
	private String getRandomMessage() {
		String message;
		if (shouldShutdown()) {
			message = Input.SHUTDOWN_STATE;
			stillActive = false;
		} else if (shouldStateChange()) {
			message = Input.CHANGE_STATE;
		} else {
			message = RandomStringUtils.randomAlphabetic(10);
		}
		
		return message;
	}
	
	private boolean shouldStateChange() {
		return Math.random() >= 0.9;
	}
	
	private boolean shouldShutdown() {
		return Math.random() >= 0.95;
	}
}

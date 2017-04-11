package org.armstrhb.prototype.behavior.service;

import org.apache.log4j.Logger;
import org.armstrhb.prototype.behavior.Behavior;
import org.armstrhb.prototype.behavior.ShutdownBehavior;
import org.armstrhb.prototype.behavior.StringReversalBehavior;
import org.armstrhb.prototype.behavior.StringUpperCaseBehavior;
import org.armstrhb.prototype.behavior.model.input.Input;
import org.armstrhb.prototype.behavior.model.output.Output;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import reactor.bus.Event;
import reactor.fn.Consumer;

@Service
@Configuration
public class ActService implements Consumer<Event<Input>> {
	private static final Logger log = Logger.getLogger(ActService.class);
	private Behavior currentBehavior;
	
	public ActService() {
		flipBehavior();
	}

	@Override
	public void accept(Event<Input> event) {
		Input input = event.getData();
		
		log.info("received message: " + input.getMessage());
		
		if (input.isStateChangeRequest()) {
			flipBehavior();
		} else if (input.isShutdown()) {
			setShutdown();
		} else {
			Output output = currentBehavior.act(input);
			log.info("output from behavior: " + output.getMessage());
		}
	}
	
	private void flipBehavior() {
		log.info("flipping behavior");
		
		if (currentBehavior == null || currentBehavior.getName().equalsIgnoreCase("String Reversal")) {
			currentBehavior = new StringUpperCaseBehavior();
		} else {
			currentBehavior = new StringReversalBehavior();
		}
		
		log.info("new behavior: " + currentBehavior.getName());
	}
	
	private void setShutdown() {
		currentBehavior = new ShutdownBehavior();
	}
}

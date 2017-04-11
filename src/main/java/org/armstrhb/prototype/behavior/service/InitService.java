package org.armstrhb.prototype.behavior.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import static reactor.bus.selector.Selectors.$;

import reactor.Environment;
import reactor.bus.EventBus;

@Service
@Configuration
@EnableAutoConfiguration
@ComponentScan("org.armstrhb.prototype")
public class InitService {
	@Autowired
	private EventBus eventBus;
	
	@Autowired
	private ActService actService;
	
	@Autowired
	private FeedService feedService;
	
	public void initialize() {
		eventBus.on($("sendMessage"), actService);
		feedService.kickOff();
	}
}

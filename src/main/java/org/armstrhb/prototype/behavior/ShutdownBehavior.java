package org.armstrhb.prototype.behavior;

import org.armstrhb.prototype.behavior.app.BehaviorProtoApplication;
import org.armstrhb.prototype.behavior.model.input.Input;
import org.armstrhb.prototype.behavior.model.output.Output;
import org.springframework.boot.SpringApplication;

public class ShutdownBehavior implements Behavior {

	@Override
	public String getName() {
		return "Shutdown Behavior";
	}

	@Override
	public Output act(Input input) {
		SpringApplication.exit(BehaviorProtoApplication.context);
		return new Output("shutting down");
	}

}

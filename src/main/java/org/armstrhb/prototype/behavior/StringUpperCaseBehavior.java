package org.armstrhb.prototype.behavior;

import org.armstrhb.prototype.behavior.model.input.Input;
import org.armstrhb.prototype.behavior.model.output.Output;

public class StringUpperCaseBehavior implements Behavior {

	@Override
	public Output act(Input input) {
		return new Output(input.getMessage().toUpperCase());
	}

	@Override
	public String getName() {
		return "String UpperCaser";
	}

}

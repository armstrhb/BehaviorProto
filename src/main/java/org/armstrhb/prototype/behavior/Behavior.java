package org.armstrhb.prototype.behavior;

import org.armstrhb.prototype.behavior.model.input.Input;
import org.armstrhb.prototype.behavior.model.output.Output;

public interface Behavior {
	public String getName();
	public Output act(Input input);
}

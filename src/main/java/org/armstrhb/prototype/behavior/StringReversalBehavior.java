package org.armstrhb.prototype.behavior;

import org.armstrhb.prototype.behavior.model.input.Input;
import org.armstrhb.prototype.behavior.model.output.Output;

public class StringReversalBehavior implements Behavior {

	@Override
	public Output act(Input input) {
		return new Output(reverse(input.getMessage()));
	}
	
	private String reverse(String string) {
		return new StringBuilder(string).reverse().toString();
	}

	@Override
	public String getName() {
		return "String Reversal";
	}

}

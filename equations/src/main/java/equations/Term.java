package equations;

import java.util.Set;

public class Term {
	
	float value;
	public float getValue() {
		return value;
	}
	
	public boolean hasName(String name) {
		return false;
	}

	public boolean hasName(Set<String> nameSet) {
		return false;
	}

}

package equationSystem;

public enum Side {

	LEFT,
	RIGHT;

	public Side next() {
		if (this == LEFT){
			return Side.RIGHT;
		}
		return Side.LEFT;
	}
}

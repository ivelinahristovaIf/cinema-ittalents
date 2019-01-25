package cinema;

import java.util.HashSet;
import java.util.Set;

import users.Consumer;

public class Cinema {
	public static Set<Consumer> consumers;

	public Cinema() {
		consumers = new HashSet<Consumer>();
	}
}

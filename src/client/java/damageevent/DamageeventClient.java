package damageevent;

import net.fabricmc.api.ClientModInitializer;

public class DamageeventClient implements ClientModInitializer {
	public static DamageeventClient instance;
	private EventServer server;

	@Override
	public void onInitializeClient() {
		DamageeventClient.instance = this;

		this.server = new EventServer(8220);
		this.server.start();
	}

	public void dispatch() {
		this.server.broadcast("hit");
	}
}
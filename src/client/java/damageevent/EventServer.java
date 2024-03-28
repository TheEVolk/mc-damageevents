package damageevent;

import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import net.minecraft.client.MinecraftClient;

public class EventServer extends WebSocketServer {

 public EventServer(int port) {
   super(new InetSocketAddress(port));
 }

 @Override
 public void onOpen(WebSocket conn, ClientHandshake handshake) {
  var mc = MinecraftClient.getInstance();
  mc.player.sendChatMessage("New connection to shok server", null);
 }

 @Override
 public void onClose(WebSocket conn, int code, String reason, boolean remote) {
 }

 @Override
 public void onMessage(WebSocket conn, String message) {
 }

 @Override
 public void onError(WebSocket conn, Exception ex) {
   ex.printStackTrace();
   if (conn != null) {
     // some errors like port binding failed may not be assignable to a specific websocket
   }
 }

 @Override
 public void onStart() {
   System.out.println("Server started!");
   setConnectionLostTimeout(0);
   setConnectionLostTimeout(100);
 }
}
 
package jp.co.tis.stc.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.Decoder;
import javax.websocket.DeploymentException;
import javax.websocket.Encoder;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.Extension;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class WebSocketMessengerNoAnnotate extends Endpoint implements Runnable {
	private Session session = null;
	private boolean halt = false;
	private static boolean connected = false;

	@Override
	public void onOpen(Session session, EndpointConfig eptCnf) {
		session.addMessageHandler(new MessageHandler.Whole<String>() {
			@Override
			public void onMessage(String message) {
				System.out.println(message);
			}
		});
	}
	
	public void onClose(Session session, CloseReason res){
		System.out.println("Session Closed.");
		this.connected = false;
		this.session = null;
		this.halt();
	}
	

	@Override
	public void run() {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(this.halt != true || this.connected != false) {
			try {
				if(bfr.ready()){
					this.session.getBasicRemote().sendText(bfr.readLine());
				}
				Thread.sleep(100);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void halt() {
		this.halt = true;
	}
	public void connect(String URL) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		ClientEndpointConfig config = ClientEndpointConfig.Builder.create().build();

		try {
			this.session = container.connectToServer(WebSocketMessengerNoAnnotate.class, config, URI.create(URL));
			this.connected = true;
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		WebSocketMessengerNoAnnotate wsm = new WebSocketMessengerNoAnnotate();
		wsm.connect("ws://localhost:8080/ws");
		Thread thread = new Thread(wsm);
		thread.start();
	}

	
}

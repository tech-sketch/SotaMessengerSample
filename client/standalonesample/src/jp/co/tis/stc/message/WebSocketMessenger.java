package jp.co.tis.stc.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class WebSocketMessenger extends Thread {
	private boolean halt = false;
	private String message = "";
	private static boolean connected = false;
	private WebSocketMessenger wsm = null;
	private Session session = null;
	
	public WebSocketMessenger() {}
	
	private void halt() {
		this.halt = true;
	}
	
	@OnError
	public void onError(Session session, Throwable cause) {
		System.out.println("Error:"+session.getId()+":"+cause.getMessage());
	}
	
	@OnMessage
	public void onMessage(String message, Session session){
		System.out.println(message);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason res){
		System.out.println("Session Closed.");
		this.connected = false;
		this.session = null;
		this.halt();
	}
	
	@OnOpen
	public void onOpen(Session session){
		System.out.println("Session Opened.");
	}
	
	public void run() {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		while(halt != true || connected != false) {
			try {
				if(bfr.ready()){
					this.session.getBasicRemote().sendText(bfr.readLine());
				}
				sleep(100);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void connect(String URL) {
		WebSocketContainer container = ContainerProvider.getWebSocketContainer();
		try {
			this.session = container.connectToServer(WebSocketMessenger.class, URI.create(URL));
			connected = true;
		} catch (DeploymentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		WebSocketMessenger wsm = new WebSocketMessenger();
		wsm.connect("ws://localhost:8080/ws");
		wsm.start();
	}
	
}

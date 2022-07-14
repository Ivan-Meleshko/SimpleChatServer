package com.skillfactory.training.threads.chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer {

	ArrayList<Client> clients = new ArrayList<>();
	ServerSocket serverSocket;

	public ChatServer() throws IOException {
		serverSocket = new ServerSocket(1234);
		
	}
	
	void sendAll(String msg) {
		for (Client c: clients) {
			c.receive(msg);
		}
	}

	public void run() {
		while (true) {
			// waiting for the client
			System.out.println("waiting for the client");
			Socket socket;
			try {
				socket = serverSocket.accept();
				// client connected
				System.out.println("client connected");
				clients.add(new Client(socket, this));
				
			} catch (IOException e) {
				e.printStackTrace();
			}


		}
	}

	public static void main(String[] args) throws IOException {
		
		new ChatServer().run();
		
	}

}

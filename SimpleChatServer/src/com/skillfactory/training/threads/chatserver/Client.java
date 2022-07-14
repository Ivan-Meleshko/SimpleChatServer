package com.skillfactory.training.threads.chatserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

	 Socket socket;
	 ChatServer chatServer;
	   Scanner in;
       PrintStream out;
	
	   public Client(Socket socket, ChatServer chatServer){

	       this.socket = socket;
	       this.chatServer = chatServer;
			new Thread(this).start();
	   }

	   public void run() {
	       try {
	           // �������� ������ ����� � ������
	           InputStream is = socket.getInputStream();
	           OutputStream os = socket.getOutputStream();
	           // ������� ������� �������� ����� � ������
	          in = new Scanner(is);
	          out = new PrintStream(os);
	           // ������ �� ���� � ����� � ����
	           out.println("Welcome to chat!");
	           String input = in.nextLine();
	           while (!input.equals("bye")) {
	               chatServer.sendAll(input);
	               input = in.nextLine();
	           }
	           socket.close();
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	   }
	   
	   void receive(String msg) {
		   out.println(msg);
	   }
	
}

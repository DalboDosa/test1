package ch12;

import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer
{
  Vector clientVector= new Vector();
  int clientNum= 0;

  public void broadcast(String msg) throws IOException
  {
    synchronized(clientVector){
      for(int i=0; i<clientVector.size(); i++){
        ChatThread client= (ChatThread) clientVector.elementAt(i);
        synchronized(client){
          client.sendMessage(msg);
        }
      }
    }
  }

  public void removeClient(ChatThread client)
  {
    synchronized(clientVector){
      clientVector.removeElement(client);
      client= null;
      System.gc();
    }
  }

  public void addClient(ChatThread client)
  {
    synchronized(clientVector){
      clientVector.addElement(client);
    }
  }
// chat 서버는 하나 // 사용자 마다 chatthread 생성해서 쓴다. 멀티 스레드 서버!!!!
  public static void main(String[] args)
  {
    ServerSocket myServerSocket= null;

    ChatServer myServer= new ChatServer();

    try{
      myServerSocket= new ServerSocket(2587);
    }catch(IOException e){
      System.out.println(e.toString());
      System.exit(-1);
    }
    
    System.out.println("[서버 대기 상태] "+ myServerSocket);

    try{
      while(true){
        ChatThread client= new ChatThread(myServer, myServerSocket.accept());
        //서버 소켓의 accpet()는 채팅 클라이언트가 접속할 때까지 기다리는 명령 ,, 기다렸다가 접속하면 클라이언트 소케켓을 돌려준다...
        // 이 소켓을 chatThread클래스의 생성자에게 인수로 넘겨준다
        // 채팅 클라이언트가 접속하면 위 명령이 실행되고 chatThread 객체가 계속 생성되서 여러개의 chattHREAD가 생성됨 
        // 전체 클라이언트에게 메세지 방송하는 경우 CHATtREAD 객체를 백터에 저장해서 둔다....
        client.start();
        myServer.addClient(client);

        myServer.clientNum++;
        System.out.println("[현재 접속자수] "+ myServer.clientNum +"명");
      }
    }catch(IOException e){
      System.out.println(e.toString());
    }
  }
}
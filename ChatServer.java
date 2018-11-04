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
// chat ������ �ϳ� // ����� ���� chatthread �����ؼ� ����. ��Ƽ ������ ����!!!!
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
    
    System.out.println("[���� ��� ����] "+ myServerSocket);

    try{
      while(true){
        ChatThread client= new ChatThread(myServer, myServerSocket.accept());
        //���� ������ accpet()�� ä�� Ŭ���̾�Ʈ�� ������ ������ ��ٸ��� ��� ,, ��ٷȴٰ� �����ϸ� Ŭ���̾�Ʈ �������� �����ش�...
        // �� ������ chatThreadŬ������ �����ڿ��� �μ��� �Ѱ��ش�
        // ä�� Ŭ���̾�Ʈ�� �����ϸ� �� ����� ����ǰ� chatThread ��ü�� ��� �����Ǽ� �������� chattHREAD�� ������ 
        // ��ü Ŭ���̾�Ʈ���� �޼��� ����ϴ� ��� CHATtREAD ��ü�� ���Ϳ� �����ؼ� �д�....
        client.start();
        myServer.addClient(client);

        myServer.clientNum++;
        System.out.println("[���� �����ڼ�] "+ myServer.clientNum +"��");
      }
    }catch(IOException e){
      System.out.println(e.toString());
    }
  }
}
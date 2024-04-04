/*
Socket Programming

Excs 2 Write a HTTP web client program to download a web page using
TCP sockets.
• Algorithm
Steps:
1. Create a URL object and pass url as string to download the webpage.
URL example = new URL(pass url of webpage you want to download)
2. Create Buffered Reader object and pass openStream(). Method of URL in Input Stream object.
3. Create a string object to read each line one by one from stream.
4. Write each line in html file where webpage will be downloaded.
5. Close all objects.
6. Catch exceptions if url failed to download.

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;

public class Web2 {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://www.google.com");
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        BufferedWriter writer = new BufferedWriter(new FileWriter("data.html"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }
}
*/


/*
Echo:- 

Algorithm
CLIENT
1. Start the program.
2. Create a socket which binds the Ip address of server and the port
address to acquire service.
3. After establishing connection send a data to server.
4. Receive and print the same data from server.
5. Close the socket.
6. End the program.

SERVER
1. Start the program.
2. Create a server socket to activate the port address.
3. Create a socket for the server socket which accepts the connection.
4. After establishing connection receive the data from client.
5. Print and send the same data to client.
6. Close the socket.
7. End the program.

Echo Server:-
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        String op1 = "";
        Socket s1;
        ServerSocket serv;
        BufferedReader r2;
        PrintStream p1;

        try {
            serv = new ServerSocket(8080);
            s1 = serv.accept();
            r2 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            p1 = new PrintStream(s1.getOutputStream());

            while (!op1.equals("end")) {
                op1 = r2.readLine();
                System.out.println("Message Received: " + op1);
                p1.println("Message Sent Back: " + op1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

Echo Client:-
import java.net.*;
import java.io.*;

public class EchoClient {
    public static void main(String[] args) {
        Socket s1;
        BufferedReader r1, r2;
        PrintStream p1;
        String op = "";

        try {
            s1 = new Socket(InetAddress.getLocalHost(), 8080);
            r1 = new BufferedReader(new InputStreamReader(System.in));
            r2 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            p1 = new PrintStream(s1.getOutputStream());

            while (!op.equals("end")) {
                System.out.print("\nClient : ");
                op = r1.readLine();
                p1.println(op);
                System.out.print("Server: " + r2.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/


/* 
Algorithm
CLIENT
1. Start the program
2. Include necessary package in java
3. To create a socket in client to server.
4. The client establishes a connection to the server.
5. The client accept the connection and to send the data from client to server.
6. The client communicates the server to send the end of the message
7. Stop the program.

SERVER
1. Start the program
2. Include necessary package in java
3. To create a socket in server to client
4. The server establishes a connection to the client.
5. The server accept the connection and to send the data from server to client and vice versa
7. The server communicate the client to send the end of the message.
8. Stop the program.

CHAT SERVER:-
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        String op1 = "";
        Socket s1;
        ServerSocket serv;
        BufferedReader r2, r1;
        PrintStream p1;

        try {
            serv = new ServerSocket(8080);
            s1 = serv.accept();
            r1 = new BufferedReader(new InputStreamReader(System.in));
            r2 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            p1 = new PrintStream(s1.getOutputStream());

            while (!op1.equals("end")) {
                System.out.println("Client: " + r2.readLine());
                System.out.print("Server: ");
                op1 = r1.readLine();
                p1.println(op1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

CHAT CLIENT:-
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        Socket s1;
        BufferedReader r1, r2;
        PrintStream p1;
        String op = "";

        try {
            s1 = new Socket(InetAddress.getLocalHost(), 8080);
            r1 = new BufferedReader(new InputStreamReader(System.in));
            r2 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            p1 = new PrintStream(s1.getOutputStream());

            while (!op.equals("end")) {
                System.out.print("Client: ");
                op = r1.readLine();
                p1.println(op);
                System.out.println("Server: " + r2.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/

/*
Algorithm

CLIENT SIDE
1. Start.
2. Establish a connection between the Client and Server.
3. Socket ss=new Socket(InetAddress.getLocalHost(),1100);
4. Implement a client that can send two requests.
i) To get a file from the server.
ii) To put or send a file to the server.
5. After getting approval from the server ,the client either get file from the server or send
6. file to the server.

SERVER SIDE
1. Start.
2. Implement a server socket that listens to a particular port number.
3. Server reads the filename and sends the data stored in the file for
the‘get’ request.
4. It reads the data from the input stream and writes it to a file in
theserver for the ‘put’ instruction.
5. Exit upon client’s request.
6. Stop.

File Transfer Server:-

import java.io.*;
import java.net.*;

public class FileTransferServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server listening...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            InputStream inputStream = clientSocket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("received_file.txt");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("File received successfully!");

            fileOutputStream.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

File Transfer Client:-

import java.io.*;
import java.net.*;

public class FileTransferClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345); // Change to appropriate server address
            System.out.println("Connected to server");

            OutputStream outputStream = socket.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream("test_file.txt");

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent successfully!");

            fileInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/


/*

DNS server and client:-

### DNS Server Algorithm:
1. Initialize a DatagramSocket on port 53.
2. Receive a DNS request packet from the client.
3. Extract the domain name from the request packet.
4. Resolve the IP address associated with the domain name.
5. Construct a DNS response packet containing the resolved IP address.
6. Send the response packet back to the client.

### DNS Client Algorithm:
1. Initialize a DatagramSocket.
2. Prompt the user to enter a domain name.
3. Construct a DNS request packet with the entered domain name.
4. Send the request packet to the DNS server.
5. Receive the response packet from the server.
6. Extract the IP address from the response packet.
7. Display the IP address to the user.


DNS SERVER:-
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class UdpDnsServer {
@SuppressWarnings("deprecation")
    public static void main(String[] args) {
        try {
            String command = "nslookup ";
            String send1 = "";
            String op = "";
            while (true) {
                DatagramSocket s = new DatagramSocket(8210);
                byte[] send = new byte[1024];
                byte[] recv = new byte[1024];
                DatagramPacket rec = new DatagramPacket(recv, recv.length, InetAddress.getLocalHost(), 8210);
                s.receive(rec);
                String s12 = new String(rec.getData());
                command += s12;
                System.out.println(command);
                InetAddress a = rec.getAddress();
                int port = rec.getPort();
                Process p = Runtime.getRuntime().exec(command.trim());
                Scanner r = new Scanner(p.getInputStream());
                // DatagramPacket send=new DatagramPacket(recv, port, a, port)
                while (r.hasNext()) {
                    op += r.next();
                    op += "\n";
                }
                System.out.println(op);
                send1 += op;
                byte[] sendArray = send1.getBytes();
                DatagramPacket sendd = new DatagramPacket(sendArray, sendArray.length, InetAddress.getLocalHost(), rec.getPort());
                s.send(sendd);
                s.close();
            }
            /*
            • command+="www.google.com";
            // try for different websites &
            Process p=Runtime getRuntime() -exec(command);
            show individual o/p
            Scanner renew Scanner (p-getInputStream));
            while(r.hasNext)) {
            op+=r.next);
            op+="\n";
            System.out.println(op);
            * /
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

DNS Client:- 
import java.net.*;
import java.io.*;

public class UdpDnsClient {
    public static void main(String args[]) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            DatagramSocket s = new DatagramSocket();
            byte[] send = new byte[1024];
            byte[] recv = new byte[1024];
            System.out.println("Enter host Name:");
            String input = r.readLine();
            send = input.getBytes();
            DatagramPacket p = new DatagramPacket(send, send.length, InetAddress.getLocalHost(), 8210);
            DatagramPacket q = new DatagramPacket(recv, recv.length, InetAddress.getLocalHost(), 8210);
            s.send(p);
            s.receive(q);
            String ip = new String(q.getData());
            System.out.println("IP Address: " + ip);
        } catch (Exception e) {
          
            e.printStackTrace();
        }
    }
}
*/
/*
ARP

Algorithm – Client ARP
1. Start the program
2. Using socket connection is established between client and server.
3. Get the IP address to be converted into MAC address. 
4. Send this IP address to server.
5. Server returns the MAC address to client

Algorithm – Server ARP
1.Start the program
2. Accept the socket which is created by the client.
3. Server maintains the table in which IP and corresponding MAC addresses are stored.
4. Read the IP address which is send by the client.
5. Map the IP address with its MAC address and return the MAC address to client.

Arp Server:-
import java.net.*;
import java.io.IOException;
import java.util.*;
import java.io.*;

public class ArpServer {
    @SuppressWarnings("deprecation")
    public static void main(String args[]) {
        String ip = "";
        String command = "arp -a ";
        String out = "";
        try {
            while (true) {
                DatagramSocket s = new DatagramSocket(7080);
                byte[] send = new byte[1024];
                byte[] recv = new byte[1024];
                DatagramPacket p = new DatagramPacket(recv, recv.length, InetAddress.getLocalHost(), 7080);
                s.receive(p);
                ip = new String(p.getData());
                String c_out = command + ip;
                Process pl = Runtime.getRuntime().exec(c_out.trim());
                System.out.println(c_out);
                Scanner r = new Scanner(pl.getInputStream());
                while (r.hasNext()) {
                    out += r.next();
                    out += "\n";
                }
                // System.out.println(out.substring(out.index0f(ip)));
                send = out.getBytes();
                DatagramPacket send1 = new DatagramPacket(send, send.length, InetAddress.getLocalHost(),
                        p.getPort());
                s.send(send1);
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

ARP Client:-
import java.net.*;
import java.util.*;
import java.net.SocketException;
import java.io.*;


public class ArpClient {
    public static void main(String[] args) {
        Scanner r = new Scanner(System.in);
        try {
            DatagramSocket s1 = new DatagramSocket();
            byte[] send = new byte[1024];
            byte[] recv = new byte[1024];
            System.out.println("Enter IP Address:");
            String ip = r.next();
            send = ip.getBytes();
            DatagramPacket send1 = new DatagramPacket(send, send.length, InetAddress.getLocalHost(), 7080);
            DatagramPacket recv1 = new DatagramPacket(recv, recv.length, InetAddress.getLocalHost(), 7080);
            s1.send(send1);
            s1.receive(recv1);
            String s = new String(recv1.getData());
            System.out.println(s);
            s1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

*/

/* 
RARP:-

Algorithm Client RARP
1.Start the program
2. using datagram sockets UDP function is established.
 3.Get the MAC address to be converted into IP address. 
 4.Send this MAC address to server.
5.Server returns the IP address to client

Algorithm Server RARP
1. Start the program.
2. Server maintains the table in which IP and corresponding MAC addresses are stored.
3. Read the MAC address which is send by the client.
4. Map the IP address with its MAC address and return the IP address to client.

RARP SERVER:-
import java.net.*;

public class RARPServer {
    public static void main(String[] args) {
        String[] ip = {"165.165.80.80", "165.165.79.1"};
        String[] mac = {"6A:08:AA:C2", "8A:BC:E3:FA"};
        String send2 = "";
        int flag = 0;
        try {
            while (true) {
                DatagramSocket socket = new DatagramSocket(1024);
                byte[] send = new byte[1024];
                byte[] recv = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(recv, recv.length, InetAddress.getLocalHost(), 1024);
                socket.receive(receivePacket);
                String uin = new String(receivePacket.getData());
                System.out.println(uin);
                for (int i = 0; i < ip.length; i++) {
                    if (mac[i].equalsIgnoreCase(uin.trim())) {
                        send2 = ip[i];
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    send2 = "Not Found";
                }
                send = send2.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(send, send.length, InetAddress.getLocalHost(), receivePacket.getPort());
                System.out.println(send2);
                socket.send(sendPacket);
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


RARP CLIENT:-
import java.io.*;
import java.util.*;
import java.net.*;

public class RARPClient {
    public static void main(String[] args) {
        System.out.println("Enter MAC address: ");
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.next();
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] send = new byte[1024];
            byte[] recv = new byte[1024];
            send = ip.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(send, send.length, InetAddress.getLocalHost(), 1024);
            socket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(recv, recv.length, InetAddress.getLocalHost(), 1024);
            socket.receive(receivePacket);
            String out = new String(receivePacket.getData());
            System.out.println(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/


/* 
CRC:-

Algorithm:
1. Append N zeros to the data unit, where N is less than the number of bits in the data unit.
2. Use binary division to divide the extended data by a divisor, resulting in a CRC remainder.
3. Replace the previously appended zeros with the CRC remainder.
4. Send the modified data unit to the receiver.
5. Upon receiving the data unit with the CRC remainder, the receiver divides it by the divisor.
6. If the remainder is zero, the data unit is considered intact and accepted.
7. If the remainder is non-zero, the data unit is considered corrupted and discarded.

public class CRC {
    
    public String xor(String s1, String s2) {
        String res = "";
        for (int i = 0; i < s2.length(); i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                res += '0';
            } else {
                res += '1';
            }
        }
        return res;
    }
    
    public String divide(String s1, String s2) {
        int d2 = s2.length();
        for (int i = 0; i < d2 - 1; i++) {
            s1 += '0';
        }
        return s1;
    }
    
    public String divideRemainder(String s1, String s2) { 
        int d1 = s1.length();
        String tmp = "";
        int track = 0;
        String tmp2 = "";
        tmp = xor(s1.substring(0, s2.length()), s2);
        track += tmp.length();
        while (track < d1) {
            if (tmp.charAt(0) == '0') {
                tmp = tmp.substring(1);
                tmp += String.valueOf(s1.charAt(track++));
            }
            tmp2 = tmp;
            tmp = "";
            tmp = xor(tmp2, s2);
        }
        return tmp2;
    }
    
    public boolean isTransmittedProperly(String originalBits, String CRCRemainder, String divisor) {
        String s2 = originalBits + CRCRemainder.substring(1);
        return new CRC().divideRemainder(s2, divisor).equals("0000");
    }
    
    public static void main(String[] args) {
        String s1 = "10111011";
        System.out.println("No Error while transmission: ");
        System.out.println("Original Bit Arrangement: " + s1);
        String chk = s1;
        String s2 = "1001";
        System.out.println("CRC Polynomial: " + s2);
        chk += s2.substring(1);
        System.out.print("CRC Match :-> " + new CRC().isTransmittedProperly("10111011", "0110", "1001"));
        System.out.println("\n\n Error introduced into Bits:\n Erroneous Bit Arrangement: 10111111\n CRC Polynomial: 1001\n");
        System.out.println("CRC Match :-> " + new CRC().isTransmittedProperly("10111111", "0110", "1001"));
    }
}
*/

import java.io.*;
import java.util.*;

public class dvr1{
    static int graph[][];
    static int via[][];
    static int rt[][];
    static int v;
    static int e;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter the number of Vertices: ");
        v = Integer.parseInt(br.readLine());
        System.out.println("Please enter the number of Edges: ");
        e = Integer.parseInt(br.readLine());
        graph = new int[v][v];
        via = new int[v][v];
        rt = new int[v][v];
        for (int i = 0; i < v; i++)
            for (int j = 0; j < v; j++) {
                if (i == j)
                    graph[i][j] = 0;
                else
                    graph[i][j] = 9999;
            }
        for (int i = 0; i < e; i++) {
            System.out.println("Please enter data for Edge " + (i + 1) + ":");
            System.out.print("Source: ");
            int s = Integer.parseInt(br.readLine());
            s--;
            System.out.print("Destination: ");
            int d = Integer.parseInt(br.readLine());
            d--;
            System.out.print("Cost: ");
            int c = Integer.parseInt(br.readLine());
            graph[s][d] = c;
            graph[d][s] = c;
        }
        dvr_calc_disp("The initial Routing Tables are: ");
        System.out.print("Please enter the Source Node for the edge whose cost has changed: ");
        int s = Integer.parseInt(br.readLine());
        s--;
        System.out.print("Please enter the Destination Node for the edge whose cost has changed: ");
        int d = Integer.parseInt(br.readLine());
        d--;
        System.out.print("Please enter the new cost: ");
        int c = Integer.parseInt(br.readLine());
        graph[s][d] = c;
        graph[d][s] = c;
        dvr_calc_disp("The new Routing Tables are: ");
    }

    static void dvr_calc_disp(String message) {
        System.out.println();
        init_tables();
        update_tables();
        System.out.println(message);
        print_tables();
        System.out.println();
    }

    static void update_table(int source) {
        for (int i = 0; i < v; i++) {
            if (graph[source][i] != 9999) {
                int dist = graph[source][i];
                for (int j = 0; j < v; j++) {
                    int inter_dist = rt[i][j];
                    if (via[i][j] == source)
                        inter_dist = 9999;
                    if (dist + inter_dist < rt[source][j]) {
                        rt[source][j] = dist + inter_dist;
                        via[source][j] = i;
                    }
                }
            }
        }
    }

    static void update_tables() {
        int k = 0;
        for (int i = 0; i < 4 * v; i++) {
            update_table(k);
            k++;
            if (k == v)
                k = 0;
        }
    }

    static void init_tables() {
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                if (i == j) {
                    rt[i][j] = 0;
                    via[i][j] = i;
                } else {
                    rt[i][j] = 9999;
                    via[i][j] = 100;
                }
            }
        }
    }

    static void print_tables() {
        System.out.println("Routing Table:");
        System.out.print("   ");
        for (int i = 0; i < v; i++) {
            System.out.printf("%3d ", i + 1);
        }
        System.out.println();
        for (int i = 0; i < v; i++) {
            System.out.printf("%3d", i + 1);
            for (int j = 0; j < v; j++) {
                if (rt[i][j] != 9999) {
                    System.out.printf("%3d ", rt[i][j]);
                } else {
                    System.out.print("INF ");
                }
            }
            System.out.println();
        }
    }
}



/* 
Link State Routing:-

Algorithm:-
1. Initialize distances array and predecessors array.
2• Set the distance of the start node to 0 and add it to the priority
queue.
3• While the priority queue is not empty:
    a. Extract the node with the minimum distance from the priority
queue.
    b. Relax all neighboring nodes of the extracted node.
4. Update distances and predecessors accordingly.
5• Return the distances and predecessors arrays.
6• Print the shortest path from start to target using predecessors.


Code:-
import java.util.*;
class Dijkstra {
    int mat[][];
    int nodes, nodeNo = 0, n_nodes;
    Dijkstra(int n_nodes, int[][] mat) {
        this.mat = mat;
        this.n_nodes = n_nodes;
    }

    ArrayList<Integer> sl = new ArrayList<Integer>();
    int k = 0;

    void set() {
        sl.add(k++, nodeNo++);
        sl.add(k++, 0);
        sl.add(k++, 0);
        sl.add(k++, null);
        for (int i = 1; i < n_nodes; i++) {
            sl.add(k++, nodeNo++);
            sl.add(k++, 0);
            sl.add(k++, Integer.MAX_VALUE);
            sl.add(k++, null);
        }
    }

    void print() {
        for (int i = 0; i < sl.size(); i += 4) {
            System.out.println(sl.subList(i, i + 4));
        }
    }

    int findmin(ArrayList<Integer> k) {
        int min = k.subList(4, 8).get(2), idx = k.subList(4, 8).get(0);
        for (int i = 4; i < mat.length * 4; i += 4) {
            if (k.subList(i, i + 4).get(2) <= min && (k.subList(i, i + 4).get(1)) == 0) {
                min = k.subList(i, i + 4).get(2);
                idx = k.subList(i, i + 4).get(0);
            }
        }
        return idx;
    }

    void run() {
        int cur;
        int iter = 0;
        int k = 0, m, i, cost = 0;
        for (int j = 0; j < mat.length; j++) {
            i = j * 4;
            if (mat[k][j] > 0) {
                if (sl.subList(i, i + 4).get(2) == Integer.MAX_VALUE) {
                    sl.subList(i, i + 4).set(2, mat[k][j]);
                    sl.subList(i, i + 4).set(3, 0);
                }
            }
        }
        sl.subList(0, 4).set(1, 1);
        int listidx, precost, pre, collst, tmp;
        while (!isVisited()) {
            i = findmin(sl);
            listidx = i * 4;
            precost = sl.subList(listidx, listidx + 4).get(3);
            cost = sl.subList(precost * 4, (precost * 4) + 4).get(2);
            for (int c = 0; c < mat.length; c++) {
                if (mat[i][c] > 0) {
                    collst = c * 4;
                    if (cost + mat[i][c] < sl.subList(collst, collst + 4).get(2)) {
                        tmp = sl.subList(collst, collst + 4).get(2);
                        sl.subList(collst, collst + 4).set(2, cost + mat[i][c]);
                        sl.subList(collst, collst + 4).set(3, i);
                    }
                }
            }
            sl.subList(listidx, listidx + 4).set(1, 1);
        }
    }

    boolean isVisited() {
        for (int i = 0; i < mat.length; i += 4) {
            if (sl.subList(i, i + 4).get(1) == 0) {
                return (false);
            }
        }
        return true;
    }

    void printRoute(int targetNode) {
        int listidx = targetNode;
        listidx *= 4;
        int cost = 0;
        System.out.print("0");
        while (sl.subList(listidx, listidx + 4).get(0) != 0) {
            cost += sl.subList(listidx, listidx + 4).get(2);
            System.out.print("-->" + sl.subList(listidx, listidx + 4).get(0));
            listidx = sl.subList(listidx, listidx + 4).get(3); // Correction made here
            listidx *= 4;
        }
        System.out.print("=" + cost);
        cost = 0;
    }

    public static void main(String[] args) {
        int[][] adjmat = {
            { 0, 0, 1, 2, 0, 0, 0 },
            { 0, 0, 2, 0, 0, 3, 0 },
            { 1, 2, 0, 1, 3, 0, 0 },
            { 2, 0, 1, 0, 0, 0, 1 },
            { 0, 0, 3, 0, 0, 2, 0 },
            { 0, 3, 0, 0, 2, 0, 1 },
            { 0, 0, 0, 1, 0, 1, 0 }
        };
        Dijkstra s = new Dijkstra(7, adjmat);
        s.set();
        s.run();
        s.print();
        for (int i = 1; i < 7; i++) {
            System.out.println();
            s.printRoute(i);
        }
    }
}
*/
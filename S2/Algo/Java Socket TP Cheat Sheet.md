**For Exams & Practical Work (TP)**  

This cheat sheet provides **ready-to-use templates** for all common socket communication patterns (TCP/UDP, full/half duplex) and a **step-by-step methodology** to solve any network programming exercise.

---

# 1. Quick-Use Templates  
### A. UDP Communication
#### **(1) Full Duplex UDP (Bi-directional)**
```java
DatagramSocket socket = new DatagramSocket(MY_PORT);  // Both sides need a socket
```
```java
// --- Receiving ---
byte[] receiveData = new byte[1024];
DatagramPacket q = null;

while (ture) {
	q = new DatagramPacket(
		receiveData, 
		receiveData.length
		);
	socket.receive(q);  // Blocks until data arrives
	String received = new String(q.getData(), 0, q.getLength());
	System.out.println("Receive: " + received);
}
```
```java
// --- Sending ---
String message = "Hello P2!";
byte[] sendData = message.getBytes();
DatagramPacket sendPacket = null;

while (true) {
	sendPacket = new DatagramPacket(
	    sendData, 
	    sendData.length, 
	    InetAddress.getByName("P2_IP"), 
	    P2_PORT
	);
	socket.send(sendPacket);
}
```

#### **(2) Half Duplex (Send Only)**
```java
// --- UDP Send ---
DatagramSocket socket = new DatagramSocket();  // (ephemeral)
DatagramPacket packet = null; // in case of while loop

String msg = "Hello!";
byte[] data = msg.getBytes();
DatagramPacket packet = new DatagramPacket(
    data,                              // data.getBytes()
    data.length, 
    InetAddress.getByName("DEST_IP"), 
    DEST_PORT
);
socket.send(packet);
```

#### **(3) Half Duplex (Receive Only)**
```java
DatagramSocket socket = new DatagramSocket(MY_PORT);
byte[] buffer = new byte[1024];
DatagramPacket q = new DatagramPacket(buffer, buffer.length);

while (true) {
	socket.receive(q);  // Waits for data
	String received = new String(q.getData(), 0, q.getLength());
	System.out.println("Receive: "+str);
}
```

---

### B. TCP Communication
#### **(1) Full Duplex TCP (Bi-directional)**
```java
// --- Server Side ---
ServerSocket serverSocket = new ServerSocket(MY_PORT);
Socket c = serverSocket.accept();  // Waits for client
ObjectOutputStream out = new ObjectOutputStream(c.getOutputStream());
ObjectInputStream in = new ObjectInputStream(c.getInputStream());
```
```java
// --- Client Side ---
Socket s = new Socket("SERVER_IP", SERVER_PORT);
ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
ObjectInputStream in = new ObjectInputStream(s.getInputStream());
```
```java
// --- Sending/Receiving (Both sides) ---
out.writeObject("Hello!");  // Send
String response = (String) in.readObject();  // Receive
System.out.println("Receive :" + response);	
```

#### **(2) Half Duplex (Send Only)**
```java
Socket socket = new Socket("DEST_IP", DEST_PORT);
ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

while (true) {
	out.writeObject("Data sent!");
}
```

#### **(3) Half Duplex (Receive Only)**
```java
ServerSocket serverSocket = new ServerSocket(MY_PORT);
Socket con = serverSocket.accept();
ObjectInputStream in = new ObjectInputStream(con.getInputStream());

while (true) {
	String received = (String) in.readObject();
	System.out.println("Receive :"+received);		
}
```

---


# 2. Java: Essential Cheat Sheet

## Data Type Conversions

### String ↔ Integer
```java
// String to int
String str = "123";
int num = Integer.parseInt(str.trim()); // Always trim() for UDP!

// int to String
int num = 456;
String str = Integer.toString(num);
```

### String ↔ Bytes (for UDP)
```java
// String to bytes
String message = "Hello";
byte[] bytes = message.getBytes(); // Default charset
byte[] bytes = message.getBytes(StandardCharsets.UTF_8); // Explicit charset

// Bytes to String
byte[] receivedBytes = ...;
String message = new String(receivedBytes).trim(); // Always trim UDP!
String message = new String(receivedBytes, StandardCharsets.UTF_8).trim();
```

### Object Serialization (for TCP)
```java
// Writing objects
ObjectOutputStream out = ...;
out.writeObject("String"); // Strings
out.writeObject(Integer.valueOf(42)); // Integers
out.flush(); // Important!

// Reading objects
ObjectInputStream in = ...;
String s = (String)in.readObject();
Integer i = (Integer)in.readObject();
```

## Package Setup
```java
package yourpackage; // Must match directory structure

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets; // For String to byte[]

public class P {
    public static void main(String[] args) {
		// Declare Sockets here
        try {
		    // Code here
        } catch (Exception e) {
            System.out.println("P3: " + e.toString());
        } finally {
            try {
                if (socket != null) socket.close();
                scanner.close();  // Close the scanner
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.toString());
            }
        }
}
```

## Fetching Connection Details

### TCP Connections
```java
// Server side (after accept())
Socket clientSocket = serverSocket.accept();

// Get client details
InetAddress clientAddress = clientSocket.getInetAddress();
int clientPort = clientSocket.getPort(); // Remote port
int localPort = clientSocket.getLocalPort(); // Server port

// Client side
Socket socket = new Socket("localhost", 8080);
InetAddress serverAddress = socket.getInetAddress();
int serverPort = socket.getPort();
```

### UDP Packets
```java
// Receiving packet
DatagramPacket packet = ...;
InetAddress senderAddress = packet.getAddress();
int senderPort = packet.getPort();

// Example reply pattern
String response = "Reply message";
byte[] responseData = response.getBytes();
DatagramPacket replyPacket = new DatagramPacket(
    responseData,
    responseData.length,
    senderAddress, // Using address from received packet
    senderPort     // Using port from received packet
);
socket.send(replyPacket);
```

## IP Address Resolution

### By Hostname
```java
InetAddress address = InetAddress.getByName("localhost");
InetAddress address = InetAddress.getByName("127.0.0.1");
```

### Local Address Info
```java
InetAddress localhost = InetAddress.getLocalHost();
System.out.println("Local IP: " + localhost.getHostAddress());
System.out.println("Hostname: " + localhost.getHostName());
```
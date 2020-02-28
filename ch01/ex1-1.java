// 예제 1.1 블로킹 입출력 예제

ServerSocket serverSocket = new ServerSocket(portNumber);
Socket clientSocket = serverSocket.accept();
BufferedReader in = new BufferedReader(
    new InputStreamReader(clientSocket.getInputStream()));
PrintWriter out =
    new PrintWriter(clientSocket.getOutputStream(), true);
String request, response;
while ((request = in.readLine()) != null) {
    if ("Done".equals(request)) {
	break;
    }
    response = processRequest(request);
    out.println(response);
}

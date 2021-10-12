package it.unitn.disi.webarch.sebastianochiari;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class TinyHTTPDConnection extends Thread {

    Socket socket;

    public TinyHTTPDConnection(Socket s) {
        socket = s;
        setPriority(NORM_PRIORITY - 1);
        start();
    }

    @Override
    public void run() {
        System.out.println("=========");
        System.out.println("Connected on port " + socket.getPort());
        OutputStream outputStream = null;
        try {
            // open sockets for reading and writing
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            // read request
            String request = bufferedReader.readLine();
            if (request == null) {
                return;
            }
            System.out.println("Request: " + request);
            // read request headers
            String header = null;
            do {
                header = bufferedReader.readLine();
                System.out.println("Header: " + header);
            } while(header != null && header.length() > 0);
            // parse request
            StringTokenizer parser = new StringTokenizer(request);
            if((parser.countTokens() >= 2) && parser.nextToken().equals("GET")) {
                // take the "/" away from the string
                if((request = parser.nextToken()).startsWith("/")) {
                    request = request.substring(1);
                }
                // in case the request points to a directory or is empty, we append a standard token
                if(request.endsWith("/") || request.equals("")) {
                    request = request + "index.html";
                }
                System.out.println("REQUEST: " + request);
                // check for the process token
                StringTokenizer tmp = new StringTokenizer(request, "/");
                String token = tmp.nextToken();
                if(token.equals("process")) {
                    // check if there are more elements
                    if(tmp.hasMoreElements()) {
                        String instruction = tmp.nextToken();
                        StringTokenizer tmp2 = new StringTokenizer(instruction, "?");
                        token = tmp2.nextToken();
                        // check for the reverse token
                        if(token.equals("reverse")) {
                            // check if there are more elements
                            if(tmp2.hasMoreElements()) {
                                // split string according to the = character to retrieve the parameter
                                String parameter = ((tmp2.nextToken()).split("="))[1];
                                String reverse = launchExternalProcess(parameter);
                                // send the response headers
                                printStream.print("HTTP/1.1 200 OK\r\n");
                                printStream.print("Content-Type: text/html\r\n");
                                printStream.print("\r\n");
                                // send the content
                                printStream.print("<!DOCTYPE html><html><head></head><body>");
                                printStream.print("<p>You requested the reverse string of <b>" + parameter + "</b>.</p>");
                                printStream.print("<p>The answer is <b>" + reverse + "</b>.</p>");
                                printStream.print("</body></html>");
                            } else {
                                print404(printStream, request);
                            }
                        } else {
                            print404(printStream, request);
                        }
                    } else {
                        print404(printStream, request);
                    }
                } else {
                    // open the requested file and copy it to the client
                    try {
                        // all our requested files must be in the "Documents" directory
                        FileInputStream fileInputStream = new FileInputStream("Documents/" + request);
                        int responseLength = fileInputStream.available();
                        // send the response headers
                        printStream.print("HTTP/1.1 200 OK\r\n");
                        printStream.print("Content-Length: " + responseLength + "\r\n");
                        printStream.print("Content-Type: text/html\r\n");
                        printStream.print("\r\n");
                        // send the content
                        byte[] data = new byte[responseLength];
                        fileInputStream.read(data);
                        outputStream.write(data);
                        fileInputStream.close();
                    } catch (FileNotFoundException e) {
                        printStream.print("HTTP/1.1 404 Not Found \r\n\r\n");
                        System.out.println("404 Not Found: " + request);
                    }
                }
            } else {
                printStream.print("HTTP/1.1 400 Bad Request \r\n\r\n");
                System.out.println("400 Bad Request: " + request);
            }
        } catch (IOException e) {
            System.out.println("Generic I/O error " + e);
        } finally {
            try {
                // close all the channels
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                System.out.println("I/O error on close " + e);
            }
        }
    }

    private void print404(PrintStream printStream, String request) {
        printStream.print("HTTP/1.1 404 Not Found \r\n\r\n");
        System.out.println("404 Not Found: " + request);
    }

    private String launchExternalProcess(String parameter) {
        // launch the external process
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", "cd scripts/ && java Reverse " + parameter);
        StringBuilder output = new StringBuilder();
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
            } else {
                System.out.println("Something went wrong!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}

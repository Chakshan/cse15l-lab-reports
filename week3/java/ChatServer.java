import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {

	String conversation = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return conversation;
        } else if (url.getPath().equals("/add-message")) {
            String[] data = url.getQuery().split("&");
			if (data[0].startsWith("s=") &&
					data[1].startsWith("user")) {
				String message = data[0].split("=")[1];
				String user = data[1].split("=")[1];

				conversation += (String.format("%s: %s", user, message) + "\n");
				return conversation;
			} else {
				return "/add-messages requires a query parameter s and user\n";
			}	
        } else {
            return "404 Not Found!";
        }
    }
}

class ChatServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}

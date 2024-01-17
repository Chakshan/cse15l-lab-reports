import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.

	ArrayList<String> dataBase = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
			String defaultMessage = "";
			defaultMessage += ("Add a string with /add?s=<string>\n" +
				   "Search the database with /search?s=<query>\n");

			defaultMessage += "Current Database: \n";

			for(String s : dataBase) {
				defaultMessage += (s + "\n");
			}
			return defaultMessage;
        } else if (url.getPath().equals("/search")) {
			String searchResults = "Search Results: \n";
			String[] parameters = url.getQuery().split("=");

			if (parameters[0].equals("s")) {
				String query = parameters[1];

				for (String s : dataBase) {
					System.out.printf("Query: %s; String: %s", query, s);
					if (s.indexOf(query) >= 0) {
						searchResults += (s + "\n");
					}
				}
				return searchResults;
			}
			return "404 Not Found!";
		} else {
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                	dataBase.add(parameters[1]);
					return String.format("Added %s to the database", parameters[1]);
				}
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}

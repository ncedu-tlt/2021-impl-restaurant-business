import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(1313), 0);
        httpServer.createContext("/news", new MyHandler());
        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("Server started, navigate to: http://localhost:1313/news");
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response =
                    "<html>\n" +
                    "  <head>\n" +
                    "    <!--<meta charset=\"UTF-8\">-->\n" +
                    "  </head>\n" +
                    "  <body>\n" +
                    "    <h1></h1>\n" +
                    "    <form action=\"\" method=\"GET\">\n" +
                    "      <textarea class=\"activity\"></textarea>\n" +
                    "      <div>\n" +
                    "        <select name = \"num\">\n" +
                    "          <option value=\"0\">-</option>\n" +
                    "          <option value=\"1\">1</option>\n" +
                    "          <option value=\"2\">2</option>\n" +
                    "          <option value=\"3\">3</option>\n" +
                    "          <option value=\"4\">4</option>\n" +
                    "          <option value=\"5\">5</option>\n" +
                    "          <option value=\"6\">6</option>\n" +
                    "          <option value=\"7\">7</option>\n" +
                    "          <option value=\"8\">8</option>\n" +
                    "          <option value=\"9\">9</option>\n" +
                    "          <option value=\"10\">10</option>\n" +
                    "          <option value=\"11\">11</option>\n" +
                    "          <option value=\"12\">12</option>\n" +
                    "          <option value=\"13\">13</option>\n" +
                    "          <option value=\"14\">14</option>\n" +
                    "          <option value=\"15\">15</option>\n" +
                    "          <option value=\"16\">16</option>\n" +
                    "          <option value=\"17\">17</option>\n" +
                    "          <option value=\"18\">18</option>\n" +
                    "          <option value=\"19\">19</option>\n" +
                    "          <option value=\"20\">20</option>\n" +
                    "          <option value=\"21\">21</option>\n" +
                    "          <option value=\"22\">22</option>\n" +
                    "          <option value=\"23\">23</option>\n" +
                    "          <option value=\"24\">24</option>\n" +
                    "          <option value=\"25\">25</option>\n" +
                    "          <option value=\"26\">26</option>\n" +
                    "          <option value=\"27\">27</option>\n" +
                    "          <option value=\"28\">28</option>\n" +
                    "          <option value=\"29\">29</option>\n" +
                    "          <option value=\"30\">30</option>\n" +
                    "          <option value=\"31\">31</option>\n" +
                    "          <option value=\"32\">32</option>\n" +
                    "          <option value=\"33\">33</option>\n" +
                    "          <option value=\"34\">34</option>\n" +
                    "          <option value=\"35\">35</option>\n" +
                    "          <option value=\"36\">36</option>\n" +
                    "          <option value=\"37\">37</option>\n" +
                    "          <option value=\"38\">38</option>\n" +
                    "          <option value=\"39\">39</option>\n" +
                    "          <option value=\"40\">40</option>\n" +
                    "          <option value=\"41\">41</option>\n" +
                    "          <option value=\"42\">42</option>\n" +
                    "          <option value=\"43\">43</option>\n" +
                    "          <option value=\"44\">44</option>\n" +
                    "          <option value=\"45\">45</option>\n" +
                    "          <option value=\"46\">46</option>\n" +
                    "          <option value=\"47\">47</option>\n" +
                    "          <option value=\"48\">48</option>\n" +
                    "          <option value=\"49\">49</option>\n" +
                    "          <option value=\"50\">50</option>\n" +
                    "        </select>\n" +
                    "        <button type=\"submit\">Next</button>\n" +
                    "      </div>\n" +
                    "    </form>\n" +
                    "  </body>\n" +
                    "</html>";

            StringBuilder response1 = new StringBuilder(response);
            setOption(response1, new Random().nextInt(50));
            setResponse(response1, getNews(getRequest(exchange)) + "\n");
            exchange.sendResponseHeaders(200, response1.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response1.toString().getBytes("Windows-1251"));
            os.close();
        }
    }
    static void setOption(StringBuilder response, int optionValue) {
        response.insert(response.indexOf(">", response.indexOf("value=" +"\"" + optionValue + "\"")), " selected");
    }
    static String getNews(int numOfElement) throws IOException {

        String line = "";

        if (numOfElement > 0) {
            URL site = new URL("http://news.olegmakarenko.ru/news");
            BufferedReader input = new BufferedReader(new InputStreamReader(site.openStream()));
            String input1 = "";

            while (!input1.contains("<a class=\"anewsli\""))
                input1 = input.readLine();

            int firstIndex = 0;

            for (int i = 0; i < numOfElement; i++) {
                firstIndex = input1.indexOf("<li", firstIndex + 2);
            }

            String news = input1.substring(firstIndex + "<li class=\"newsli\">".length(), input1.indexOf("</li>", firstIndex));
            line = "Заголовок: " + news.substring(63, news.lastIndexOf('<'))+". Время:" + news.substring(24, 29);
        }

        return line;

    }

    static void setResponse(StringBuilder response, String content) {
        response.insert(response.indexOf(">", response.indexOf("class=\"activity\"")) + 1, content);
    }


    public static int getRequest(HttpExchange t) {
        String requestResult = t.getRequestURI().getQuery();
        return (requestResult == null || requestResult.equals("0")) ? 0 : Integer.parseInt(
                requestResult.substring(requestResult.indexOf("=") + 1));
    }

}






package com.kodokushi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Main {

    static final String host = "localhost";
    static final int port = 8080;
    static final String contextPath = "news";

    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext("/" + contextPath, new MyHandler());
            httpServer.setExecutor(null);
            httpServer.start();
            System.out.println("Server started, navigate to: http://" + host + ":" + port + "/" + contextPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder response = new StringBuilder(new String(Files.readAllBytes(Path.of(
                    "src/main/resources/view/news.html"))));
            setOptionSelected(response, new Random().nextInt(1, 50));
            setNews(response, getRequestNumber(exchange) - 1);
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes("Windows-1251"));
            os.close();
        }
    }

    /**
     * Получает дату и заголовок новости, заданной по номеру
     * @param newsId номер новости
     */
    public static void setNews(StringBuilder response, int newsId) {
        try {
            NodeList nodeList = new Parser("http://news.olegmakarenko.ru/news").parse(new TagNameFilter("LI"));
            System.out.println(nodeList.size() + " " + newsId);
            if (nodeList.size() > newsId && newsId >= 0) {
                response.insert(response.indexOf(">", response.indexOf("textarea")) + 1,
                        nodeList.elementAt(newsId).getFirstChild().getChildren().toHtml() + " " +
                        nodeList.elementAt(newsId).getLastChild().getChildren().toHtml());
            }
        } catch (ParserException e) {
            e.printStackTrace();
        }
    }

    /**
     * Устанавливает атрибут "selected" для определенного тега <option>
     * @param response рабочая строка
     * @param optionValue значение value в теге <option>
     */
    public static void setOptionSelected(StringBuilder response, int optionValue) {
        response.insert(response.indexOf(">", response.indexOf("value=\"" + optionValue + "\"")), " selected");
    }

    /**
     * Возвращает значение переменной number, переданной в GET-запросе
     * @param exchange -
     * @return значение number
     */
    public static int getRequestNumber(HttpExchange exchange) {
        String requestResult = exchange.getRequestURI().getQuery();
        return (requestResult == null || requestResult.equals("0")) ? 0 : Integer.parseInt(
                requestResult.substring(requestResult.indexOf("=") + 1));
    }
}

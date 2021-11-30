package com.kodokushi;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class Main {

    static final int port = 8080;
    static final String contextPath = "/news";

    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServer.createContext(contextPath, new MyHandler());
            httpServer.setExecutor(null);
            httpServer.start();
            System.out.println("Server started, navigate to: http://localhost:" + port + contextPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            StringBuilder response = new StringBuilder(readHtmlFile());
            setOptionSelected(response, new Random().nextInt(51));
            insertContent(response, getNews(getRequestNumber(exchange)) + "\n");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.toString().getBytes("Windows-1251"));
            os.close();
        }
    }

    /**
     * Получает дату и заголовок новости, заданной по номеру
     * @param counter номер новости
     * @return строку с датой и заголовком новости
     */
    static String getNews(int counter) {
        String content = "";
        try {
            URL site = new URL("http://news.olegmakarenko.ru/news");
            BufferedReader input = new BufferedReader(new InputStreamReader(site.openStream()));
            String inputLine;
            do {
                inputLine = input.readLine();
            } while (!inputLine.contains("<a class=\"anewsli\""));
            int firstIndex = 0;
            for (int i = 0; i < counter; i++) {
                firstIndex = inputLine.indexOf("<li", firstIndex + 2);
            }
            // 19 - длина открывающего тега <li class="newsli">
            String news = inputLine.substring(firstIndex + 19, inputLine.indexOf("</li>", firstIndex));
            // news представляет собой строку вида <span class="shortdate">09:27</span><span class="headlinetext">Текст новости</span>
            // substring(24, 29) извлекает дату (09:27), substring(63, lastIndex('<')) извлекает текст новости
            content = news.substring(24, 29) + " " + news.substring(63, news.lastIndexOf('<'));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * Вставляет контент в тело тега с соответствующим селектором
     * @param response рабочая строка
     * @param content вставляемый контент
     */
    static void insertContent(StringBuilder response, String content) {
        response.insert(response.indexOf(">", response.indexOf("class=\"content\"")) + 1, content);
    }

    /**
     * Устанавливает атрибут "selected" для определенного "<option>"
     * @param response рабочая строка
     * @param optionValue значение value в <option>
     */
    static void setOptionSelected(StringBuilder response, int optionValue) {
        response.insert(response.indexOf(">", response.indexOf("value=\"" + optionValue + "\"")), " selected");
    }

    /**
     * Возвращает значение переменной number, переданной в GET-запросе
     * @param exchange -
     * @return значение number
     */
    public static int getRequestNumber(HttpExchange exchange) {
        String requestResult = exchange.getRequestURI().getQuery();
        return Integer.parseInt(requestResult.substring(requestResult.indexOf("=") + 1));
    }

    /**
     * Считывает файл в строку
     * @return содержимое файла в виде строки
     */
    static String readHtmlFile() {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Path.of("src/resources/view/news.html")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}

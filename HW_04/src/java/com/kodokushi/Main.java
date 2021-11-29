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
import java.nio.charset.StandardCharsets;
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
        public void handle(HttpExchange t) throws IOException {
            String response = readHtmlFile();
            Random randomGenerator = new Random();
            int rand = randomGenerator.nextInt(51);
            response = setOptionSelected(response, rand);
            response = insertContent(response, "class", "content", getContent(rand) + "\n");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes("Windows-1251"));
            os.close();
        }
    }

    /**
     * Получает дату и заголовок новости, заданной по номеру
     * @param counter номер новости
     * @return строку с датой и заголовком новости
     */
    static String getContent(int counter) {
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
            String news = inputLine.substring(firstIndex + 19, inputLine.indexOf("</li>", firstIndex));
            content = news.substring(24, 29) + " " + news.substring(63, news.lastIndexOf('<'));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * Вставляет контент в тело тега с соответствующим селектором
     * @param response рабочая строка
     * @param selector название селектора
     * @param selectorValue значение селектора
     * @param content вставляемый контент
     * @return измененная рабочая строка
     */
    static String insertContent(String response, String selector, String selectorValue, String content) {
        return new StringBuilder(response).insert(response.indexOf(">", response.indexOf(selector + "=\"" + selectorValue +
                "\"")) + 1, content).toString();
    }

    /**
     * Устанавливает атрибут "selected" для определенного "<option>"
     * @param response рабочая строка
     * @param optionValue значение value в <option>
     * @return измененная рабочая строка
     */
    static String setOptionSelected(String response, int optionValue) {
        return new StringBuilder(response).insert(response.indexOf(">", response.indexOf("value=\"" + optionValue +
                "\"")), " selected").toString();
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

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {
    public static Element second;
    public static Random rand = new Random();
    public static int a = rand.nextInt(50);
    public static void main(String[] args) throws Exception {


            HttpServer server = HttpServer.create(new InetSocketAddress(1313), 0);
            server.createContext("/news", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("Server started, navigate to: http://localhost:1313/news");


            Document doc = Jsoup.connect("http://news.olegmakarenko.ru/news")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36")
                    .referrer("http://www.google.com")
                    .get();
            Elements listNews = doc.select("div#news");

            int i = 0;

            for (Element element : listNews.select("span.headlinetext")){
            i+=1;
                if (i == a) {
                    second = element;
                    System.out.println(second.text());
                }
            }


    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Random rand = new Random();
            String response = "<html><head><title>NEWS</title><!--<meta charset=\"UTF-8\">-->ActualNews:"+""+"</head><body><h1></h1>" +
                    "<select><option value = 1>1</option><option value = 2>2</option><option value = 3>3</option><option value = 4>4</option><option value = 5>5</option><option value = 6>6</option><option value = 7>7</option><option value = 8>8</option><option value = 9>9</option><option value = 10>10</option>" +
                    "<option value = 11>11</option><option value = 12>12</option><option value = 13>13</option><option value = 14>14</option><option value = 15>15</option><option value = 16>16</option><option value = 17>17</option><option value = 18>18</option><option value = 19>19</option><option value = 20>20</option>" +
                    "<option value = 21>21</option><option value = 22>22</option><option value = 23>23</option><option value = 24>24</option><option value = 25>25</option><option value = 26>26</option><option value = 27>27</option><option value = 28>28</option><option value = 29>29</option><option value = 30>30</option>" +
                    "<option value = 31>31</option><option value = 32>32</option><option value = 33>33</option><option value = 34>34</option><option value = 35>35</option><option value = 36>36</option><option value = 37>37</option><option value = 38>38</option><option value = 39>39</option><option value = 40>40</option>" +
                    "<option value = 41>41</option><option value = 42>42</option><option value = 43>43</option><option value = 44>44</option><option value = 45>45</option><option value = 46>46</option><option value = 47>47</option><option value = 48>48</option><option value = 49>49</option><option value = 50>50</option></select>" +
                    "<form action=\"http://localhost:1313/news\"><button>NewValue</button></form></body></html>";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}









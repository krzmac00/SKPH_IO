package com.example.skph.service;

import jakarta.servlet.http.*;
import jakarta.servlet.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class GeocodingService extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("q");
        String url = "https://nominatim.openstreetmap.org/search?q=" + query + "&format=json";

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("User-Agent", "YourJavaApp");
        connection.setRequestMethod("GET");

        InputStream inputStream = connection.getInputStream();
        StringBuilder responseBody = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
        }

        response.setContentType("application/json");
        response.getWriter().write(responseBody.toString());
    }
}


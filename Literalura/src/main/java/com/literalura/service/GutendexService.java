package com.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.model.Autor;
import com.literalura.model.Livro;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GutendexService {

    private static final String API_BASE = "https://gutendex.com/books/";

    public Livro buscarLivroPorTitulo(String titulo) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String url = API_BASE + "?search=" + titulo.replace(" ", "+");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            JsonNode firstBook = root.get("results").get(0);

            JsonNode authorNode = firstBook.get("authors").get(0);
            Autor autor = new Autor(
                    authorNode.get("name").asText(),
                    authorNode.get("birth_year").isNull() ? null : authorNode.get("birth_year").asInt(),
                    authorNode.get("death_year").isNull() ? null : authorNode.get("death_year").asInt()
            );

            String idioma = firstBook.get("languages").get(0).asText();

            return new Livro(
                    firstBook.get("title").asText(),
                    idioma,
                    firstBook.get("download_count").asInt(),
                    autor
            );

        } catch (Exception e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
            return null;
        }
    }
}

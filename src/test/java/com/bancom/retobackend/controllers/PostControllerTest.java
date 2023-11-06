package com.bancom.retobackend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class PostControllerTest {

    @LocalServerPort
    private int port;


    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createPost() {
              String url = "http://localhost:" + port + "/posts/1"; // "/posts/idUsuario"
            HttpHeaders headers = new HttpHeaders();
            restTemplate = new TestRestTemplate();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> requestEntity = new HttpEntity<>("{\"text\":\"Mi ultimo post\"}", headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void getPostByUser() {
        String url = "http://localhost:" + port + "/posts/1"; // "/post/{idUsuario}"
        restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void updatePost() throws Exception {
        String url = "http://localhost:" + port + "/posts/2/1"; // "/posts/{idPost}/{idUsuario}"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"text\":\"nuevo texto\"}";

        mockMvc.perform(MockMvcRequestBuilders.patch(url)
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                .andReturn();
    }

    @Test
    public void deletePost() {
        String url = "http://localhost:" + port + "/posts/2/1"; // "/posts/{idPost}/{idUser}"
        restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

        assertEquals(200, response.getStatusCode().value());
    }
}
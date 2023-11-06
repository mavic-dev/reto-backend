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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.DEFINED_PORT, properties = {"server.port=8080"})
@AutoConfigureMockMvc
class UsuarioControllerTest {
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate ;
    @Autowired
    private MockMvc mockMvc;
    @Test
    void createUser() {
        String url = "http://localhost:" + port + "/usuarios";
        restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>("{\"name\":\"Victor Manuel\", \"lastName\":\"Saravia\", \"cellphone\":\"123456789\", \"password\":\"123456789\"}", headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println(response.toString());

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void getUsuarioById() {
        String url = "http://localhost:" + port + "/usuarios/1"; // "/usuarios/{idUsuario}"
        restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        System.out.println(response.toString());

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void updateUser() throws Exception {
        String url = "http://localhost:" + port + "/usuarios/1"; // "/usuarios/idUsuario"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"cellphone\":\"11111111\"}";

        mockMvc.perform(MockMvcRequestBuilders.patch(url)
                        .content(requestJson)
                        .header(HttpHeaders.CONTENT_TYPE, "application/json"))
                        .andReturn();
    }

    @Test
    void deleteUser() {
        String url = "http://localhost:" + port + "/usuarios/1"; //  "/usuarios/idUsuario"
        restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);

        assertEquals(200, response.getStatusCode().value());
    }

    @Test
    void getUsers() {
        String url = "http://localhost:" + port + "/usuarios";
        restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        System.out.println(response.toString());

        assertEquals(200, response.getStatusCode().value());
    }
}
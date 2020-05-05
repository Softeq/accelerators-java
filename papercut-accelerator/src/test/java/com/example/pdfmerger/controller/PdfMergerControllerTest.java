package com.example.pdfmerger.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PdfMergerControllerTest {

    public static final String LOCALHOST = "http://localhost:";
    public static final String PATH = "pdf/";
    public static final String CONCATENATED_FILE = "concatenated.pdf";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        try {
            File toDelete = getConcatenatedPdf();
            if (!toDelete.delete()) {
                throw new RuntimeException();
            }
        } catch (NullPointerException e) {
            System.out.println("File not found. Continue test");
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void constructSinglePdf() {

        ResponseEntity response = this.restTemplate.getForEntity(LOCALHOST + port + "/papercut", ResponseEntity.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        File file = getConcatenatedPdf();
        assertEquals(CONCATENATED_FILE, file.getName());
        file.delete();
    }

    private File getConcatenatedPdf() {
        String resource = getClass().getClassLoader().getResource(PATH + CONCATENATED_FILE).getPath();
        return new File(resource);
    }
}
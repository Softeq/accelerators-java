package com.example.papercutserver.controller;

import com.example.papercutserver.service.PaperсutService;
import com.itextpdf.text.DocumentException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.io.IOException;

@RestController
@RequestMapping("/papercut")
public class PaperсutController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PaperсutService paperсutService;

    @GetMapping
    public ResponseEntity constructSinglePdf() throws IOException, DocumentException {
        paperсutService.createSinglePdf(uploadPath);
        return ResponseEntity.ok().build();
    }
}

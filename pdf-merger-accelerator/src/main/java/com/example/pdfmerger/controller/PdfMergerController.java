package com.example.pdfmerger.controller;

import com.example.pdfmerger.service.PdfMergerService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/merge-pdf")
public class PdfMergerController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private PdfMergerService pdfMergerService;

    @GetMapping
    public ResponseEntity constructSinglePdf() throws IOException, DocumentException {
        pdfMergerService.createSinglePdf(uploadPath);
        return ResponseEntity.ok().build();
    }
}

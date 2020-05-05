package com.example.pdfmerger.service.impl;

import com.example.pdfmerger.service.PdfMergerService;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class PdfBoxMerger extends PdfMergerService {

    @Override
    public File mergePdfIntoNewFile(String resource, List<String> paths, String fileName) throws IOException {

        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        paths.forEach(path -> {
            try {
                pdfMergerUtility.addSource(path);
            } catch (FileNotFoundException e) {
                throw new RuntimeException();
            }
        });

        File newPdf = new File(resource + fileName);
        newPdf.createNewFile();

        pdfMergerUtility.setDocumentMergeMode(PDFMergerUtility.DocumentMergeMode.PDFBOX_LEGACY_MODE);
        pdfMergerUtility.setDestinationFileName(newPdf.getPath());

        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMixed(MAX_MEMORY_USAGE));
        return newPdf;
    }
}

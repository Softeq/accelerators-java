package com.example.pdfmerger.service.impl;

import com.example.pdfmerger.service.PdfMergerService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ITextMerger extends PdfMergerService {

    @Override
    public File mergePdfIntoNewFile(String resource, List<String> paths, String fileName) throws IOException,
        DocumentException {

        File newPdf = new File(resource + fileName);
        newPdf.createNewFile();
        Document document = new Document();
        FileOutputStream outputStream = new FileOutputStream(newPdf);
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();

        paths.forEach(path -> {
            try {
                PdfReader reader = new PdfReader(path);
                for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                    // Works with multipage files
                    document.newPage();
                    //import the page from source pdf
                    PdfImportedPage page = writer.getImportedPage(reader, i);
                    //add the page to the destination pdf
                    cb.addTemplate(page, 0, 0);
                }
            } catch (IOException e) {
                throw new RuntimeException();
            }
        });
        outputStream.flush();
        document.close();
        outputStream.close();
        return newPdf;
    }
}

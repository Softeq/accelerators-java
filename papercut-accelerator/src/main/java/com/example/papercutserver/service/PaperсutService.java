package com.example.papercutserver.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PaperсutService {

    public static File mergePdfIntoNewFileApache(String resource, List<String> paths, String fileName) throws IOException {
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        paths.forEach(path -> {
            try {
                pdfMergerUtility.addSource(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });

        File newPdf = new File(resource + fileName);
        newPdf.createNewFile();

        pdfMergerUtility.setDocumentMergeMode(PDFMergerUtility.DocumentMergeMode.OPTIMIZE_RESOURCES_MODE);
        pdfMergerUtility.setDestinationFileName(newPdf.getPath());

        pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
//            pdfMergerUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
        return newPdf;
    }

    public static File mergePdfIntoNewFileIText(String resource, List<String> paths, String fileName) throws IOException, DocumentException {

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
                    document.newPage();
                    //import the page from source pdf
                    PdfImportedPage page = writer.getImportedPage(reader, i);
                    //add the page to the destination pdf
                    cb.addTemplate(page, 0, 0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        outputStream.flush();
        document.close();
        outputStream.close();
        return newPdf;
    }

    public static void createSinglePdf(String uploadPath) throws IOException, DocumentException {

        String resource = getResourcePath(uploadPath);
        List<String> paths = getPaths(resource);

        mergePdfIntoNewFileIText(resource, paths, "/concatenated.pdf");
    }

    public static String getResourcePath(String uploadPath) {
        return Objects.requireNonNull(PaperсutService.class.getClassLoader().getResource(uploadPath)).getPath();
    }

    public static List<String> getPaths(String resource) {
        try ( Stream<Path> walk = Files.walk(Paths.get(resource)) ){

            return walk
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.comparing(
                            path ->
                                    Integer.valueOf(
                                            path.getFileName().toString().substring(0, path.getFileName().toString().lastIndexOf("."))
                                    )
                    ))
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

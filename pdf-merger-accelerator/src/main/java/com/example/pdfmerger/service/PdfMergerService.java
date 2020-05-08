package com.example.pdfmerger.service;

import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class PdfMergerService {

    public static final Long MAX_MEMORY_USAGE = 2684354560L;

    public abstract File mergePdfIntoNewFile(String resource, List<String> paths, String s) throws IOException, DocumentException;

    public void createSinglePdf(String uploadPath) throws IOException, DocumentException {

        String resource = getResourcePath(uploadPath);
        List<String> paths = getPaths(resource);

        mergePdfIntoNewFile(resource, paths, "/concatenated.pdf");
    }

    public static String getResourcePath(String uploadPath) {
        try {
            return Objects.requireNonNull(Paths.get(PdfMergerService.class.getClassLoader().getResource(uploadPath).toURI())).toString();
        } catch (URISyntaxException e) {
            return "";
        }
    }

    public static List<String> getPaths(String resource) {
        try (Stream<Path> walk = Files.walk(Paths.get(resource))) {

            return walk
                .filter(Files::isRegularFile)
                .sorted(Comparator.comparing(Path::getFileName))
                .map(Path::toString)
                .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}

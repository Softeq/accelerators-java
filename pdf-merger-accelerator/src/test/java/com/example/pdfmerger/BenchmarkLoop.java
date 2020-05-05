package com.example.pdfmerger;

import com.example.pdfmerger.service.PdfMergerService;
import com.example.pdfmerger.service.impl.ITextMerger;
import com.example.pdfmerger.service.impl.PdfBoxMerger;
import com.itextpdf.text.DocumentException;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Timeout;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 1, time = 15, timeUnit = TimeUnit.MINUTES)
@Measurement(iterations = 2)
@Timeout(time = 15, timeUnit = TimeUnit.MINUTES)
public class BenchmarkLoop {

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir") + "/tmp/";

    @Param({"10"})
    private int N;

    private List<String> DATA_FOR_TESTING;
    private String resource;

    @Setup(Level.Trial)
    public synchronized void initialize() {
        resource = PdfMergerService.getResourcePath("pdf/");
        DATA_FOR_TESTING = createData(resource);

        java.util.logging.Logger
            .getLogger("org.apache.pdfbox").setLevel(java.util.logging.Level.WARNING);
    }

    @TearDown
    public synchronized void tearDown() throws IOException {
        File tempDir = new File(resource + TEMP_DIR);
        if (tempDir.isDirectory()) {
            FileUtils.deleteDirectory(tempDir);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
            .include(BenchmarkLoop.class.getSimpleName())
            .forks(1)
            .resultFormat(ResultFormatType.JSON)
            .result(System.getProperty("java.io.tmpdir") + "/test-result.jmh.json")
            .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void apachePdfBoxTest(Blackhole bh) throws IOException, DocumentException {
        startBenchmarkForLib(Lib.APACHE, bh);
    }

    @Benchmark
    public void iTextTest(Blackhole bh) throws IOException, DocumentException {
        startBenchmarkForLib(Lib.ITEXT, bh);
    }

    private void startBenchmarkForLib(Lib lib, Blackhole bh) throws IOException, DocumentException {

        PdfMergerService pdfMergerService;
        switch (lib) {
            case ITEXT:
                pdfMergerService = new ITextMerger();
                break;
            case APACHE:
                pdfMergerService = new PdfBoxMerger();
                break;
            default:
                throw new RuntimeException();

        }

        Path tempDir = Files.createDirectory(Paths.get(TEMP_DIR));
        for (int i = 0; i < N; i++) {
            File newFile = pdfMergerService.mergePdfIntoNewFile(TEMP_DIR, DATA_FOR_TESTING, i + ".pdf");
            bh.consume(newFile);
        }
        FileUtils.deleteDirectory(tempDir.toFile());
    }

    private List<String> createData(String resource) {
        return PdfMergerService.getPaths(resource);
    }

    private enum Lib {
        APACHE, ITEXT;
    }
}

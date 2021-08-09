package com.softeq.wiremock.service;

import com.softeq.wiremock.dto.RatesResponse;
import com.softeq.wiremock.dto.WeatherResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet weatherSheet;
    private XSSFSheet ratesSheet;
    private CellStyle headerStyle;
    private CellStyle lineStyle;
    private CellStyle dateStyle;

    public ExcelExporter() {
        this.workbook = new XSSFWorkbook();
        setStyles();
    }

    public void export(HttpServletResponse response, List<WeatherResponse> weatherResponses, List<RatesResponse> ratesResponses) {
        createFile(weatherResponses, ratesResponses);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                workbook.close();
                outputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void createFile(List<WeatherResponse> weatherResponses, List<RatesResponse> ratesResponses) {
        if (weatherResponses != null) {
            writeWeatherHeaderLine();
            writeWeatherDataLines(weatherResponses);
        }
        if (ratesResponses != null) {
            writeRatesHeaderLine();
            writeRatesDataLines(ratesResponses);
        }
    }

    private void setStyles() {
        headerStyle = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeight(16);
        headerStyle.setFont(headerFont);

        lineStyle = workbook.createCellStyle();
        XSSFFont rowFont = workbook.createFont();
        rowFont.setFontHeight(14);
        lineStyle.setFont(rowFont);

        dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat("hh:mm dd.mm.yyyy"));
        dateStyle.setFont(rowFont);
    }

    private void createCell(XSSFSheet sheet, Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Double) {
            cell.setCellValue((Double) value);
            cell.setCellStyle(style);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
            cell.setCellStyle(style);
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
            cell.setCellStyle(style);
        } else if (value instanceof LocalDateTime) {
            cell.setCellValue((LocalDateTime) value);
            cell.setCellStyle(dateStyle);
        } else {
            cell.setCellValue((String) value);
            cell.setCellStyle(style);
        }
    }

    private void writeWeatherHeaderLine() {
        weatherSheet = workbook.createSheet("Weather report");
        Row row = weatherSheet.createRow(0);
        createCell(weatherSheet, row, 0, "City name", headerStyle);
        createCell(weatherSheet, row, 1, "Country code", headerStyle);
        createCell(weatherSheet, row, 2, "Temperature", headerStyle);
        createCell(weatherSheet, row, 3, "Weather", headerStyle);
    }

    private void writeWeatherDataLines(List<WeatherResponse> weatherResponses) {
        int rowCount = 1;
        for (WeatherResponse response : weatherResponses) {
            Row row = weatherSheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(weatherSheet, row, columnCount++, response.getCityName(), lineStyle);
            createCell(weatherSheet, row, columnCount++, response.getCountryCode(), lineStyle);
            createCell(weatherSheet, row, columnCount++, response.getTemperature(), lineStyle);
            createCell(weatherSheet, row, columnCount++, response.getWeatherDescription(), lineStyle);

        }
    }

    private void writeRatesHeaderLine() {
        ratesSheet = workbook.createSheet("Rates report");
        Row row = ratesSheet.createRow(0);
        createCell(ratesSheet, row, 0, "City name", headerStyle);
        createCell(ratesSheet, row, 1, "Currency ID", headerStyle);
        createCell(ratesSheet, row, 2, "Date", headerStyle);
        createCell(ratesSheet, row, 3, "Abbreviation", headerStyle);
        createCell(ratesSheet, row, 4, "Scale", headerStyle);
        createCell(ratesSheet, row, 5, "Currency name", headerStyle);
        createCell(ratesSheet, row, 6, "Official rate", headerStyle);
    }

    private void writeRatesDataLines(List<RatesResponse> ratesResponses) {
        int rowCount = 1;
        for (RatesResponse response : ratesResponses) {
            Row row = ratesSheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(ratesSheet, row, columnCount++, response.getCityName(), lineStyle);
            createCell(ratesSheet, row, columnCount++, response.getCurrencyId(), lineStyle);
            createCell(ratesSheet, row, columnCount++, response.getDate(), lineStyle);
            createCell(ratesSheet, row, columnCount++, response.getCurrencyAbbreviation(), lineStyle);
            createCell(ratesSheet, row, columnCount++, response.getCurrencyScale(), lineStyle);
            createCell(ratesSheet, row, columnCount++, response.getCurrencyName(), lineStyle);
            createCell(ratesSheet, row, columnCount++, response.getCurrencyOfficialRate(), lineStyle);

        }
    }
}

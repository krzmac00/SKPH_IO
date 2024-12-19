package com.example.skph.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;

//Wymagne klasy innych zepołów i baza danych
//Zrobić pełne generowanie PDF (i sprawdzić czy wogóle działa)
//Na podstawie nazwy pliku najpierw sprawdzić czy istnieje on lokalnie, później w db, później wygenerować
public class ResourceController {

    /*public List<Resource> getResourceData(String resourceType, LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }

    public List<DamageReport> getDamageData(LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }

    public List<AidReport> getAidData(LocalDate dateFrom, LocalDate dateTo) {
        return null;
    }

    public Donor getDonorData(String donorId) {
        return null;
    }*/

    public File generatePDFReport(String reportType, LocalDate dateFrom, LocalDate dateTo) {
        //sprawdzić plik lokalny
        Path outputPath = Path.of("HelloWorldReport.pdf");
        try {
            PdfWriter writer = new PdfWriter(outputPath.toString());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.add(new Paragraph("Hello World!"));
            document.close();
            return outputPath.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

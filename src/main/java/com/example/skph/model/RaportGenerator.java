package com.example.skph.model;

import java.time.LocalDate;
import com.itextpdf.layout.element.Paragraph;

//Wymagana baza danych
public class RaportGenerator {

    public Paragraph prepareFile(String reportName) {return null;}

    public void generateResourceAvalibilityReport(LocalDate dateFrom, LocalDate dateTo) {}

    public void generateDamageReport(LocalDate dateFrom, LocalDate dateTo) {}

    public void generateAidReport(LocalDate dateFrom, LocalDate dateTo) {}

    public void generateDonationReport(LocalDate dateFrom, LocalDate dateTo) {}

    public void generateDonationConfirmation(Long donorId) {}
}

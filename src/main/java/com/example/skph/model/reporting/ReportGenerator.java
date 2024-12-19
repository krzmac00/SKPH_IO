package com.example.skph.model.reporting;

import java.time.LocalDate;
import com.itextpdf.layout.element.Paragraph;

//Wymagana baza danych
public class ReportGenerator {

    public Paragraph prepareFile(String reportName) {return null;}

    public void generateResourceAvailabilityReport(LocalDate dateFrom, LocalDate dateTo) {}

    public void generateDamageReport(LocalDate dateFrom, LocalDate dateTo) {}

    public void generateAidReport(LocalDate dateFrom, LocalDate dateTo) {}

    public void generateDonationReport(LocalDate dateFrom, LocalDate dateTo) {}

    public void generateDonationConfirmation(Long donorId) {}
}

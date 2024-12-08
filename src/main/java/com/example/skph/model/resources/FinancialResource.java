package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder
@Table(name = "financial_resources")
public class FinancialResource extends Resource {

    private BigDecimal amount;

    private String currency;
}

package com.example.skph.model.resources;

import com.example.skph.model.Resource;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "financial_resource")
public class FinancialResource extends Resource {

    private String currency;
}

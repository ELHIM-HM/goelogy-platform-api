package com.geology_platform.geology.dto.both;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class InternshipDTO {
    private String title;
    private String description;
    private double remuneration;
    private LocalDate startDate;
    private LocalDate endDate;
    private long categoryId;
    private long sectorId;
}

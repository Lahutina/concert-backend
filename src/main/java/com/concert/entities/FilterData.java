package com.concert.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class FilterData {
    private String minPrice;
    private String maxPrice;
    private String selectedCity;
    private String selectedDate;
}

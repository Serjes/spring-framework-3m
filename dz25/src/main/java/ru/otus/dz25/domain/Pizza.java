package ru.otus.dz25.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pizza {
    public static final int NUMBER_OF_SIZES = 3;
    private String name;
    private int size;
}

package com.mygarden.mygarden.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFruitDto {
    private Long id;
    private String name;
    private Calendar dateCreate;
}

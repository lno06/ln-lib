package com.lnlib.springboot.domain;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedPassengerResponse
{
    private Long totalPassengers;
    private Long totalPages;
    private List<PassengerResponse> data;
}

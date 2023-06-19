package com.concert.entities.dto;

public record RegisterDto(String firstName, String lastName, String email, String password, String confirmPassword) {
}

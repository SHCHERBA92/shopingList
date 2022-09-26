package com.example.shopinglist.DTO.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Mail_DTO {
    private String mail;
    private String code;
}

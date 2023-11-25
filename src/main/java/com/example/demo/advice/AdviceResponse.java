package com.example.demo.advice;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdviceResponse {

    private String requestUrl;
    private String extension;
    private Long size;

}

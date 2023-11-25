package com.example.demo.requestholder;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SessionType {
    USER_SESSION("userSession");
    public final String sessionName;
}

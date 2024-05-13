package com.jurgenvrapi.garastore.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTDTO {
    private String token;

    public JWTDTO(String token) {
        this.token = token;
    }

}

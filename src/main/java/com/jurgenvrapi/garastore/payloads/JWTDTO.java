package com.jurgenvrapi.garastore.payloads;

import com.jurgenvrapi.garastore.entities.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JWTDTO {
    private String token;
    private User user;

    public JWTDTO(String token) {
        this.token = token;

    }

    public JWTDTO(String token, User user) {
        this.token = token;
        this.user = user;
    }

    @Override
public String toString() {
    return
             token          ;
}

}

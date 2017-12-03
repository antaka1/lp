package ro.lsacbucuresti.lanpartyquiz.security.service;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;
    private final Integer status;

    public JwtAuthenticationResponse(String token,Integer status) {
        this.token = token;
        this.status = status;
    }

    public String getToken() {
        return this.token;
    }

    public Integer getStatus() {
        return status;
    }
}

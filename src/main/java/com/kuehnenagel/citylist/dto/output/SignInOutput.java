package com.kuehnenagel.citylist.dto.output;

import java.io.Serial;
import java.io.Serializable;

public record SignInOutput(String accessToken) implements Serializable {
    @Serial
    private static final long serialVersionUID = 9127839480453594908L;
}

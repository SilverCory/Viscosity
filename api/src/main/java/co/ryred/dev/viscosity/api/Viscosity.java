package co.ryred.dev.viscosity.api;

import co.ryred.dev.viscosity.api.frame.FrameBus;
import lombok.Getter;
import lombok.Setter;

import java.util.logging.Logger;

public abstract class Viscosity {

    @Getter
    @Setter
    private static Viscosity API;

    @Getter
    private final FrameBus frameBus;

    @Getter
    private final Logger logger;

    @Getter
    private final String authToken;

    public Viscosity(Logger logger, String authToken) {
        this.logger = logger;
        this.frameBus = new FrameBus(logger);
        this.authToken = authToken;
    }

}

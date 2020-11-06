package co.ryred.dev.viscosity.bukkit;

import co.ryred.dev.viscosity.api.Viscosity;

import java.util.logging.Logger;

public class ViscosityBukkit extends Viscosity {
    public ViscosityBukkit(Logger logger, String authToken) {
        super(logger, authToken);
        Viscosity.setAPI(this);
    }

    // TODO.
}

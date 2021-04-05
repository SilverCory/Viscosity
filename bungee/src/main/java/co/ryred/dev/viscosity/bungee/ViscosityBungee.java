package co.ryred.dev.viscosity.bungee;

import co.ryred.dev.viscosity.api.IAuthTokenSupplier;
import co.ryred.dev.viscosity.api.Viscosity;

import java.util.logging.Logger;

public class ViscosityBungee extends Viscosity {
    public ViscosityBungee(Logger logger, IAuthTokenSupplier supplier) {
        super(logger, supplier);
        Viscosity.setAPI(this);
    }
}

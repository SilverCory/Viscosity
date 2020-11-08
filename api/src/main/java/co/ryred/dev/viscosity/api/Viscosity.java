package co.ryred.dev.viscosity.api;

import co.ryred.dev.viscosity.api.connection.ConnectionManager;
import co.ryred.dev.viscosity.api.frame.FrameBus;
import lombok.Getter;
import lombok.Setter;

import java.io.Closeable;
import java.util.logging.Logger;

public abstract class Viscosity implements Closeable {

    @Getter
    @Setter
    private static Viscosity API;

    @Getter
    private final FrameBus frameBus;

    @Getter
    private final Logger logger;

    @Getter
    private final IAuthTokenSupplier authToken;

    @Getter
    private final ConnectionManager connectionManager;

    public Viscosity(Logger logger, IAuthTokenSupplier authToken) {
        this.logger = logger;
        this.frameBus = new FrameBus(logger);
        this.authToken = authToken;
        this.connectionManager = new ConnectionManager(this);
    }

    @Override
    public void close() {
        this.connectionManager.close();
    }
}

package co.ryred.dev.viscosity.api.frame;

import com.google.gson.JsonElement;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Frame {
    private final String identifier;
    private final JsonElement contents;
}

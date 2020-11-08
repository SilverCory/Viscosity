package co.ryred.dev.viscosity.api.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GSONUtils {

    private static Gson GSON;

    public static Gson getGSON() {
        return GSON != null ? GSON : new GsonBuilder().
                enableComplexMapKeySerialization().
                registerTypeHierarchyAdapter(byte[].class, new ByteArrayToBase64TypeAdapter()).
                serializeNulls().
                create();
    }
}

package co.ryred.dev.viscosity.api.gson;

import com.google.gson.*;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;

import java.lang.reflect.Type;
import java.nio.charset.Charset;

public class ByteArrayToBase64TypeAdapter implements JsonSerializer<byte[]>, JsonDeserializer<byte[]> {
    public byte[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Base64.decode(
                Unpooled.wrappedBuffer(
                        json.getAsString().getBytes(Charset.defaultCharset())
                )
        ).array();
    }

    public JsonElement serialize(byte[] src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(
                Base64.encode(Unpooled.wrappedBuffer(src)).toString(Charset.defaultCharset())
        );
    }
}
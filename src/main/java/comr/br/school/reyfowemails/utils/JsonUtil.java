package comr.br.school.reyfowemails.utils;

import com.google.gson.Gson;

public class JsonUtil {

    private static final Gson gson = new Gson();

    public static <T> T toObject(final String payload, final Class<T> valueType) {
        return gson.fromJson(payload, valueType);
    }

    public static String toJson(final Object object) {
        return gson.toJson(object);
    }


}

package CarpetTCTCAddition.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class CarpetTCTCAdditionTranslations
{
    public static Map<String, String> getTranslationFromResourcePath(String lang)
    {
        String dataJSON;
        try
        {
            dataJSON = IOUtils.toString(
                    Objects.requireNonNull(CarpetTCTCAdditionTranslations.class.getClassLoader().getResourceAsStream(String.format("assets/CarpetTCTCAddition/lang/%s.json", lang))),
                    StandardCharsets.UTF_8);
        }
        catch (NullPointerException | IOException e)
        {
            return null;
        }
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        return gson.fromJson(dataJSON, new TypeToken<Map<String, String>>() {}.getType());
    }
}

/*
 * Copyright (c) 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class CarpetTCTCAdditionTranslations {
    public static Map<String, String> getTranslationFromResourcePath(String lang)
    {
        String dataJSON;
        try
        {
            dataJSON = IOUtils.toString(
                    Objects.requireNonNull(CarpetTCTCAdditionTranslations.class.getClassLoader().getResourceAsStream(String.format("assets/carpet-tctc-addition/lang/%s.json", lang))),
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

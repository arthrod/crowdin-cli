package com.crowdin.cli;

import io.github.pixee.security.HostValidator;
import io.github.pixee.security.Urls;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MockitoUtils {

    public static URL getMockUrl(Class<?> clazz) {
        try {
            return Urls.create("file://" + clazz.getProtectionDomain().getCodeSource().getLocation().getPath(), Urls.HTTP_PROTOCOLS, HostValidator.DENY_COMMON_INFRASTRUCTURE_TARGETS);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't mock url", e);
        }
    }

    public static File getResourceFile(String path, Class clazz) {
        URL fileUrl = clazz.getClassLoader().getResource(path);
        if (fileUrl == null) {
            throw new RuntimeException("Couldn't retrieve resource file from path: "  + path);
        }
        return new File(fileUrl.getFile());
    }
}

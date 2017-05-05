package customSerializer;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;


public class ConfigFieldModule extends SimpleModule {
    private static final String NAME = "ConfigFieldModule";
    private static final VersionUtil VERSION_UTIL = new VersionUtil() {};

    public ConfigFieldModule() {
        super(NAME, VERSION_UTIL.version());
        addSerializer(Object.class, new CustomSerializerV1());
    }
}

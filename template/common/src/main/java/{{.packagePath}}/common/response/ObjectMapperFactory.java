package {{ .package }}.common.response;

import {{ .package }}.common.response.MyDateFormat;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.math.BigDecimal;

public class ObjectMapperFactory {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ObjectMapperFactory() {
    }

    public static ObjectMapper getDefaultObjectMapper() {
        return objectMapper.copy();
    }

    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).configure(Feature.ALLOW_SINGLE_QUOTES, true);
        SimpleModule bigDecimalModule = new SimpleModule();
        bigDecimalModule.addSerializer(BigDecimal.class, new ToStringSerializer());
        objectMapper.registerModule(bigDecimalModule);
        objectMapper.setDateFormat(new MyDateFormat());
    }
}
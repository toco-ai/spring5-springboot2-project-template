package {{ .package }}.common.response;

import java.lang.ThreadLocal;

public class ResponseMessageHolder {
    private static final ThreadLocal<String> MESSAGE_HOLDER = new InheritableThreadLocal<>();

    public static void setMessage(String message) {
        MESSAGE_HOLDER.set(message);
    }

    public static String getMessage() {
        return MESSAGE_HOLDER.get();
    }

    public static void clear() {
        MESSAGE_HOLDER.remove();
    }

    private static final ThreadLocal<Integer> CODE_HOLDER = new InheritableThreadLocal<>();

    public static void setCode(Integer code) {
        CODE_HOLDER.set(code);
    }

    public static Integer getCode() {
        Integer code = CODE_HOLDER.get();
        return code == null ? 200 : code;
    }

    public static void clearCode() {
        CODE_HOLDER.remove();
    }

    public static void setCodeAndMessage(Integer code, String message) {
        setCode(code);
        setMessage(message);
    }

    public static void clearAll() {
        MESSAGE_HOLDER.remove();
        CODE_HOLDER.remove();
    }
}
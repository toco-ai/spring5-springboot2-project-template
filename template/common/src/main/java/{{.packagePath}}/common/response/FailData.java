package {{ .package }}.common.response;

public class FailData {
    private int code = 500;
    private String message;

    public FailData() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

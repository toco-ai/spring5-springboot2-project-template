package {{ .package }}.common.response;

public class SuccessData {
    private int code = 200;
    private Object data;
    private String message;

    public SuccessData() {
    }

    public SuccessData(Object data){
        this.data = data;
    }

    public SuccessData(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public SuccessData(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
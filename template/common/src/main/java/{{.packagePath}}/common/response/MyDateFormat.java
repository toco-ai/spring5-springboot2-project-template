package {{ .package }}.common.response;

import java.text.*;
import java.util.Calendar;
import java.util.Date;

public class MyDateFormat extends DateFormat {
    private static final long serialVersionUID = -4580955831439573829L;
    private DateFormat dateFormat;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public MyDateFormat() {
        this.calendar = Calendar.getInstance();
        this.dateFormat = simpleDateFormat;
    }


    public MyDateFormat(DateFormat dateFormat) {
        this.calendar = Calendar.getInstance();
        this.dateFormat = dateFormat;
    }

    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return this.dateFormat.format(date, toAppendTo, fieldPosition);
    }

    public Date parse(String source, ParsePosition pos) {
        Date date = null;
        if (source.length() == "yyyy-MM-dd HH:mm:ss".length()) {
            date = this.simpleDateFormat.parse(source, pos);
        } else {
            date = this.dateFormat.parse(source, pos);
        }

        return date;
    }

    public Date parse(String source) throws ParseException {
        Date date = null;
        if (source.length() == "yyyy-MM-dd HH:mm:ss".length()) {
            date = this.simpleDateFormat.parse(source);
        } else {
            date = this.dateFormat.parse(source);
        }

        return date;
    }

    public Object clone() {
        Object format = this.dateFormat.clone();
        return new MyDateFormat((DateFormat)format);
    }
}
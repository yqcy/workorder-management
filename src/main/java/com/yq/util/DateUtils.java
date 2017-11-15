package com.yq.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * created by YQ on 2017-11-08
 */
@Component
public class DateUtils {

    private Map<String, DateFormat> dateFormatMap;

    @PostConstruct
    public void init() {
        this.dateFormatMap = new HashMap<String, DateFormat>();
        String format1 = "yyyy-MM-dd HH:mm:ss";
        String format2 = "yyyy-MM-dd";
        this.dateFormatMap.put(format1, new SimpleDateFormat(format1));
        this.dateFormatMap.put(format2, new SimpleDateFormat(format2));
    }

    public Date currentTime() {
        return new Date();
    }

    public Date parse(String str, String pattern) {
        DateFormat dateFormat = this.dateFormatMap.get(pattern);
        Date result = null;
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(pattern);
            try {
                result = dateFormat.parse(str);
                this.dateFormatMap.put(pattern, dateFormat);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                result = dateFormat.parse(str);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String format(Date date, String pattern) {
        DateFormat dateFormat = this.dateFormatMap.get(pattern);
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(pattern);
            this.dateFormatMap.put(pattern, dateFormat);
        }
        return dateFormat.format(date);
    }
}

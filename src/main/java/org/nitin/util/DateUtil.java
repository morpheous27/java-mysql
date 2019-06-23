package org.nitin.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class DateUtil {

    public static String getFromattedCurrDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("YYYYMMdd");
        return simpleDateFormat.format(new Date());
    }
}

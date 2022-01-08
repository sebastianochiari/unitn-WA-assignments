package it.unitn.disi.webarch.sebac.trivago.ejb.util;

import java.sql.Date;

public class DateUtil {

    public int getDays(Date startDate, Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();
        int days = (int) diff / (1000 * 60 * 60 * 24);
        return days + 1;
    }

}

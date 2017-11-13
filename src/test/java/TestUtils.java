import com.yq.util.DateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * created by wb-yq264139 on 2017/11/13
 */
public class TestUtils {
    @Test
    public void testCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -10);
        Date time = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(time);
        System.out.println(format);
    }

    @Test
    public void testDateUtils() {
        DateUtils dateUtils = new DateUtils();
        dateUtils.init();
        Date parse = dateUtils.parse("1999-10-10", "yyyy-MM-dd");
        System.out.println(parse);
    }
}

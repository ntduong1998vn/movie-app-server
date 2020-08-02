/*
 * @Author by Nguyen Trieu Duong
 * @Email: ntduong1998vn@gmail.com
 */

package ntduong.movieappserver.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateFormatUtil {
    public static String DEFAULT_DATE_TIME = "1998-01-24";

    public LocalDate stringToLocalDate(String date,String format) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate;

        if(StringUtils.isEmpty(date))
            localDate = LocalDate.parse(DEFAULT_DATE_TIME,formatter);
        else
            localDate = LocalDate.parse(date,formatter);

        return localDate;
    }
}

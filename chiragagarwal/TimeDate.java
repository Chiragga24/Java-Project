package chiragagarwal;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class TimeDate{
  public String getCurrentTimeDate() {
    SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
 	dateTime.setTimeZone(TimeZone.getDefault());
 	String TimeDate = dateTime.format(new Date());
  return TimeDate;
}
}

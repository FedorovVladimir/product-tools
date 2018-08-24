import mailtools.MailTools;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SendController {

    public static void main(String[] args) {
        GregorianCalendar gcalendar = new GregorianCalendar();;
        MailTools mailTools = new MailTools();
        boolean isSent = false;
        int minute = gcalendar.get(Calendar.MINUTE);

        while(true) {
            gcalendar = new GregorianCalendar();

            if(minute != gcalendar.get(Calendar.MINUTE)) {
                minute = gcalendar.get(Calendar.MINUTE);
                isSent = false;
            }

            if(minute % 2 == 0 && !isSent) {
                mailTools.sendMsg("/home/dmitry/Documents/testDir/");
                isSent = true;
            }
        }
    }
}

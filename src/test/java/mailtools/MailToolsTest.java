package mailtools;

import static org.junit.Assert.*;

public class MailToolsTest {

    @org.junit.Test
    public void sendMsg() {
        MailTools mailTools = new MailTools();
        mailTools.sendMsg("/home/dmitry/Documents/testDir/");
    }
}
import com.oleg.maincode.InterfaceEntryGenerator;
import com.oleg.maincode.entity.InterfaceEntry;
import org.junit.Assert;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


public class InterfaceEntryGeneratorTest {
    @Test
    public void mainTest() throws UnknownHostException {
        InterfaceEntry testElement1 = prepereTestElement(false, 0, 0, null, "11:22:e9:e5:44:52");
        InterfaceEntry testElement2 = prepereTestElement(true, 102400, 0, InetAddress.getByName("11.11.11.11"), "11:22:e9:e5:44:48");
        InterfaceEntry testElement3 = prepereTestElement(true, 10, 0, InetAddress.getByName("10.10.10.10"), "11:22:e9:e5:44:50");
        String fileLocation = "src/main/resources/config.txt";
        InterfaceEntryGenerator interfaceEntryGenerator = new InterfaceEntryGenerator();
        List<InterfaceEntry> interfaceEntries = interfaceEntryGenerator.parse(fileLocation);
        Assert.assertTrue(interfaceEntries.size() == 3);
        Assert.assertTrue(interfaceEntries.contains(testElement1));
        Assert.assertTrue(interfaceEntries.contains(testElement2));
        Assert.assertTrue(interfaceEntries.contains(testElement3));
    }

    public InterfaceEntry prepereTestElement(boolean state, int speed, int startLineNumber, InetAddress ipAdress, String macAdress) throws UnknownHostException {
        InterfaceEntry testElement = new InterfaceEntry();
        testElement.setState(state);
        testElement.setSpeed(speed);
        testElement.setStartLineNumber(startLineNumber);
        testElement.setIpAddr(ipAdress);
        testElement.setMacAddress(macAdress);
        return testElement;
    }
}


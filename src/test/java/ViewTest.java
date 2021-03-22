import model.LogEntryBuffer;
import org.junit.After;
import org.junit.Test;
import view.LogEntryBufferView;

/**
 * to test View
 */
public class ViewTest {
    /**
     * test model
     */
    LogEntryBuffer d_log=new LogEntryBuffer();


    /**
     * print ok when test is passed
     */
    @After
    public void checked(){
        System.out.println("ok");
    }

    @Test
    public void obsListAttachTest(){
        LogEntryBufferView d_TestView = new LogEntryBufferView(d_log);
        LogEntryBufferView d_TestView1 = new LogEntryBufferView(d_log);
        System.out.println(d_log.getDObserverList());
    }
}

/*package modeltest;

import model.LogEntryBuffer;
import org.junit.After;
import org.junit.Test;

*//**
 * unit test for Play State Test
 *//*
public class LogEntryBufferTest {
    *//**
     * test object
     *//*
    LogEntryBuffer d_LogEntryBuffer = new LogEntryBuffer();

    *//**
     * test log file path
     *//*
    private String d_FilePath = "log/TestLog.txt";

    *//**
     * print ok when test is passed
     *//*
    @After
    public void checked() {
        System.out.println("ok");
    }

    *//**
     * write log to file test, go check file after test
     *//*
    @Test
    public void writeTest() {
        d_LogEntryBuffer.start();
        System.out.println("test msg1 before first update");
        System.out.println("test msg2 before first update");
        d_LogEntryBuffer.updateFile(d_FilePath);
        System.out.println("test msg3 before second update");
        d_LogEntryBuffer.updateFile(d_FilePath);
    }


}*/

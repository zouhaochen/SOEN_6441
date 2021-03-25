package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * record the game progress into log entry buffer
 */
public class LogEntryBuffer extends Observable {
    /**
     * Byte Array OutputStream
     */
    private ByteArrayOutputStream d_Baos;
    /**
     * previous output stream
     */
    private PrintStream d_Previous;
    /**
     * is capturing flag
     */
    private boolean d_Capturing;
    /**
     * log buffer string list
     */
    private List<String> d_LogBuffer;

    /**
     * file path
     */
    private String d_FilePath = "log/Log.txt";

    /**
     * Constructor
     */
    public LogEntryBuffer() {
        d_LogBuffer = new ArrayList<>();
    }

    /**
     * capturing console ouput start
     */
    public void start() {
        if (d_Capturing) {
            return;
        }

        d_Capturing = true;
        d_Previous = System.out;
        d_Baos = new ByteArrayOutputStream();

        OutputStream outputStreamCombiner =
                new OutputStreamCombiner(Arrays.asList(d_Previous, d_Baos));
        PrintStream custom = new PrintStream(outputStreamCombiner);

        System.setOut(custom);
    }

    /**
     * @return string that captured from console
     */
    public String stop() {
        if (!d_Capturing) {
            return "";
        }

        System.setOut(d_Previous);

        String capturedValue = d_Baos.toString();

        d_Baos = null;
        d_Previous = null;
        d_Capturing = false;
        d_LogBuffer.add(capturedValue);
        return capturedValue;
    }

    /**
     * when controller call log entry buffer to update file
     */
    public void updateFile() {
        // stop capturing and flush buffer to file
        String l_CapturedValue = stop();
        PrintWriter l_Out = null;
        try {
            System.out.println("writing...");
            l_Out = new PrintWriter(new BufferedWriter(new FileWriter(d_FilePath, true)));
            l_Out.println(l_CapturedValue);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (l_Out != null) {
                l_Out.close();
            }
        }
        //notify obs
        notifyLogEntryBufferObs(this);
        // after update the data to file, recapture the console output
        start();
    }

    /**
     * when controller call log entry buffer to update file
     *
     * @param p_FilePath need file path to output
     */
    public void updateFile(String p_FilePath) {
        // stop capturing and flush buffer to file
        String l_CapturedValue = stop();
        PrintWriter l_Out = null;
        try {
            l_Out = new PrintWriter(new BufferedWriter(new FileWriter(p_FilePath, true)));
            l_Out.println(l_CapturedValue);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (l_Out != null) {
                l_Out.close();
            }
        }
        // after update the data to file, recapture the console output
        start();
    }


    /**
     * private class combine two string
     */
    private static class OutputStreamCombiner extends OutputStream {
        private List<OutputStream> d_OutputStreams;

        /**
         * constructor
         *
         * @param p_OutputStreams current java output stream
         */
        public OutputStreamCombiner(List<OutputStream> p_OutputStreams) {
            this.d_OutputStreams = p_OutputStreams;
        }

        /**
         * write to output stream
         *
         * @param b int b
         * @throws IOException if I/O error occurs
         */
        public void write(int b) throws IOException {
            for (OutputStream os : d_OutputStreams) {
                os.write(b);
            }
        }

        /**
         * flush the stream
         *
         * @throws IOException if I/O error occurs
         */
        public void flush() throws IOException {
            for (OutputStream os : d_OutputStreams) {
                os.flush();
            }
        }

        /**
         * stream close
         *
         * @throws IOException if I/O error occurs
         */
        public void close() throws IOException {
            for (OutputStream os : d_OutputStreams) {
                os.close();
            }
        }
    }
}
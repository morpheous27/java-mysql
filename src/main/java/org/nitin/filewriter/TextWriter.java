package org.nitin.filewriter;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class TextWriter<T> {

    private RandomAccessFile stream;
    private FileChannel inChannel;

    public void initializeChannel() throws FileNotFoundException {
        stream = new RandomAccessFile("reports" + Date.from(Instant.now()).getDate(), "rw");
        inChannel = stream.getChannel();
    }

    public void closeChannel() throws IOException {
        inChannel.close();
        stream.close();
    }

    public void writeDataToFile(List<T> data) throws IOException {
        initializeChannel();

        if (!CollectionUtils.isEmpty(data)) {
            data.stream().forEach(i -> writeData(i));
        }
        closeChannel();

    }

    private void writeData(T data) {
        byte[] strBytes = data.toString().getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
        buffer.put(strBytes);
        buffer.flip();
    }

    public void writeDataUsingFiles(List<T> data) {
        try (FileWriter fw = new FileWriter("reports" + Date.from(Instant.now()).getMinutes() + ".txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            data.stream().forEach(i -> out.println(i));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package de.lcraft.lapi.javaUtils;

import de.lcraft.lapi.javaUtils.api.queueWorker.Producer;
import de.lcraft.lapi.javaUtils.format.*;
import de.lcraft.lapi.javaUtils.queueWorker.*;
import de.lcraft.lapi.javaUtils.*;
import de.lcraft.lapi.javaUtils.queueWorker.Process;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class JavaUtilsTest {

    @Test
    public void createAndUseFormat() {
        Format format = new Format(16, 9, 1920, 720);
        format.getFormat();
        format.getFormatX();
        format.getFormatY();
        format.getWidth();
        format.getHeight();

        format = new Format(1920, 720);
        format.getFormat();
        format.getFormatX();
        format.getFormatY();
        format.getWidth();
        format.getHeight();
    }

    @Test
    public void useFormatUtils() {
        FormatUtils.getAllDimensionsFromFormat(FormatUtils.getFormatInt(1920, 720), new Dimension(0, 0), new Dimension(1920, 720));
    }

    @Test
    public void createProcess() {
        de.lcraft.lapi.javaUtils.api.queueWorker.Process<String> process = new Process<>(s -> {});
        process.getProcessID();
        process.getConsumer();
    }

    @Test
    public void testProcessQueue() throws InterruptedException {
        de.lcraft.lapi.javaUtils.api.queueWorker.ProcessQueue processQueue = new ProcessQueue(200);
        processQueue.add(new Process<>(s -> {}));
        processQueue.add(s -> {});
        processQueue.getAndRemoveCurrentProcess();
        processQueue.getRawProcessQueue();
        processQueue.getMaxOverflowSize();
    }

    @Test
    public void testProcessQueueManager() {
        ProcessQueueManager processQueueManager = new ProcessQueueManager(new ProcessQueue(200));
        processQueueManager.addProducer(null);
        processQueueManager.addConsumer(null);
        processQueueManager.startProducer();
        processQueueManager.stopProducer();
        processQueueManager.startConsumer();
        processQueueManager.stopConsumer();
        processQueueManager.startConsumer();
        processQueueManager.stopConsumerOnFinish();
    }

    @Test
    public void testFileUtils() throws IOException {
        File folder = new File("tests/fileUtils");
        folder.mkdirs();
        new File(folder, "file1.yml").createNewFile();
        new File(folder, "file2.yml").createNewFile();
        new File(folder, "file3.txt").createNewFile();
        new File(folder, "file4.mp3").createNewFile();
        new File(folder, "file5.mp4").createNewFile();

        List<File> children = FileUtils.getAllFilesFromFolder(folder);
        FileUtils.getAllLinesFromFile(children.getLast());
    }

    @Test
    public void testInternetUtils() {
        InternetUtils.isConnectionSucceedToWebsite("google.com");
        InternetUtils.getHighestConnectionSucceedFromWebsite(new String[]{"google.com", "youtube.com"});
        InternetUtils.hasInternet();
    }

    @Test
    public void testListArrayUtils() {
        ListArrayUtils.containsFromStringArray(new String[]{"a", "n"}, "a");
        ListArrayUtils.containsFromStringArray(new String[]{"a", "n"}, "b");
        List<String> txt = ListArrayUtils.makeStringArrayToList("Hello.", " You found ", "an easter egg");
        ListArrayUtils.makeStringListToArray(txt);
    }

    @Test
    public void testNumberUtils() {
        NumberUtils.getRandom(1, 5);
        NumberUtils.isInteger(5);
    }

    @Test
    public void testStringUtils() {
        StringUtils.replaceLast("Hello", "o", "");
        StringUtils.lengthAllLowerCaseLetters("Hello World");
        StringUtils.lengthAllCaseLetters("Hello World");
        StringUtils.lengthAllUpperCaseLetters("Hello World");
        StringUtils.lengthAllSpaces("Hello World");
    }

    @Test
    public void testXPSCounter() {
        XPSCounter xpsCounter = new XPSCounter(6000L);
        xpsCounter.nextFrame();
        xpsCounter.getAverageFrameTime_ns();
        xpsCounter.getAverageFrameTime_micros();
        xpsCounter.getAverageFrameTime_ms();
        xpsCounter.getAverageFrameTime_sec();
        xpsCounter.getAverageFPS();
        xpsCounter.getLastFrameTime();
        xpsCounter.getFrameTimes();
        xpsCounter.getMaxNanoSeconds();
    }

}

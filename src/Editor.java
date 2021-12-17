import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Editor {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss,SSS");

    public static SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public List<Sub> getSubList(String path) throws IOException {

        List<Sub> subList = new ArrayList<>();

        Charset charset = detectCharset(path);
        BufferedReader reader = new BufferedReader(new FileReader(path, charset));

        boolean fileEnd = false;

        while (true) {

            String line = reader.readLine();
            if (line == null || line.isEmpty()) break;
            //  int index = Integer.parseInt(line);

            String time = reader.readLine();
            Date from = getFrom(time);
            Date to = getTo(time);
            String content = "";

            while (true) {
                line = reader.readLine();
                if (line == null) {
                    fileEnd = true;
                    break;
                } else if (line.isEmpty()) {
                    break;
                } else {
                    content += line;
                }
            }

            subList.add(new Sub(from, to, content));

            if (fileEnd) break;
        }

        return subList;
    }

    private Charset detectCharset(String path) {

        File f = new File(path);

        String[] charsetsToBeTested = {"UTF-8", "UTF-16"};

        CharsetDetector detector = new CharsetDetector();
        return detector.detectCharset(f, charsetsToBeTested);
    }

    private Date getFrom(String time) {

        String strFrom = time.substring(0, 12);
        Date from = null;
        try {
            from = dateFormat.parse(strFrom);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return from;
    }

    private Date getTo(String time) {

        String strTo = time.substring(17);
        Date to = null;
        try {
            to = dateFormat.parse(strTo);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return to;
    }

    public List<Sub> shift(long duration, List<Sub> subList) {

        List<Sub> newSubList = new ArrayList<>();

        long minTime = 0;
        try {
            minTime = dateFormat.parse("00:00:00,000").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (Sub sub : subList) {
            long newFromAsMillisecond = sub.getFrom().getTime() + duration;
            if (newFromAsMillisecond < minTime) {
                continue;
            }
            long newToAsMillisecond = sub.getTo().getTime() + duration;

            newSubList.add(new Sub(
                    new Date(newFromAsMillisecond),
                    new Date(newToAsMillisecond),
                    sub.getContent()));
        }

        return newSubList;
    }
}


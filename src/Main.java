import java.io.*;
import java.lang.ref.WeakReference;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        String path = "C:\\Users\\truon\\Desktop\\Scary.Movie.2.2001.m720p.BluRay.AAC.x264-2247.srt";

        Editor editor = new Editor();

        List<Sub> result = editor.shift(-4000, editor.getSubList(path));

        StringBuilder builder = new StringBuilder();

        String str = "%d\n%s --> %s\n%s\n\n";
        for(int i = 0; i < result.size(); i++) {
            int index = i + 1;
            String from = Editor.getDateFormat().format(result.get(i).getFrom());
            String to = Editor.getDateFormat().format(result.get(i).getTo());
            String content = result.get(i).getContent();

            builder.append(String.format(str, index, from, to, content));
        }

        String saveTo = "C:\\Users\\truon\\Desktop\\new.srt";
        File file = new File(saveTo);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_16));
        writer.write(builder.toString());
        writer.close();
    }
}

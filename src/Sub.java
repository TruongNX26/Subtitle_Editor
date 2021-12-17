import java.util.Date;

public class Sub {
    private Date from;
    private Date to;
    private String content;

    public Sub(Date from, Date to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Sub{" +
                "from=" + from +
                ", to=" + to +
                ", content='" + content + '\'' +
                '}';
    }
}

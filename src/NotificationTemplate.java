import java.util.HashMap;
import java.util.Map;

public class NotificationTemplate {
    private String type;
    private int id;
    private Map<Integer, String> contents = new HashMap<>();

    public void setTemplate(String type, String content, int id){
        this.type = type;
        this.id = id;
        this.contents.put(id, content);
    }

    public String getType(){
        return type;
    }

    public int getID(){
        return id;
    }

    public Map<Integer, String> getContent(){
        return contents;
    }
}

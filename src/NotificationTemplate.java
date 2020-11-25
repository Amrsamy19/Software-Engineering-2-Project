public class NotificationTemplate {
    private String type;
    private String content;

    public void setTemplate(String type, String content){
        this.type = type;
        this.content = content;
    }

    public String getType(){
        return type;
    }

    public String getContent(){
        return content;
    }
}

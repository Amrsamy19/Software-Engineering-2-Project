import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotificationsOperations {
    public void createTemplate(NotificationTemplate template) {
        File file = new File(template.getType() + ".txt");
        FileWriter myWriter;
        try {
            if(file.exists()){
                myWriter = new FileWriter(file, true);
                myWriter.append(template.getContent());
            } else {
                myWriter = new FileWriter(file, false);
                myWriter.write(template.getContent());
                myWriter.write("\n\n");
            }
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readTemplate(){

    } //TODO

    public void updateTemplate(String type){
        File fileName = new File(type + ".txt");
        Scanner contentReader = new Scanner(System.in);

        if(fileName.exists()){
            System.out.println("Enter the new content: ");
            contentReader.useDelimiter("\\t");
            String content = "";
            while(true){
                content += contentReader.next();
                break;
            }
            Main.template.setTemplate(type, content);
            createTemplate(Main.template);
            System.out.println("Content has been updated successfully");
        }
        else
            System.out.println("Template " + type + " is not found");
    }

    public void deleteTemplate(){

    } //TODO
}

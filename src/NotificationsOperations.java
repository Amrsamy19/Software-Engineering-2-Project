import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotificationsOperations {
    //Creates new file for each type of the templates
    public void createTemplate(NotificationTemplate template) {
        File file = new File(template.getType() + ".txt");
        FileWriter myWriter;
        try {
            if(file.exists()){
                myWriter = new FileWriter(file, true);
                myWriter.append(template.getContent());
                System.out.println("The new template was added to an existed file called " + template.getType());
            } else {
                myWriter = new FileWriter(file, false);
                myWriter.write(template.getContent());
                myWriter.write("\n\n");
                System.out.println("The new template was created in a file called " + template.getType());
            }
            myWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Reads the templates which is written in the file depends on the given type
    public void readTemplate(String type) {
         File fileName = new File(type +".txt");

        try {
            Scanner myReader = new Scanner(fileName);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Template is not found\n");
        }
    }

    //Updates the content of the template depends on the given type
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
            System.out.println("Content has been updated successfully\n");
        }
        else
            System.out.println("Template " + type + " is not found\n");
    }

    //Delete the template depends on the given type
    public void deleteTemplate(String type){
        File file = new File(type + ".txt");
        if(file.isFile()){
            if(file.exists()){
                file.delete();
                System.out.println("Template is deleted successfully\n");
            }
            else
                System.out.println("Template is not found\n");
        }
    }
}

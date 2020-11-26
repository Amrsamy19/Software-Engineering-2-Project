import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> templatesNames = new ArrayList<>();
    public static NotificationTemplate template = new NotificationTemplate();
    public static NotificationsOperations templateOperation = new NotificationsOperations();

    public static void readFileNames(){
        File file = new File(System.getProperty("user.dir"));
        FilenameFilter fileFilter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                return name.endsWith(".txt");
            }
        };

        File[] templateNames = file.listFiles(fileFilter);
        for(int i = 0; i < templateNames.length; i++)
            templatesNames.add(templateNames[i].getName().replace(".txt", ""));
    }

    public static void operationsExecutor() throws IOException{
        while(true){
            Scanner reader = new Scanner(System.in);
            System.out.println("Choose an option: \n" +
                    "[1] Create a template\n" +
                    "[2] Read a template\n" +
                    "[3] Update a template\n" +
                    "[4] Delete a template\n" +
                    "[5] Exit");
            String choice = reader.nextLine();
            switch(choice){
                case "1":{
                    System.out.println("Enter type of the template: ");
                    String type = reader.nextLine();
                    System.out.println("Enter content of the template: ");
                    reader.useDelimiter("\\t");
                    String content = "";
                    while(true){
                        content += reader.next();
                        break;
                    }
                    template.setTemplate(type, content);
                    templateOperation.createTemplate(template);
                    System.out.println("Template created successfully");
                    break;
                }
               case "2":{ 
                   System.out.println("Enter type of the template: ");
                    String type = reader.nextLine();
                    for(int i = 0; i < templatesNames.size(); i++){
                        if(templatesNames.get(i).equals(type))
                        {
                            templateOperation.readTemplate(templatesNames.get(i));
                        }
                        else
                             System.out.println("The template is not found");
                        
                    }
                    break;
                }
                case "3":{
                    System.out.println("Enter type of the template: ");
                    String type = reader.nextLine();
                    for(int i = 0; i < templatesNames.size(); i++){
                        if(templatesNames.get(i).equals(type)){
                            templateOperation.updateTemplate(templatesNames.get(i));
                        }
                        else
                            System.out.println("The template is not found");
                    }
                    break;
                }
//                case "4":{ //TODO
//
//                }
                case "5":{
                    return;
                }
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        readFileNames();
        operationsExecutor();
    }
}

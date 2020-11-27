import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class NotificationsOperations {
    //Creates new file for each type of the templates
    public void createTemplate(NotificationTemplate template) {
        File file = new File(template.getType() + ".txt");
        FileWriter myWriter = null;
        try{
            if (file.exists()) {
                myWriter = new FileWriter(file, true);
                for (Map.Entry<Integer, String> entry : template.getContent().entrySet()) {
                    myWriter.append(entry.getKey() + ":" + entry.getValue());
                }
            } else {
                myWriter = new FileWriter(file, false);
                for (Map.Entry<Integer, String> entry : template.getContent().entrySet()) {
                    myWriter.write(entry.getKey() + ":" + entry.getValue());
                    myWriter.write("\n");
                }
            }
            myWriter.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Reads the templates that are already written in the file
    public static void readFromFile(String type) throws IOException {
        File fileName = new File(type + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        List<String> Parts = new ArrayList<>();
        String line;
        String content = "";
        int key = 0;
        while ((line = reader.readLine()) != null) {
            if (line.contains(":")){
                String[] contentParts = line.split("\\:", 2);
                for(String text : contentParts) {
                    Parts.add(text);
                }
                content = "";
                key = Integer.parseInt(Parts.get(0));
                content += Parts.get(1) + "\n";
                Main.map.put(key, content);
            } else {
                content += line + "\n";
                Main.map.put(key, content);
            }
            Parts.clear();
        }
    }

    //Reads the templates which is written in the file depends on the given type
    public void readTemplate(String type) throws IOException {
        Scanner myReader = new Scanner(System.in);
        System.out.println("Enter the id of the template: ");
        int id = Integer.parseInt(myReader.nextLine());

        readFromFile(type);

        if(Main.map.containsKey(id))
            System.out.println(Main.map.get(id));
        else
            System.out.println("ID is not found");
    }

    //Update specific template
    public void updateSpecificData(String type, int id) throws IOException {
        File file = new File(type + ".txt");
        BufferedWriter myReader = null;
        if (Main.map.containsKey(id)) {
            if (file.exists()) {
                myReader = new BufferedWriter(new FileWriter(file, false));
                for (Map.Entry<Integer, String> entry : Main.map.entrySet()) {
                    myReader.write(entry.getKey() + ":" + entry.getValue());
                    myReader.newLine();
                }
            }
        }
        myReader.flush();
    }

    //Updates the content of the template depends on the given type
    public void updateTemplate(String type) throws IOException {
        File fileName = new File(type + ".txt");
        Scanner myReader = new Scanner(System.in);

        System.out.println("Enter the id of the template: ");
        int id = Integer.parseInt(myReader.nextLine());

        if(fileName.exists()){
            if(Main.map.containsKey(id)){
                System.out.println("Enter the new content: ");
                myReader.useDelimiter("\\t");
                String content = "";
                content += myReader.next();
                Main.map.clear();
                readFromFile(type);
                Main.map.replace(id, content);
                updateSpecificData(type, id);
                System.out.println("Content has been updated successfully\n");
            }
            else
                System.out.println("ID is already used\n");
        }
        else
            System.out.println("Template " + type + " is not found\n");
    }

    //Delete the template depends on the given type
    public void deleteTemplate(String type) {
        File file = new File(type + ".txt");
        try {
            if (file.isFile()) {
                if (file.exists()) {
                    if(file.delete())
                        System.out.println("Templates are deleted successfully\n");
                    else
                        System.out.println("Template not found");
                }
            }
        } catch (Exception e){
            System.out.println("Something bad happened");
        }
    }
}

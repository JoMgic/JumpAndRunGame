import java.util.Iterator;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;


public class LevelLoader
{
    private static String levelDir = "./level";
    private LevelLoader(){}
    protected static BorderController loadLevel(int i, BorderController bc){
        if(!new File(levelDir + "/level"+i+".txt").exists()) {
            System.out.println("Herzlichen Glückwunsch du hast das Spiel durchgespielt!");
            return new BorderController();
        }
        System.out.println("Loading Level " +i);
        Iterator<String> it = readFile(levelDir + "/level"+i+".txt");
        bc = new BorderController();
        while(it.hasNext()){
            bc.add(stringToPlatform(it.next()));
        }
        return bc;
    }
    public static void exec(){
        
        //String name = htmlEncoder("name", cmd);
        //value = htmlEncoder("value", cmd);
        
        
        try {
            Method methode = LevelLoader.class.getMethod("sayHello");
            methode.invoke(new LevelLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("EXECUTION");
    }
    public static void sayHello(){
        System.out.println("hello guys");
    }
    private static Platform stringToPlatform(String s){
        return new Platform(Integer.parseInt(htmlEncoder("x", s)), Integer.parseInt(htmlEncoder("y", s)), Integer.parseInt(htmlEncoder("width", s)), Integer.parseInt(htmlEncoder("height", s)));
    }
    private static String htmlEncoder(String param, String s){
        if(s == null || param == null){
            return "69";
        }
        try{
            String value = s.split("<" + param + ">")[1].split("</" + param + ">")[0];
            return value;
        }catch(Exception e){
            switch(param){
                case "width":
                    return Integer.toString(200);
                case "height":
                    return Integer.toString(20);
                default:
                    return "69";
            }
        }
    }
    public static Iterator<String> readFile(String filename){
        ArrayList<String> arrList = new ArrayList<>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                arrList.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        return arrList.iterator();
    }
}

 
import java.util.Arrays;

public class JohannesList <T>
{
    private T[] array;
    protected int size;
    public JohannesList(){
        size = 0;
        array = (T[]) new Object[size+1];
    }
    public void delete(){
        array = null;
        array = (T[]) new Object[size+1];
    }
    protected void add(T value){
        size++;
        if(size > array.length){
            array = Arrays.copyOf(array, array.length + 1);
        }
        array[size-1] = value;
    }
    protected void add(int index, T value){
        size++;
        if(size > array.length){
            array = Arrays.copyOf(array, array.length + 1);
        }
        for(int i = size; i > index; i--){
            array[i-1] = array[i-2]; 
        }
        array[index] = value;
    }
    protected T get(int i){
        try{
        if(i >= array.length){
            System.out.println("Du Idiot");
            throw new IndexOutOfBoundsException("Idiot am Steuer, das wird teuer(Index out of bounds: " + i + ")");
        }
        return array[i];}catch(Exception e){throw e; }
    }
    protected void print(){
        for(T v : array){
            System.out.println(v);
        }
    }
    public void fill(){
        for(int i = 0; i < 5; i++){
            add((T) Integer.valueOf(i)); // wtf man kann nur Objekte casten. Wer ist Carsten?
        }
    }
    protected T getLast(){
        return array[array.length-1];
    }
    private Integer[] shuffle(Integer[] arr){
        Integer[] sortedArr = new Integer[arr.length];
        java.util.ArrayList <Integer> arrlist = new java.util.ArrayList<Integer>();
        printArray(arr);
        for (int i : arr){
            arrlist.add(i);
        }
        java.util.ArrayList <Integer> shuffled = shuffleArrList(arrlist);
        for (int i = 0; i < arr.length; i++){
            sortedArr[i] = shuffled.get(i);
        }
        return sortedArr;
    }
    private static java.util.ArrayList <Integer> shuffleArrList(java.util.ArrayList<Integer> arrlist){
        java.util.ArrayList <Integer> shuffled = new java.util.ArrayList<Integer>();
        int index = 0;
        boolean firstRun = true;
        for(int i : arrlist){
            if(firstRun){
                shuffled.add(i);
                firstRun = false;
            }else{
                java.util.Random rand = new java.util.Random();
                shuffled.add(index, i);
                index = rand.nextInt(shuffled.size()+1);
            }
        }
        return shuffled;
    }
    public static boolean control(Integer[] num){
        int vorher = Integer.MIN_VALUE; 
        for (int i = 0; i < num.length; i++) {
            if (vorher > num[i]) {
                return false;
            }
            vorher = num[i];
        }
        return true;
    }
    private java.util.ArrayList<Integer> arrayToList(int[] arr){
        java.util.ArrayList arrList = new java.util.ArrayList<>();
        for(int i : arr){
            arrList.add(arr);
        }
        return arrList;
    }
    public static void printArray(int[] num){
        for(int i = 0; i < num.length; i++){
            System.out.print(num[i] + " " );
        }
        System.out.print("; " + num.length);
        System.out.println("");
    }
    private static <T> void printArray (T[] arr){
        for(T i : arr){
            System.out.print(i + " " );
        }
        //System.out.print("; " + arr.length);
        System.out.println("");
    }
    protected void sort(){
        //if(!(array instanceof Integer[])) { System.out.println("Kein Integer"); return;}
        Integer[] arr = Arrays.copyOf(array, size, Integer[].class);
        while(!(control(arr) || encoded)){
            arr = shuffle((Integer[]) arr);
            printArray(arr);
        }
        for (int i = 0; i < size; i++) {
            array[i] = (T) arr[i];
        }
        System.out.println("Ergebnis: " + array);
    }
    public void haveFun(int j){
        delete();
        for(int i = 0; i < j; i++){
            add((T) Integer.valueOf(i));
        }
        Integer[] arr = Arrays.copyOf(array, size, Integer[].class);
        arr = shuffle(arr);
        for (int i = 0; i < size; i++) {
            array[i] = (T) arr[i];
        }
        sort();
    }
    public static boolean encoded = false;
    public void haveBigFun(){
        
        delete();
        for(int i = 0; i < 16; i++){
            add((T) Integer.valueOf(i));
        }
        Integer[] arr = Arrays.copyOf(array, size, Integer[].class);
        arr = shuffle(arr);
        for (int i = 0; i < size; i++) {
            array[i] = (T) arr[i];
        }
        for(int i = 0; i < 10000; i++){
            new Thread(){
                @Override
                public void run(){
                    sort();
                    JohannesList.encoded = true;
                }
            }.start();
        }
    }
    public static void main(String[] args) {
        JohannesList<Integer> jList = new JohannesList<>();
        jList.fill();
        jList.print();
        jList.sort();
        jList.print();
    }
}

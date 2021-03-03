package utilities;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import data.Flat;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Hashtable;

/**
 * Класс загрузки/чтения коллекции из файла
 */
public class FileManager {
    private Gson gson = new Gson();
    private String envVariable;

    public FileManager(String fileName) {
        this.envVariable = fileName;
    }

    /**
     * Запись коллекции в файл
     * @param collection -коллекция, которую нужно записать
     */
    public void writeCollection(Hashtable collection){
        if (System.getenv().get(envVariable) != null) {
        try ( OutputStreamWriter pw = new OutputStreamWriter(new FileOutputStream(System.getenv().get(envVariable)))){
            pw.write(gson.toJson(collection));
            System.out.println("Коллекция успешна сохранена в файл!");
        } catch (Exception e) {
            System.out.println();
        }
        } else System.out.println("Системная переменная с загрузочным файлом не найдена!");
    }

    /**
     *Чтение коллекции из файла
     * @return коллекция, которая была считана из файла
     */
    public Hashtable<Integer, Flat> readCollection()  {
        if (System.getenv().get(envVariable) != null) {
        try (FileReader fileScanner = new FileReader(new File(System.getenv().get(envVariable)))){
            Hashtable<Integer,Flat> hashtable=new Hashtable<Integer,Flat>();
            Type collectionType = new TypeToken<Hashtable<Integer,Flat>>() {}.getType();
            Hashtable<Integer,Flat> collection = gson.fromJson(fileScanner,collectionType);
            System.out.println("\u001B[30m"+"\u001B[33m"+"Коллекция успешна загружена!"+"\u001B[33m"+"\u001B[30m");
            return collection;
        } catch (FileNotFoundException e) {
            System.err.println("Файл с таким именем не найден :(");

        } catch (IOException e) {
            System.err.println("Ошибка ввода-вывода");

        }catch(JsonSyntaxException e){
            System.err.println("Формат файла не удовлетворяет условию");
        }
        }else System.out.println("Системная переменная с загрузочным файлом не найдена!");
        return new Hashtable<>();

    }

    @Override
    public String toString(){
        String string = "FileManager (класс для работы с загрузочным файлом)";
        return string;
    }
}

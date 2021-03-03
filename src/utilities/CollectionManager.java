package utilities;

import exceptions.KeyException;
import data.Flat;
import data.Furnish;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Управляет коллекцией
 */
//работа с коллекцией: создание нового id, добавление элемента, удаление и тд
public class CollectionManager {
    private Hashtable<Integer, Flat> hashtable=new Hashtable<>();
    private FileManager fileManager;
    private LocalDateTime lastInitTime;

    public CollectionManager(FileManager fileManager) {
        this.fileManager=fileManager;
    }

    /**
     * Записывает коллекцию в файл
     */
    public void saveToFile(){
        fileManager.writeCollection(hashtable);
    }

    /**
     * Читает коллекцию из файла
     */
    public void loadCollection() {
        hashtable = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();


    }

    /**
     * Представляет все элементы коллекции в виде строки
     * @return строковое представление коллекции
     */
    public String getStringElements(){
        String strElem="";
        if (hashtable.isEmpty()) return "Коллекция пуста!!!";
        for (Flat f: hashtable.values()){
            strElem+=f.toString()+"\n";
        }
        return strElem;


    }

    /**
     * Чистит коллекцию
     */

    public void clear(){
        hashtable.clear();
    }
    /**
     * Определяет класс коллекции
     * @return имя класса коллекции
     */
    public String collectionType(){
        return hashtable.getClass().getName();

    }
    /**
     * Определяет размер коллекции
     * @return размер коллекции
     */
    public int collectionSize(){
        return hashtable.size();
    }
    public LocalDateTime getLastInitTime(){
        return lastInitTime;
    }

    /**
     * Генерирует ID
     * @return ID
     */
    public Integer newId(){
        if (hashtable.isEmpty()) return 1;
        int lastId = 0;
        for (Flat f : hashtable.values()) {
            lastId = Math.max(lastId, f.getID());
        }
        return lastId + 1;
    }

    /**
     * Добавляет новый элемент в коллекцию
     * @param key ключ
     * @param flat значение
     */
    public void insertNew(Integer key, Flat flat){
        hashtable.put(key,flat);
    }

    /**
     * Находит ключ элемента по его ID
     * @param id id
     * @return ключ
     */
    public Integer getKeyById(Integer id){
        for (Map.Entry<Integer, Flat> e : hashtable.entrySet()) {
            if (e.getValue().getID().equals(id)) return e.getKey();
        }
        return null;
    }

    /**
     * Заменяет элемент по ключу
     * @param key ключ
     * @param flat значение
     */
    public void update(Integer key, Flat flat){
        hashtable.remove(key);
        hashtable.put(key,flat);
    }

    /**
     * Удаляет элемент по ключу
     * @param key ключ
     */
    public void removeKey(int key){
        try{
            if (!hashtable.containsKey(key)) throw new KeyException();
            hashtable.remove(key);
            System.out.println("\u001B[30m"+"\u001B[33m"+"Элемент с ключом "+ key+" успешно удален"+"\u001B[33m"+"\u001B[30m");
        }catch (KeyException e){
            System.err.println("Элемента с таким ключом не существует");
        }
    }

    /**
     * Удаляет все элементы с заданным числом комнат
     * @param number число комнат
     * @return количество удаленных элементов
     */
    public int removeAllByNumber(Integer number){
        List<Integer> a=new ArrayList<>();
        for (Map.Entry<Integer, Flat> e : hashtable.entrySet()) {
            if (e.getValue().getNumberOfRooms().equals(number)) {

                a.add(e.getKey());
            }
        }

        for (Integer i: a){
            hashtable.remove(i);
        }
        return a.size();

    }

    /**
     * Удаляет все элементы с наименьшими ключами
     * @param key ключ
     * @return количество удаленных элементов
     */
    public int removeLowerKey(Integer key){
        List<Integer> a=new ArrayList<>();
        for (Map.Entry<Integer, Flat> e : hashtable.entrySet()) {
            if (e.getKey().intValue()<key.intValue()) {

                a.add(e.getKey());
            }
        }
        for (Integer i: a){
            hashtable.remove(i);
        }
        return a.size();

    }

    /**
     * Находит элементы, имя которых начинается с заданной подстроки
     * @param string подстрока
     *
     */
    public void filterName(String string){
        int i=1;
        for (Map.Entry<Integer, Flat> e : hashtable.entrySet()) {
            if (e.getValue().getName().indexOf(string)==0) {
                System.out.println(e.getValue().toString());
                i=0;
            }
        }
        if (i==1){System.out.println("\u001B[30m"+"\u001B[33m"+"Нет элементов, начинающихся на такую строку"+"\u001B[33m"+"\u001B[30m");}
    }

    /**
     * Заменяет значение по ключу, если оно больше
     * @param key ключ
     * @param flat значение
     */
    public void replaceIfGreater(Integer key,Flat flat){
        try{
            if (hashtable.get(key).compareTo(flat)<0){
                hashtable.put(key,flat);
                System.out.println("\u001B[30m"+"\u001B[33m"+"Квартира с ключом "+key+" была успешно заменена"+"\u001B[33m"+"\u001B[30m");

            }
            else {System.out.println("\u001B[30m"+"\u001B[33m"+"Квартира с ключом "+key+" не была заменена, так как меньше уже существующей"+"\u001B[33m"+"\u001B[30m");
        }}catch(NullPointerException e){
            System.err.println("Элемента с таким ключом не существует");
        }


    }

    /**
     * Проверяет, есть ли в коллекции элемент с данным ключом
     * @param key ключ
     * @throws NullPointerException если ключа нет
     */
    public void checkKey(Integer key){
            if (!hashtable.containsKey(key)) {throw new NullPointerException();}

    }
    /**
     * Проверяет, есть ли в коллекции элемент с данным ключом
     * @param key ключ
     * @throws NullPointerException если ключ есть
     */
    public void Key(Integer key){
        if (hashtable.containsKey(key)) {throw new NullPointerException();}
    }

    /**
     * Проверяет, есть ли в коллекции элемент с таким ID
     * @param id id
     * @throws NullPointerException если нет эл-та с таким ID
     */
    public void checkId(Integer id){
        for (Map.Entry<Integer, Flat> e : hashtable.entrySet()) {
            boolean checker=false;
            if (e.getValue().getID().equals(id)) {
                checker=true;

            }
            if (checker=false) throw new NullPointerException();
        }
    }
    /**
     * Заменяет значение по ключу, если оно меньше
     * @param key ключ
     * @param flat значение
     */
    public void replaceIfLower(Integer key,Flat flat){
        if (hashtable.get(key).compareTo(flat)>0){
                hashtable.put(key,flat);
                System.out.println("\u001B[30m"+"\u001B[33m"+"Квартира с ключом "+key+" была успешно заменена"+"\u001B[33m"+"\u001B[30m");
        }else {System.out.println("\u001B[30m"+"\u001B[33m"+"Квартира с ключом "+key+" не была заменена, так как меньше уже существующей");}

    }

    /**
     * Считает кол-во элементов с определенной отделкой
     * @param furnish определенная отделка
     * @return количество эл-тов
     */
    public int countFurnish(String furnish){
        int count=0;
        for (Map.Entry<Integer, Flat> e : hashtable.entrySet()) {
            if (e.getValue().getFurnish().compareTo(Furnish.valueOf(furnish))>0) {

                count+=1;
            }
        }
        return count;
    }
}

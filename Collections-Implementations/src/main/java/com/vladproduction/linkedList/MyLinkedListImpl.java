package com.vladproduction.linkedList;


public class MyLinkedListImpl implements MyList {
    private int size;
    private Node root; //root element(first node)

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(Object o) {                     //добавление обьекта в LinkedList
        if (root == null) {                             // если первого элемента нет пока
            root = new Node();                      //заводим новый элемент
            root.setValue(o);                       //устанвливаем значение добавляемого элемента
        } else {                                     //иначе если в LinkedList есть элементы
            Node tmp = root;                        //делаем root как временный узел
            while (tmp.getNext() != null) {            // пока за временным есть следующий элемент
                tmp = tmp.getNext();                // идем к следующему до
            }
            Node nextNode = new Node();             //создаем новый элемент
            nextNode.setValue(o);                   //устанавливаем для него значение
            tmp.setNext(nextNode);                  //цепляем к LinkedList добавляемый элемент со значением
        }
        size++;                                     //увеличиваем размер LinkedList на единицу

    }

    @Override
    public Object get(int index) {                  //хотим взять элемент по идексу
        int step = 0;                               //определяем счетчик для шага по элементам
        Node tmp = root;                            // определяем root как временный узел (начало)
        while (tmp.getNext() != null) {                //пока временный узел имеет следующий
            if (step == index) {                    //если номер шага это искомый индекс
                break;                          //выходим из цикла поиска
            }
            tmp = tmp.getNext();                      // устанавливаем временный узел как следующий
            step++;                                 //делаем следующий шаг
        }
        return tmp.getValue();                      //берем значение по индексу
    }

    @Override
    public Object set(int index, Object newObject) {
        int step = 0;
        Node tmp = root;
        while (tmp != null) {
            if (step == index) {
                break;
            }
            tmp = tmp.getNext();
            step++;
        }
        Object currentValue = tmp.getValue();
        tmp.setValue(newObject);
        return currentValue;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public void remove(int index) {
        int step = 0;
        if (index == 0) {
            root = root.getNext();
            size--;
            return;
        }
        if (index == size - 1) {
            Node tmp = root;
            while (tmp.getNext() != null) {
                if (step == index - 1) {
                    break;
                }
                tmp = tmp.getNext();
                step++;
            }
            tmp.setNext(null);
        } else {
            Node tmp = root;
            while (tmp.getNext() != null) {
                if (step == index - 1) {
                    break;
                }
                tmp = tmp.getNext();
                step++;
            }
            Node toDelete = tmp.getNext();
            Node nextAfterDelete = toDelete.getNext();
            tmp.setNext(nextAfterDelete);
        }
        size--;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node tmp = root;
        while (tmp != null) {
            Object value = tmp.getValue();
            sb.append(value);
            sb.append(",");
            tmp = tmp.getNext();
        }
        if (size != 0) {
            int comaPosition = sb.length() - 1;
            sb.deleteCharAt(comaPosition);
        }

        sb.append("]");


        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o==null){
            return false;
        }
        if (o==this){
            return true;
        }
        if(o.getClass()==this.getClass()){
            MyLinkedListImpl otherList = (MyLinkedListImpl) o;
            if(size!=otherList.size){
                return false;
            }
            for (int i = 0; i < size; i++) {
                Object value = get(i);
                Object otherValue = otherList.get(i);
                if(!value.equals(otherValue)){
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        Node tmp = root;
        while (tmp.getNext()!=null){
            Object value = tmp.getValue();
            if(value!=null){
                int hashValue = value.hashCode();
                hashCode=hashCode+hashValue;
            }
            tmp=tmp.getNext();
        }
        return hashCode;
    }
}

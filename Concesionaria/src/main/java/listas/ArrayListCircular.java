
package listas;

/**
 *
 * @author Justin Roldan
 */
public class ArrayListCircular<E> implements List<E>{
    private int effectiveSize = 0;
    private int CAPACITY = 100;
    private E[] elements = null;
    private int currentIndex =0;
    
    public ArrayListCircular(){
        this.elements = (E[]) new Object[CAPACITY];
        
    }
    
    @Override
    public E get(int index) {
        if(index<0 || index>=effectiveSize){
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }
    

    @Override
    public boolean set(int index, E element) {
        if(index<0 || index>=effectiveSize){
            throw new IndexOutOfBoundsException();
        }
        if(element == null){
            throw new NullPointerException();
        }
        elements[index]=element;
        return true;
    }

    @Override
    public boolean add(E element) {
                
        if (element == null){
            throw new NullPointerException();
        }else if(this.isEmpty()){
            elements[effectiveSize++]=element;
            return true;
        }else if(this.isFull()){
            addCapacity();
        }
        elements[effectiveSize] = element;
        effectiveSize++;
        return true;
    }

    @Override
    public void add(int index, E element) {      
        if(element == null){
            throw new NullPointerException();
        }else if (index <0 || index > this.effectiveSize){
            throw new IndexOutOfBoundsException(); 
        }else if (this.isEmpty()){
            elements[effectiveSize++] = element;
        }else if(this.isFull()){
            this.addCapacity();
        }
        for(int i=effectiveSize; i>index; i--){
            elements[i+1] = elements[i];
        }
        elements[index] = element;
        effectiveSize++;
    }

    @Override
    public boolean addFirst(E element) {
        if(element==null){
            throw new NullPointerException();
        }
        if(isFull()){
            addCapacity();
        }
        if(isEmpty()){
            elements[0]=element;
            effectiveSize+=1;
        }
        for(int i=effectiveSize-1;i>=0;i--){
            elements[i+1]=elements[i];
        }
        elements[0]=element;
        effectiveSize+=1;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return effectiveSize==0;
    }

    @Override
    public boolean contains(Object o) {
        if(o==null){
            throw new NullPointerException();
        }
        for(int i=0;i<effectiveSize;i++){
            if(elements[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int index) {
        if(index<0 || index>=effectiveSize || isEmpty()) throw new IndexOutOfBoundsException();
        if(isFull()) addCapacity();
        for(int i=index;i<effectiveSize;i++){
            if(isFull()){
                addCapacity();
            }
            elements[i]=elements[i+1];
        }
        return true;
    }

    @Override
    public boolean removeFirst() {
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        for(int i=0;i<effectiveSize;i++){
            if(isFull()){
                addCapacity();
            }
            elements[i]=elements[i+1];
        }
        effectiveSize-=1;
        return true;
    }

    @Override
    public boolean removeLast() {
        if(isEmpty()){
            throw new IndexOutOfBoundsException();
        }
        if(isFull()){
            addCapacity();
        }
        elements[effectiveSize-1]=elements[effectiveSize];
        effectiveSize-=1;
        return true;
    }
    
    private void addCapacity(){
        E[] temp =(E[])new Object[CAPACITY*2];
        for(int i=0;i<effectiveSize;i++){
            temp[i]=elements[i];
        }
        elements=temp;
        CAPACITY*=2;
    }
    
    private boolean isFull(){
        return effectiveSize==CAPACITY;
    }

    @Override
    public int size() {
        return effectiveSize;
    }

    @Override
    public void clear() {
        effectiveSize=0;
    }
    
    public boolean addAll(List<? extends E> c) {
        if(isFull()){
            addCapacity();
        }
        for (int i = 0; i < c.size(); i++) {
            elements[effectiveSize++] = c.get(i);
        }
        return !c.isEmpty();
    }
    public E getNext() {
        if (effectiveSize == 0) {
            return null;
        }
        currentIndex = (currentIndex + 1) % effectiveSize;
        return elements[currentIndex];
    }

    public E getPrevious() {
        if (effectiveSize == 0) {
            return null;
        }
        currentIndex = (currentIndex - 1 + effectiveSize) % effectiveSize;
        return elements[currentIndex];
    }
}

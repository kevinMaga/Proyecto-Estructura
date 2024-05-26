/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package listas;

/**
 *
 * @author Justin Roldan
 */
public interface List<E> {
    public E get(int index);
    public boolean set(int index,E element);
    public boolean add(E element);
    public void add(int index,E element);
    public boolean addFirst(E element);
    public boolean isEmpty();
    public boolean contains(Object o);
    public boolean remove(int index);
    public boolean removeFirst();
    public boolean removeLast();
    public int size();
}

package com.zakcorp.questions.cache_design.datastructures;

public class DoublyLinkedList<Key, Value> {
    DoublyLinkedListNode<Key, Value> head;
    DoublyLinkedListNode<Key, Value> tail;

    public DoublyLinkedList() {
        head = new DoublyLinkedListNode<>();
        tail = new DoublyLinkedListNode<>();
        head.next = tail;
        tail.prev = head;
    }

    public void unlink(DoublyLinkedListNode<Key, Value> dllNode) {
        DoublyLinkedListNode<Key, Value> temp = dllNode.prev;
        temp.next = dllNode.next;
        dllNode.next.prev = temp;
        dllNode.next = null;
        dllNode.prev = null;
    }

    public void addFirst(DoublyLinkedListNode<Key, Value> dllNode) {
        DoublyLinkedListNode<Key, Value> temp = head.next;
        head.next = dllNode;
        dllNode.next = temp;
        temp.prev = dllNode;
        dllNode.prev = head;
    }

    public DoublyLinkedListNode<Key, Value> removeLast() {
        DoublyLinkedListNode<Key, Value> temp = tail.prev;
        tail.prev = temp.prev;
        tail.prev.next = tail;
        temp.prev = null;
        temp.next = null;
        return temp;
    }
}

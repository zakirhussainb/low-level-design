package com.zakcorp.questions.cache_design.datastructures;

public class DoublyLinkedListNode<Key, Value> {
    DoublyLinkedListNode<Key, Value> next;
    DoublyLinkedListNode<Key, Value> prev;
    private Key key;
    private Value value;

    public DoublyLinkedListNode(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

    public DoublyLinkedListNode() {
    }

    public Key getKey() {
        return this.key;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

}

package com.zakcorp.questions.cache_design.policies.eviction;

import com.zakcorp.questions.cache_design.datastructures.DoublyLinkedList;
import com.zakcorp.questions.cache_design.datastructures.DoublyLinkedListNode;
import com.zakcorp.questions.cache_design.exceptions.CacheKeyNotFoundException;
import com.zakcorp.questions.cache_design.exceptions.StorageFullException;

import java.util.*;

public class LRUEvictionPolicy<Key, Value> implements EvictionPolicy<Key, Value> {

    private final DoublyLinkedList<Key, Value> dll;
    private final Map<Key, DoublyLinkedListNode<Key, Value>> map;
    private final int capacity;

    public LRUEvictionPolicy(int capacity) {
        this.dll = new DoublyLinkedList<>();
        this.map = new HashMap<>();
        this.capacity = capacity;
    }

    @Override
    public Value get(Key key) {
        if(!map.containsKey(key)) {
            throw new CacheKeyNotFoundException(String.valueOf(key));
        }
        DoublyLinkedListNode<Key, Value> dllNode = map.get(key);
        dll.unlink(dllNode);
        dll.addFirst(dllNode);
        return dllNode.getValue();
    }

    @Override
    public void put(Key key, Value value) {
        if(capacity <= map.size() && !map.containsKey(key)) {
            throw new StorageFullException(String.valueOf(key));
        }
        DoublyLinkedListNode<Key, Value> dllNode;
        if(map.containsKey(key)) {
            dllNode = map.get(key);
            dllNode.setValue(value);
            dll.unlink(dllNode);
        } else {
            dllNode = new DoublyLinkedListNode<>(key, value);
            map.put(key, dllNode);
        }
        dll.addFirst(dllNode);
    }

    @Override
    public Key evictKey() {
        DoublyLinkedListNode<Key, Value> tempNode = dll.removeLast();
        Key key = tempNode.getKey();
        map.remove(key);
        return key;
    }
}

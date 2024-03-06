package org.narendra.cache.evictionpolicies;

import org.narendra.algorithms.DoublyLinkedList;
import org.narendra.algorithms.DoublyLinkedListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key> {

    private final DoublyLinkedList<Key> doublyLinkedList;
    private final Map<Key, DoublyLinkedListNode<Key>> map;

    public LRUEvictionPolicy() {
        doublyLinkedList = new DoublyLinkedList<>();
        map = new HashMap<>();
    }
    @Override
    public void keyAccessed(Key key) {
        if(map.containsKey(key)) {
            doublyLinkedList.detachNode(map.get(key));
            doublyLinkedList.addNodeAtLast(map.get(key));
        } else {
            DoublyLinkedListNode<Key> node =  doublyLinkedList.addElementAtLast(key);
            map.put(key, node);
        }
    }

    @Override
    public Key evictKey() {
        DoublyLinkedListNode<Key> first = doublyLinkedList.getFirstNode();
        if(first == null) {
            return null;
        }

        doublyLinkedList.detachNode(first);
        map.remove(first.getElement());
        return first.getElement();
    }
}

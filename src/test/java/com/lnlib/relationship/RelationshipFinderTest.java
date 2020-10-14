package com.lnlib.relationship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing with Integers that are next to the other
 */
class RelationshipFinderTest
{

    private RelationshipFinder<Integer> relationshipFinder;

    @BeforeEach
    void setUp()
    {
        // means that integers are contiguous or same
        relationshipFinder = new RelationshipFinder<>((x, y) -> Math.abs(x - y) <= 1);
    }

    @Test
    void disjoint()
    {
        relationshipFinder.add("1", 1);
        relationshipFinder.add("3", 3);

        assertEquals(Set.of(1), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(3), relationshipFinder.getAllLinks("3"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(2, groups.size());
        assertEquals(Set.of(1), groups.get(0));
        assertEquals(Set.of(3), groups.get(1));

    }

    @Test
    void same()
    {
        relationshipFinder.add("1", 1);
        relationshipFinder.add("1", 1);

        assertEquals(Set.of(1), relationshipFinder.getAllLinks("1"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(1, groups.size());
        assertEquals(Set.of(1), groups.get(0));
    }


    @Test
    void simple()
    {
        relationshipFinder.add("1", 1);
        relationshipFinder.add("2", 2);

        assertEquals(Set.of(1, 2), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(1, 2), relationshipFinder.getAllLinks("2"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(1, groups.size());
        assertEquals(Set.of(1, 2), groups.get(0));
    }


    @Test
    void joined()
    {
        relationshipFinder.add("1", 1);
        relationshipFinder.add("3", 3);
        relationshipFinder.add("2", 2);

        assertEquals(Set.of(1, 2, 3), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(1, 2, 3), relationshipFinder.getAllLinks("2"));
        assertEquals(Set.of(1, 2, 3), relationshipFinder.getAllLinks("3"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(1, groups.size());
        assertEquals(Set.of(1, 3, 2), groups.get(0));
    }

    @Test
    void joinLater()
    {
        relationshipFinder.add("1", 1);
        relationshipFinder.add("2", 2);
        relationshipFinder.add("5", 5);
        relationshipFinder.add("4", 4);

        assertEquals(Set.of(1, 2), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(4, 5), relationshipFinder.getAllLinks("4"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(2, groups.size());
        assertEquals(Set.of(1, 2), groups.get(0));
        assertEquals(Set.of(5, 4), groups.get(1));

        relationshipFinder.add("3", 3);

        assertEquals(Set.of(1, 2, 3, 4, 5), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(1, 2, 3, 4, 5), relationshipFinder.getAllLinks("5"));

        groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(1, groups.size());
        assertEquals(Set.of(1, 2, 5, 4, 3), groups.get(0));

    }

    @Test
    void complex()
    {
        relationshipFinder.add("1", 1);
        relationshipFinder.add("2", 2);
        relationshipFinder.add("5", 5);
        relationshipFinder.add("4", 4);
        relationshipFinder.add("1", 1);
        relationshipFinder.add("10", 10);

        assertEquals(Set.of(1, 2), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(10), relationshipFinder.getAllLinks("10"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(3, groups.size());
        assertEquals(Set.of(1, 2), groups.get(0));
        assertEquals(Set.of(5, 4), groups.get(1));
        assertEquals(Set.of(10), groups.get(2));

        relationshipFinder.add("3", 3);

        assertEquals(Set.of(1, 2, 3, 4, 5), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(10), relationshipFinder.getAllLinks("10"));

        groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(2, groups.size());
        assertEquals(Set.of(10), groups.get(0));
        assertEquals(Set.of(1, 2, 5, 4, 3), groups.get(1));
    }
}
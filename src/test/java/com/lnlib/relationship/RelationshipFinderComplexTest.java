package com.lnlib.relationship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Testing with Squares that are intersecting or touching
 */
class RelationshipFinderComplexTest
{

    private RelationshipFinder<Geometry> relationshipFinder;
    private final WKTReader reader = new WKTReader(new GeometryFactory());

    private final Geometry SQUARE0 = getSquare(0, 0, 2);
    private final Geometry SQUARE1 = getSquare(1, 0, 2);
    private final Geometry SQUARE2 = getSquare(2, 0, 2);
    private final Geometry SQUARE3 = getSquare(3, 0, 2);
    private final Geometry SQUARE4 = getSquare(4, 0, 2);
    private final Geometry SQUARE5 = getSquare(5, 0, 2);
    private final Geometry SQUARE10 = getSquare(10, 0, 2);

    @BeforeEach
    void setUp()
    {
        // is true if there is at least 1 common point
        relationshipFinder = new RelationshipFinder<>(Geometry::intersects);
    }


    /**
     * Testing 2 squares not intersecting
     */
    @Test
    void disjoint()
    {
        relationshipFinder.add("0", SQUARE0);
        relationshipFinder.add("5", SQUARE5);

        assertEquals(Set.of(SQUARE0), relationshipFinder.getAllLinks("0"));
        assertEquals(Set.of(SQUARE5), relationshipFinder.getAllLinks("5"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(2, groups.size());
        assertEquals(Set.of(SQUARE0), groups.get(0));
        assertEquals(Set.of(SQUARE5), groups.get(1));

    }

    /**
     * Testing 2 same squares
     */
    @Test
    void same()
    {
        relationshipFinder.add("0", SQUARE0);
        relationshipFinder.add("0", SQUARE0);

        assertEquals(Set.of(SQUARE0), relationshipFinder.getAllLinks("0"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(1, groups.size());
        assertEquals(Set.of(SQUARE0), groups.get(0));
    }

    /**
     * Testing 2 squares intersecting
     */
    @Test
    void simple()
    {
        relationshipFinder.add("0", SQUARE0);
        relationshipFinder.add("1", SQUARE1);

        assertEquals(Set.of(SQUARE0, SQUARE1), relationshipFinder.getAllLinks("0"));
        assertEquals(Set.of(SQUARE0, SQUARE1), relationshipFinder.getAllLinks("1"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(1, groups.size());
        assertEquals(Set.of(SQUARE0, SQUARE1), groups.get(0));
    }

    /**
     * Adding 2 squares not intersecting and 1 intersecting both
     */
    @Test
    void joined()
    {
        relationshipFinder.add("0", SQUARE0);
        relationshipFinder.add("3", SQUARE3);
        relationshipFinder.add("1", SQUARE1);

        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE3), relationshipFinder.getAllLinks("0"));
        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE3), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE3), relationshipFinder.getAllLinks("3"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(1, groups.size());
        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE3), groups.get(0));
    }

    /**
     * Testing 2 not-intersecting-groups of 2 intersecting squares,
     * And joining them later
     */
    @Test
    void joinLater()
    {
        relationshipFinder.add("0", SQUARE0);
        relationshipFinder.add("1", SQUARE1);
        relationshipFinder.add("5", SQUARE5);
        relationshipFinder.add("4", SQUARE4);

        assertEquals(Set.of(SQUARE0, SQUARE1), relationshipFinder.getAllLinks("0"));
        assertEquals(Set.of(SQUARE4, SQUARE5), relationshipFinder.getAllLinks("4"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(2, groups.size());
        assertEquals(Set.of(SQUARE0, SQUARE1), groups.get(0));
        assertEquals(Set.of(SQUARE4, SQUARE5), groups.get(1));

        relationshipFinder.add("3", SQUARE3);

        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE3, SQUARE4, SQUARE5), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE3, SQUARE4, SQUARE5), relationshipFinder.getAllLinks("5"));

        groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(1, groups.size());
        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE3, SQUARE4, SQUARE5), groups.get(0));

    }

    /**
     * Testing a complex case
     */
    @Test
    void complex()
    {
        relationshipFinder.add("0", SQUARE0);
        relationshipFinder.add("1", SQUARE1);
        relationshipFinder.add("5", SQUARE5);
        relationshipFinder.add("4", SQUARE4);
        relationshipFinder.add("0", SQUARE0);
        relationshipFinder.add("10", SQUARE10);

        assertEquals(Set.of(SQUARE0, SQUARE1), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(SQUARE10), relationshipFinder.getAllLinks("10"));

        var groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(3, groups.size());
        assertEquals(Set.of(SQUARE0, SQUARE1), groups.get(1));
        assertEquals(Set.of(SQUARE4, SQUARE5), groups.get(2));
        assertEquals(Set.of(SQUARE10), groups.get(0));

        relationshipFinder.add("2", SQUARE2);

        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE2, SQUARE4, SQUARE5), relationshipFinder.getAllLinks("1"));
        assertEquals(Set.of(SQUARE10), relationshipFinder.getAllLinks("10"));

        groups = new ArrayList<>(relationshipFinder.getGroups());
        assertEquals(2, groups.size());
        assertEquals(Set.of(SQUARE10), groups.get(0));
        assertEquals(Set.of(SQUARE0, SQUARE1, SQUARE2, SQUARE4, SQUARE5), groups.get(1));
    }


    private Geometry getSquare(int x, int y, int width)
    {
        try {
            return reader.read("POLYGON ((" +
                    x + " " + y + ", " +
                    x + " " + (y + width) + ", " +
                    (x + width) + " " + (y + width) + ", " +
                    (x + width) + " " + y + ", " +
                    x + " " + y + "))");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
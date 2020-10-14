package com.lnlib.relationship;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * This class aims at linking objects depending a match condition.
 * <p>
 * Output is getGroups, which gives a set of objects that are matching
 * Another output is getAllLinks that gives all the matching objects for a given id
 *
 *
 *
 * <h3>Example:</h3>
 * <p>
 * If a match condition is a number should be next to another,
 * <ul>
 * <li>then if I add 1,2,3 , all 3 numbers are linked.
 * <li>I continue adding 5, I should get 2 groups [1,2,3] and [5]
 * <li>Then if I add 4, then I get only one group [1,2,3,4,5]
 * </ul>
 *
 * @param <T> type of object
 */
public class RelationshipFinder<T>
{
    /**
     * Function to apply to check a condition is met
     */
    private final BiFunction<T, T, Boolean> matchCondition;
    private final Map<String, Link<T>> map = new HashMap<>();

    public RelationshipFinder(BiFunction<T, T, Boolean> matchCondition)
    {
        this.matchCondition = matchCondition;
    }


    /**
     * Entry point to add object with the given id
     *
     * @param id          an object identifier
     * @param objectToAdd the object to add
     */
    public void add(String id, T objectToAdd)
    {
        map.putIfAbsent(id, new Link<>(id, objectToAdd));

        addObjectToExistingMatchingObjects(id, objectToAdd);

        spreadLinkedObjects();
    }


    /**
     * Returns all the objects that are matching the condition for the given id
     *
     * @param id the object identifier
     * @return a set of object
     */
    public Set<T> getAllLinks(String id)
    {
        var toReturn = new Link<T>();

        gatherAllLinkedObjects(id, toReturn);

        return new HashSet<>(toReturn.items.values());
    }

    /**
     * @return groups of objects, represented by a set
     */
    public Set<Set<T>> getGroups()
    {
        var m = new HashMap<String, Set<T>>();
        map.keySet()
                .forEach(f -> m.put(f, getAllLinks(f)));

        return new HashSet<>(m.values());

    }


    /**
     * Put missing values in other cells
     * <p>
     * Starting point:
     * <ul>
     * <li>1 => [1,2]
     * <li>2 => [2]
     * <li>3 => [3]
     * </ul>
     * <p>
     * After calling this method, the result will be:
     * <ul>
     * <li>1 => [1,2]
     * <li>2 => [2,1] // added '1'
     * <li>3 => [3]
     * </ul>
     */
    private void spreadLinkedObjects()
    {
        map.forEach((key, value) -> {
            var values = value.items;
            values.forEach((k, v) -> map.get(k).items.putAll(values));
        });
    }

    /**
     * Check if the objectToAdd matches a another key
     * and add it to each corresponding key
     * <p>
     * Starting point:
     * <ul>
     * <li>1 => [1]
     * <li>2 => [2]
     * </ul>
     * <p>
     * Adding '3':
     * <ul>
     * <li>1 => [1]
     * <li>2 => [2,3]
     * </ul>
     *
     * @param id          object identifier
     * @param objectToAdd the object to add
     */
    private void addObjectToExistingMatchingObjects(String id, T objectToAdd)
    {
        map.entrySet().stream()
                .filter(f -> matchCondition.apply(f.getValue().items.get(f.getKey()), objectToAdd))
                .map(m -> m.getValue().items)
                .forEach(each -> each.put(id, objectToAdd));
    }

    /**
     * Starting point:
     * <ul>
     * <li>1 => [1,2,6]
     * <li>2 => [2]
     * <li>4 => [4]
     * <li>6 => [6]
     * </ul>
     * <p>
     * If calling this method with '1', toReturn contains:
     * <ul>
     * <li>1 => [1]
     * <li>2 => [2]
     * <li>6 => [6]
     * </ul>
     *
     * @param id object identifier
     * @param toReturn the object where to gather the linked objects
     */
    private void gatherAllLinkedObjects(String id, final Link<T> toReturn)
    {
        if (toReturn.containsKey(id)) {
            return;
        }

        toReturn.items.put(id, map.get(id).items.get(id));

        // and search all objects that are matching, but exclude itself
        map.get(id).items.entrySet().stream()
                .filter(f -> !f.getKey().equals(id))
                .forEach(m -> gatherAllLinkedObjects(m.getKey(), toReturn));
    }

    /**
     * Inner class representing all the objects linked
     *
     * @param <T>
     */
    private static class Link<T>
    {
        private final Map<String, T> items = new HashMap<>();

        public Link(String id, T objectToAdd)
        {
            items.put(id, objectToAdd);
        }

        public Link()
        {

        }


        public boolean containsKey(String key)
        {
            return items.containsKey(key);
        }

        @Override
        public String toString()
        {
            return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                    .append("items", items)
                    .toString();
        }


        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            var link = (Link<?>) o;

            return new EqualsBuilder()
                    .append(items, link.items)
                    .isEquals();
        }

        @Override
        public int hashCode()
        {
            return new HashCodeBuilder(17, 37)
                    .append(items)
                    .toHashCode();
        }
    }
}

package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class SimpleMapTest {

    @Test
    public void putTrue() {
       SimpleMap<String, Integer> map = new SimpleMap<>();
       Assert.assertTrue(map.put("a", 1));
    }

    @Test
    public void putFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        Assert.assertFalse(map.put("i", 2));
    }

    @Test
    public void get() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        Assert.assertThat(map.get("a"), is(1));
    }

    @Test
    public void getNull() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        map.put("b", 2);
        Assert.assertThat(map.get("c"), is(nullValue()));
    }

    @Test
    public void getSameIndex() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        Assert.assertThat(map.get("i"), is(nullValue()));
    }

    @Test
    public void removeTrue() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        map.put("b", 2);
        Assert.assertTrue(map.remove("a"));
    }

    @Test
    public void removeFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        Assert.assertFalse(map.remove("i"));
    }

    @Test
    public void iteration() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is("a"));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is("b"));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is("c"));
        Assert.assertFalse(iterator.hasNext());
    }
}
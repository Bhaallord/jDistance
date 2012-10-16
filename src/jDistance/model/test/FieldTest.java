package jDistance.model.test;

import static org.junit.Assert.*;
import jDistance.model.Distance;
import jDistance.model.Field;

import org.junit.Test;

public class FieldTest {

    @Test
    public void testFieldIntInt() {
        Field field1 = new Field(0, 0);
        assertEquals(0, field1.getX());
        assertEquals(0, field1.getY());
        Field field2 = new Field(100, -9);
        assertEquals(100, field2.getX());
        assertEquals(-9, field2.getY());
    }

    @Test
    public void testFieldField() {
        Field field1 = new Field(100, -9);
        assertEquals(100, new Field(field1).getX());
        Field field2 = new Field(field1);
        assertEquals(-9, field2.getY());
    }

    @Test
    public void testGetX() {
        Field field1 = new Field(100, -9);
        assertEquals(100, field1.getX());
    }

    @Test
    public void testGetY() {
        Field field1 = new Field(100, -9);
        assertEquals(-9, field1.getY());
    }

    @Test
    public void testDistanceTo() {
        Field field1 = new Field(0, 0);
        Field field2 = new Field(5, 6);
        Field field3 = new Field(4, 2);
        assertEquals(true, field1.distanceTo(field2).equals(new Distance(5, 6)));
        assertEquals(true, field2.distanceTo(field1).equals(new Distance(5, 6)));
        assertEquals(true, field3.distanceTo(field1).equals(new Distance(4, 2)));

    }

    @Test
    public void testEqualsField() {
        Field field1 = new Field(0, 0);
        Field field2 = new Field(5, 0);
        Field field3 = new Field(0, 0);
        Field field4 = new Field(0, 4);
        assertEquals(true, field1.equals(field3));
        assertEquals(false, field2.equals(field3));
        assertEquals(false, field4.equals(field1));
        assertEquals(false, field2.equals(field4));
    }
}

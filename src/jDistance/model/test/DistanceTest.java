package jDistance.model.test;

import static org.junit.Assert.*;
import jDistance.model.Distance;

import org.junit.Test;

public class DistanceTest {

    @Test
    public void testDistanceIntInt() {
        Distance dist1 = new Distance(2, 4);
        assertEquals(2, dist1.getX());
        assertEquals(4, dist1.getY());
        Distance dist2 = new Distance(-4, -1);
        assertEquals(4, dist2.getX());
        assertEquals(1, dist2.getY());
    }

    @Test
    public void testDistanceDistance() {
        Distance dist1 = new Distance(-4, -1);
        Distance dist2 = new Distance(dist1);
        assertEquals(4, dist2.getX());
        assertEquals(1, dist2.getY());

    }

    @Test
    public void testGetX() {
        Distance dist1 = new Distance(0, -1);
        assertEquals(0, dist1.getX());
        Distance dist2 = new Distance(-99, -1);
        assertEquals(99, dist2.getX());
    }

    @Test
    public void testGetY() {
        Distance dist1 = new Distance(0, -1);
        assertEquals(1, dist1.getY());
        Distance dist2 = new Distance(-99, 16);
        assertEquals(16, dist2.getY());
    }

    @Test
    public void testEqualsObject() {
        Distance dist1 = new Distance(4, -1);
        Distance dist2 = new Distance(-99, 16);
        assertEquals(false, dist1.equals(dist2));
        assertEquals(true, dist1.equals(new Distance(-4, 1)));
        assertEquals(true, dist1.equals(new Distance(4, 1)));
        assertEquals(true, dist1.equals(new Distance(-4, -1)));
        assertEquals(true, dist1.equals(new Distance(4, -1)));
        assertEquals(false, dist1.equals(new Distance(4, 2)));
        assertEquals(false, dist1.equals(new Distance(3, -1)));
        assertEquals(false, dist1.equals(new Object()));
        assertEquals(false, dist1.equals(null));
    }

    @Test
    public void testToString() {
        Distance dist1 = new Distance(10, 20);
        assertEquals(true, dist1.toString().contains("10"));
        assertEquals(true, dist1.toString().contains("20"));
    }

    @Test
    public void testHashCode() {
        Distance dist1 = new Distance(10, 20);
        assertEquals(32773, dist1.hashCode());
        Distance dist2 = new Distance(20, 10);
        assertEquals(32773, dist2.hashCode());
        Distance dist3 = new Distance(20, 9);
        assertEquals(32772, dist3.hashCode());
    }
}

package jDistance.model.test;

import jDistance.model.Field;
import jDistance.model.Slave;
import static org.junit.Assert.*;
import org.junit.Test;

public class SlaveTest {

    @Test
    public void testSlaveIntField() {
        jDistance.model.Slave reference = new jDistance.model.Slave(1, new jDistance.model.Field(1, 3));
        assertEquals(1, reference.getPos().getX());
        assertEquals(3, reference.getPos().getY());
        assertEquals(1, reference.getPlayer());
        jDistance.model.Slave alterable = new jDistance.model.Slave(2, new jDistance.model.Field(-1, 0));
        assertEquals(-1, alterable.getPos().getX());
        assertEquals(0, alterable.getPos().getY());
        assertEquals(2, alterable.getPlayer());
        assertEquals(false, alterable.isLocked());
    }

    @Test
    public void testSlaveSlave() {
        jDistance.model.Slave reference = new jDistance.model.Slave(1, new jDistance.model.Field(1, 3));
        jDistance.model.Slave alterable = new jDistance.model.Slave(reference);
        assertEquals(reference, alterable);
        alterable.setLocked(true);
        assertEquals(false, alterable.equals(reference));
        assertEquals(true, alterable.getPos().equals(reference.getPos()));
    }

    @Test
    public void testEqualsSlave() {
        jDistance.model.Slave reference = new jDistance.model.Slave(2, new jDistance.model.Field(5, 2));
        jDistance.model.Slave alterable = new jDistance.model.Slave(reference);
        assertEquals(true, alterable.equals(reference));
        assertEquals(true, reference.equals(alterable));
        alterable.setLocked(true);
        assertEquals(false, alterable.equals(reference));
        assertEquals(false, alterable.equals(null));
        assertEquals(false, alterable.equals(new Object()));
    }

    @Test
    public void testClone() {
        jDistance.model.Slave reference = new jDistance.model.Slave(2, new jDistance.model.Field(5, 2));
        jDistance.model.Slave alterable = reference.clone();
        assertEquals(reference, alterable);
        assertEquals(new jDistance.model.Slave(reference), alterable);
        alterable.move(new Field(3, 5));
        assertEquals(false, alterable.equals(reference));
        assertEquals(false, new jDistance.model.Slave(reference).equals(alterable));
    }

    @Test
    public void testHashCode() {
        jDistance.model.Slave reference = new jDistance.model.Slave(1, new Field(2, 3));
        assertEquals(63739612, reference.hashCode());
    }

    @Test
    public void testToString() {
        Slave token1 = new Slave(1, new Field(2, 3));
        assertEquals(true, token1.toString().contains("1"));
        assertEquals(true, token1.toString().contains("2"));
        assertEquals(true, token1.toString().contains("3"));
    }


}

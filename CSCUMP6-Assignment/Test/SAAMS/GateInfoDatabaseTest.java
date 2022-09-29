package SAAMS;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore; 
import static org.junit.Assert.*;

/**
 *
 * @author MAI
 */
public class GateInfoDatabaseTest {
    
    public GateInfoDatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStatus method, of class GateInfoDatabase.
     */
     @Test
    public void testGetStatus() {
        System.out.println("Test getStatus one");
        GateInfoDatabase Instance = new GateInfoDatabase();
        int mCode = 3454;
        int gateNumber = 1;
        Instance.allocate(gateNumber, mCode);
        int expResult = 1;
        int result = Instance.getStatus(gateNumber);
        assertEquals(expResult, result);       
    }

    
     @Test
    public void testGetStatus1() {
        System.out.println("Test getStatus two");
        int gateNumber = 1;
        GateInfoDatabase instance = new GateInfoDatabase();
        int expResult = 0;
        int result = instance.getStatus(gateNumber);
        assertNotEquals(expResult, result);    
    }


    
    /**
     * Test of allocate method, of class GateInfoDatabase.
     */
  @Test
    public void testAllocate() {
        System.out.println("Test allocate one");
        int gateNumber = 1;
        int mCode = 1;
        GateInfoDatabase instance = new GateInfoDatabase();
        instance.allocate(gateNumber, mCode);
        int result = instance.getStatus(gateNumber);
        assertEquals(1, result);
        assertNotEquals(2, result);

    }

    @Test
    public void testAllocate1() {
        System.out.println("Test allocate two");
        int gateNumber = 1;
        int mCode = 0;
        GateInfoDatabase instance = new GateInfoDatabase();
        instance.allocate(gateNumber, mCode);
        int result = instance.getStatus(gateNumber);
        assertEquals(1, result);
        assertNotEquals(2, result);      
       }


    /**
     * Test of docked method, of class GateInfoDatabase.
     */
    @Test 
    public void testDocked() {
        System.out.println("Test docked one");
        int gateNumber = 0;
        int mCode = 1;
        GateInfoDatabase instance = new GateInfoDatabase();
        instance.allocate(gateNumber, mCode);
        instance.docked(gateNumber);
        int result = instance.getStatus(gateNumber); 
        assertEquals(2, result);
    }
    @Test 
    public void testDocked1() {
        System.out.println("Test docked two");
        int gateNumber = 1;
        int mCode = 0;
        GateInfoDatabase instance = new GateInfoDatabase();
        instance.allocate(gateNumber, mCode);
        instance.docked(gateNumber);
        int result = instance.getStatus(gateNumber); 
        assertNotEquals(0, result);
    }


    /**
     * Test of departed method, of class GateInfoDatabase.
     */
 @Test 
    public void testDeparted() {
        System.out.println("Test departed one");
        GateInfoDatabase Instance = new GateInfoDatabase();
        int gateNumber = 0;        
        Instance.departed(gateNumber);
        assertEquals(0, Instance.getStatus(gateNumber));
    }

        @Test 
        
    public void testDeparted1() {
        System.out.println("Test departed two");
        int gateNumber = 0;
        GateInfoDatabase Instance = new GateInfoDatabase();
        Instance.departed(gateNumber);
        assertNotEquals(1, Instance.getStatus(gateNumber));
    }


    /**
     * Test of getGateAssignedToMR method, of class GateInfoDatabase.
     */
    @Test
    public void testGetGateAssignedToMR() {
        System.out.println("Test getGateAssignedToMR one");
        int mCode = 1234;
        GateInfoDatabase instance = new GateInfoDatabase();
        int expResult = 0;
        int result = instance.getGateAssignedToMR(mCode);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetGateAssignedToMR1() {
        System.out.println("Test getGateAssignedToMR two");
        int mCode = 3456;
        GateInfoDatabase instance = new GateInfoDatabase();
        int expResult = 1;
        int result = instance.getGateAssignedToMR(mCode);
        assertEquals(expResult, result);
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charlie.bs.section1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author claud
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({charlie.bs.section1.Test01_12_2.class, charlie.bs.section1.Test01_12_2.class, 
                    charlie.bs.section1.Test00_12_2.class, charlie.bs.section1.Test00_12_7.class,
                    charlie.bs.section2.Test00_5_2.class,charlie.bs.section2.Test01_5_2.class,
                    charlie.bs.section2.Test00_5_7.class,charlie.bs.section2.Test01_5_7.class,
                    charlie.bs.section3.Test00_A2_2.class,charlie.bs.section3.Test01_A2_2.class,
                    charlie.bs.section3.Test00_A2_7.class,charlie.bs.section3.Test01_A2_7.class,
                    charlie.bs.section4.Test00_22_2.class,charlie.bs.section4.Test01_22_2.class,
                    charlie.bs.section4.Test00_22_7.class,charlie.bs.section4.Test01_22_7.class,})
public class CharlieTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}

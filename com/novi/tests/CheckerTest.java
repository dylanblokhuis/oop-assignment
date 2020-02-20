package com.novi.tests;

import com.novi.models.Checker;
import com.novi.models.CheckerType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dylan Blokhuis
 * @date 20-2-2020
 * Leerlijn: Object Oriented Programmeren
 */
public class CheckerTest {

    @Test
    public void checkerType() {
        System.out.println("getCheckerType");
        Checker checker = new Checker(CheckerType.CLEAR);
        assertEquals(checker.getCheckerType(), CheckerType.CLEAR);

        checker = new Checker(CheckerType.DARK);
        assertEquals(checker.getCheckerType(), CheckerType.DARK);
    }

    @Test
    public void highlight() {
        System.out.println("highlight on");
        Checker checker = new Checker(CheckerType.CLEAR);
        checker.highlight();

        assertTrue(checker.isHighlighted());
    }

    @Test
    public void removeHighlight() {
        System.out.println("highlight remove");
        Checker checker = new Checker(CheckerType.CLEAR);
        checker.highlight();
        checker.removeHighlight();

        assertFalse(checker.isHighlighted());
    }

    @Test
    public void king() {
        System.out.println("makeKing and isKing");
        Checker checker = new Checker(CheckerType.CLEAR);
        checker.makeKing();

        assertTrue(checker.isKing());
    }
}
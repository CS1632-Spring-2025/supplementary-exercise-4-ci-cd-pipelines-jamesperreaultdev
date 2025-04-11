package edu.pitt.cs;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PitcherTest {
    Pitcher pitcher;
    BallGame game;


    @Before
    public void setUp() {

        pitcher = new Pitcher();
    }

    @Test
    public void addStress(){
        pitcher.addStress(10);
        assertEquals(10,pitcher.getStress());
    }

    @Test
    public void testPitch() {
        Pitcher PitcherMock = Mockito.mock(Pitcher.class);
        Batter BatterMock = Mockito.mock(Batter.class);
        BallGame BallGame = new BallGame(PitcherMock,BatterMock);
        Mockito.when(PitcherMock.throwBall()).thenReturn(true);
        Mockito.when(BatterMock.swingBat()).thenReturn(true);
        String result = BallGame.pitch();
        assertEquals("hit",result);
        verify(PitcherMock,times(1)).addStress(10);
    }

    /**
     * <pre>
     * Preconditions: A Pitcher pitcher is created.
     *                The pitcher always throws strikes.
     *                A Batter batter is created.
     *                The batter always swings bat.
     *                A BallGame game is created using pitcher and batter.
     * Execution steps: Call game.pitch().
     * Postconditions: The string "hit" is returned from the call.
     *                 Stress of 10 is added to the pitcher.
     * </pre>
     */



}
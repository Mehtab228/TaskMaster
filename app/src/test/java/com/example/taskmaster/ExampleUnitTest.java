package com.example.taskmaster;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.taskmaster.model.Tasks;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void canCreateNewTask(){
        Tasks sut = new Tasks("Sleep", "take a nap", Tasks.State.IN_PROGRESS);
        assertEquals(sut.getTitle(), "Sleep");
        assertEquals(sut.getState(), Tasks.State.IN_PROGRESS);
        assertEquals(sut.getBody(), "take a nap");
    }
}
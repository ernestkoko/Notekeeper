package com.buildappswithernest.notekeeper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    static DataManager sDataManager;


    @BeforeClass
    public static void classSetUp() throws  Exception{
        sDataManager =DataManager.getInstance();

    }

    //methods that have @Before run before every unit test
    @Before
    public void setUp() throws  Exception {
       
        sDataManager.getNotes().clear();
        sDataManager.initializeExampleNotes();
    }

    @Test
    public void createNewNote() {


        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body text of my test note";



        //create new note and set the values
        int noteIndex = sDataManager.createNewNote();
        NoteInfo newNote = sDataManager.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);

        // running the test with Assert class
        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);
        assertEquals(course, compareNote.getCourse());
        assertEquals(noteTitle, compareNote.getTitle());
        assertEquals(noteText, compareNote.getText());
       // assertSame(newNote, compareNote);

    }
   @Test
    public void findSimilarnotes() throws Exception{



        //initialise values for the course
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText1 = "This is the body text of my test note";
        final String noteText2 = "This i the body of my second test note";

        //creating new note 1
        int noteIndex1 = sDataManager.createNewNote();

        // getting note position
        NoteInfo newNote1 = sDataManager.getNotes().get(noteIndex1);

        //setting course name, title, and text to created new note
        newNote1.setCourse(course);
        newNote1.setTitle(noteTitle);
        newNote1.setText(noteText1);

        //creating new note 2
        int noteIndex2 = sDataManager.createNewNote();

        // getting note position
        NoteInfo newNote2 = sDataManager.getNotes().get(noteIndex2);

        //setting course name, title, and text to created new note 2
        newNote2.setCourse(course);
        newNote2.setTitle(noteTitle);
        newNote2.setText(noteText2);

        //finding the newNote1 and testing if it is equal to the expected note
        int foundIndex1 = sDataManager.findNote(newNote1);
        assertEquals(noteIndex1, foundIndex1);

        //finding the newNote2 and testing if it is equal to the expected note
        int foundIndex2 = sDataManager.findNote(newNote2);
        assertEquals(noteIndex2, foundIndex2);

    }

    @Test
    public void createNewNoteOneStepCreation(){

        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final  String noteText = "This is the body of my test note";

        int noteIndex = sDataManager.createNewNote(course, noteTitle, noteText);

        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);

        assertEquals(course, compareNote.getCourse());
        assertEquals(noteTitle, compareNote.getTitle());
        assertEquals(noteText, compareNote.getText());
    }
}

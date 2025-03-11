package levelEditor.controller;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LevelEditorTest.class,  // Your existing test class
        NivSkapande.class       // The other test class
})
public class LevelEditorTestSuite {
    // This class is empty on purpose.
    // The annotations do all the work.
}
package levelEditor.controller;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        NivHanteringTest.class,
        NivSkapandeTest.class,
        ObjHanteringTest.class,
        Test_ForhandsGranskning.class
})
public class LESuite {

}

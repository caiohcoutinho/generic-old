package gurps.graphics.components;

import org.newdawn.slick.gui.TextField;

/**
 * Created by Caio on 15/04/2015.
 */
public class TextInput{

    private String initialValue;
    private TextField textField;

    public TextInput(String initialValue) {
        this.initialValue = initialValue;
    }

    public String getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(String initialValue) {
        this.initialValue = initialValue;
    }

    public TextField getTextField() {
        return textField;
    }

    public void setTextField(TextField textField) {
        this.textField = textField;
    }
}

package com.yuriikonovalov.caesarcipher;

import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;
import com.yuriikonovalov.caesarcipher.domain.usecases.CopyToClipboard;
import com.yuriikonovalov.caesarcipher.domain.usecases.DecryptMessage;
import com.yuriikonovalov.caesarcipher.domain.usecases.EncryptMessage;
import com.yuriikonovalov.caesarcipher.domain.usecases.GetAlphabets;
import com.yuriikonovalov.caesarcipher.utils.result.Result;
import com.yuriikonovalov.caesarcipher.utils.result.Success;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.paint.Paint;

import java.util.List;

public class MainController {

    private static final int KEY_MIN_VALUE = 0;
    private static final int KEY_MAX_VALUE = 50;
    private static final int KEY_INITIAL_VALUE = 8;

    private static final String COLOR_SUCCESS = "#A5FF2B";
    private static final String COLOR_ERROR = "#FF4444";
    @FXML
    private ChoiceBox<Alphabet> alphabetChoiceBox;
    @FXML
    private Spinner<Integer> keySpinner;
    @FXML
    private TextArea inputText;
    @FXML
    private Label resultLabel;
    @FXML
    private Label resultText;
    @FXML
    private Button encryptButton;
    @FXML
    private Button decryptButton;
    @FXML
    private Button copyButton;
    private DecryptMessage decryptMessage;
    private EncryptMessage encryptMessage;
    private CopyToClipboard copyToClipboard;


    public void initController(
            GetAlphabets getAlphabets,
            DecryptMessage decryptMessage,
            EncryptMessage encryptMessage,
            CopyToClipboard copyToClipboard
    ) {
        this.decryptMessage = decryptMessage;
        this.encryptMessage = encryptMessage;
        this.copyToClipboard = copyToClipboard;
        setupAlphabetChoiceBox(getAlphabets.execute());
        setupKeySpinner();
        setupEncryptDecryptButtons();
        setupCopyButton();
    }

    private void setupKeySpinner() {
        keySpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(KEY_MIN_VALUE, KEY_MAX_VALUE, KEY_INITIAL_VALUE)
        );
    }

    private void setupAlphabetChoiceBox(List<Alphabet> alphabets) {
        alphabetChoiceBox.getItems().addAll(alphabets);
        alphabetChoiceBox.setValue(alphabets.get(0));
    }

    private void setupEncryptDecryptButtons() {
        // Initial setup.
        encryptButton.setDisable(true);
        decryptButton.setDisable(true);
        // If the inputField is empty - then the buttons should be disabled.
        inputText.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean disabled = newValue.isEmpty();
            encryptButton.setDisable(disabled);
            decryptButton.setDisable(disabled);
        });
    }

    private void setupCopyButton() {
        // Initial setup.
        copyButton.setVisible(false);
        resultText.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean visible = !newValue.isEmpty();
            copyButton.setVisible(visible);
        });
    }

    @FXML
    protected void onCopyButtonClick() {
        copyToClipboard.execute(copyResult(resultText.getText()));
    }

    @FXML
    protected void onDecryptButtonClick() {
        onButtonClick(decryptMessage::execute);
    }

    @FXML
    protected void onEncryptButtonClick() {
        onButtonClick(encryptMessage::execute);
    }

    private void onButtonClick(CryptographyAction action) {
        int key = keySpinner.getValue();
        String message = inputText.getText();
        Alphabet alphabet = alphabetChoiceBox.getValue();
        Result<String> result = action.perform(key, message, alphabet);
        displayResult(result);
    }

    private String uiResult(String message) {
        return "'" + message + "'";
    }

    private String copyResult(String message) {
        return message.replace("'", "");
    }

    private void displayResult(Result<String> result) {
        resultLabel.setVisible(true);
        if (result instanceof Success<String>) {
            resultLabel.setText("Результат");
            resultLabel.setBackground(Background.fill(Paint.valueOf(COLOR_SUCCESS)));
            resultText.setText(uiResult(result.getData()));
        } else {
            resultLabel.setText("Помилка");
            resultLabel.setBackground(Background.fill(Paint.valueOf(COLOR_ERROR)));
            resultText.setText("Ключем (зміщенням) повиненно бути число більше за 0.");
        }
    }

    @FunctionalInterface
    private interface CryptographyAction {
        Result<String> perform(int key, String message, Alphabet alphabet);
    }
}
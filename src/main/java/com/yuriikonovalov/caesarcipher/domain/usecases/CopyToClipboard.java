package com.yuriikonovalov.caesarcipher.domain.usecases;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyToClipboard {
    public void execute(String message) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(message);
        clipboard.setContent(content);
    }
}

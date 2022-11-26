module com.yuriikonovalov.caesarcipher {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.yuriikonovalov.caesarcipher to javafx.fxml;
    exports com.yuriikonovalov.caesarcipher;
    exports com.yuriikonovalov.caesarcipher.domain.usecases;
    exports com.yuriikonovalov.caesarcipher.adapter;
    exports com.yuriikonovalov.caesarcipher.domain.entities;
    exports com.yuriikonovalov.caesarcipher.domain;
    exports com.yuriikonovalov.caesarcipher.utils.result;
    opens com.yuriikonovalov.caesarcipher.utils.result to javafx.fxml;
}
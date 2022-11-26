package com.yuriikonovalov.caesarcipher.domain.usecases;

import com.yuriikonovalov.caesarcipher.domain.AlphabetSource;
import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;

import java.util.List;

public class GetAlphabets {
    private final AlphabetSource alphabetSource;

    public GetAlphabets(AlphabetSource alphabetSource) {
        this.alphabetSource = alphabetSource;
    }

    public List<Alphabet> execute() {
        return alphabetSource.getAllAlphabets();
    }
}

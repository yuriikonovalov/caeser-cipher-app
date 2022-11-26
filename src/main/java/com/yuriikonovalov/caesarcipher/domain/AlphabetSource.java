package com.yuriikonovalov.caesarcipher.domain;

import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;

import java.util.List;

public interface AlphabetSource {
    List<Alphabet> getAllAlphabets();
}

package com.yuriikonovalov.caesarcipher.domain.entities;

import java.util.List;

public record Alphabet(String name, List<Character> characters) {

    @Override
    public String toString() {
        return this.name;
    }
}

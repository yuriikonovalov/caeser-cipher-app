package com.yuriikonovalov.caesarcipher;

import com.yuriikonovalov.caesarcipher.domain.entities.Alphabet;

import java.util.List;

public class TestAlphabet {
    private static final String uaName = "Український";
    private static final List<Character> uaCharacters = List.of(
            'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р',
            'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь', 'Ю', 'Я'
    );

    private static final String enName = "English";
    private static final List<Character> enCharacters = List.of(
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    );

    public static final Alphabet uaAlphabet = new Alphabet(uaName, uaCharacters);
    public static final Alphabet enAlphabet = new Alphabet(enName, enCharacters);
}

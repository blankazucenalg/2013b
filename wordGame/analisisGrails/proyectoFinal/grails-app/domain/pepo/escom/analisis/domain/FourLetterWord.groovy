package pepo.escom.analisis.domain

class FourLetterWord {
    Long idWord
    String word
    static constraints = {
        idWord(nullable:true, blank:true)
        word(blank: false, size: 0..100)
    }
    static mapping = {
        version false
        table 'four_letter_word'
        id(name:'idWord',column:'idWord')
        word(column:'word')
    }
}

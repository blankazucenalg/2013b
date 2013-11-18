package pepo.escom.analisis.domain

class FiveLetterWord {
    Long idWord
    String word
    static constraints = {
        idWord(nullable:true, blank:true)
        word(blank: false, size: 0..100)
    }
    static mapping = {
        version false
        id(name:'idWord',column:'idWord')
        word(column:'word')
    }
}

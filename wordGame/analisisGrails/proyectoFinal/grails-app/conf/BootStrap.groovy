import pepo.escom.analisis.*
import pepo.escom.analisis.domain.FourLetterWord
import pepo.escom.analisis.domain.FiveLetterWord
class BootStrap {

    def init = { servletContext ->
        /*Se utiliza el bootstrap para arrancar y cargar los archivos y generar los nodos en la sesión*/
        /*
         *def dictionary1 = new File(servletContext.getRealPath('archivos/englishWords.txt'))
        def dictionary2 = new File(servletContext.getRealPath('archivos/englishWords2.txt'))
        FourLetterWord four = null
        dictionary1.eachLine{ line ->
            four = new FourLetterWord()
            four.word = line
            four.save()
        }
        FiveLetterWord five = null
        dictionary2.eachLine{ line ->
            five = new FiveLetterWord()
            five.word = line
            five.save()
        }
         **/
    }
    def destroy = {
    }
}

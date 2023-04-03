package unitTest;

import br.com.iterasys.CalcularArea;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Classe
public class TesteArea {
    //Atributos

    //Funções e Métodos

    @Test
    public void testeCalcularAreaCirculo(){

        //Configura
        //Valores de entrada
        double raio = 5;
        // Valores de saída
        double resultadoEsperado = 78.5;

        //Executa
        double resultadoAtual = CalcularArea.areaCirculo(raio);

        //Valida
        assertEquals(resultadoEsperado, resultadoAtual);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "7, 153.86",
            "6, 113.04",
            "1, 3.14",
            "15, 706.50"
    }, delimiter = ',')
    public void testeCalcularAreaCirculoLendoLista(String txtRaio, String resultadoEsperado) {

        //Executa
        double resultadoAtual = CalcularArea.areaCirculo(Integer.valueOf(txtRaio));

        //Valida
        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/unitCSV/areaCirculo.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeCalcularAreaCirculoLendoArquivo(String txtRaio, String resultadoEsperado) {

        //Executa
        double resultadoAtual = CalcularArea.areaCirculo(Integer.valueOf(txtRaio));

        //Valida
        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/unitCSV/volumeCilindro.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeCalcularVolumeCilindroLendoArquivo(String txtRaio, String txtAltura, String resultadoEsperado) {

        //Executa
        double resultadoAtual = CalcularArea.volumeCilindro(Integer.valueOf(txtRaio), Integer.valueOf(txtAltura));

        //Valida
        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }
}


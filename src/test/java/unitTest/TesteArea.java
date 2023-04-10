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

    @Test
    public void testeAreaRetangulo(){
        double base = 7;
        double altura = 5;
        double resultadoEsperado = 35;

        Double resultadoAtual = CalcularArea.areaRetangulo(base, altura);

        assertEquals(resultadoEsperado, resultadoAtual);
    }
    // este é um teste de unidade data driven - direcionado por dados

    @ParameterizedTest
    @CsvFileSource(resources = "unitCSV/massaRetangulo.csv")
    public void testeAreaRetanguloLendoArquivo(String txtNum1, String txtNum2, String resultadoEsperado){

        Double resultadoAtual = CalcularArea.areaRetangulo(Integer.valueOf(txtNum1), Integer.valueOf(txtNum2));

        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }

        @Test
        public void testeCalcularAreaQuadrado(){

            //Configura
            //Valores de entrada
            double aresta1 = 2;
            // Valores de saída
            double resultadoEsperado = 4;

            //Executa
            double resultadoAtual = CalcularArea.areaQuadrado(aresta1);

            //Valida
            assertEquals(resultadoEsperado, resultadoAtual);

        }

        @ParameterizedTest
        @CsvSource(value = {
                "2, 4",
                "4, 16",
                "8, 64",
                "9, 81"
        }, delimiter = ',')
        public void testeAreaQuadradoLendoLista(String txtaresta1, String resultadoEsperado) {

            //Executa
            double resultadoAtual = CalcularArea.areaQuadrado(Integer.valueOf(txtaresta1));

            //Valida
            assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);

        }
        @ParameterizedTest
        @CsvFileSource(resources = "unitCSV/massaQuadrado.csv", numLinesToSkip = 1, delimiter = ',')
        public void TesteCalcularAreaQuadradoLendoArquivo(String txtAresta, String resultadoEsperado){ // inicio do teste d
            // Configura
            // Os dados de entrada e o resultado esperado vem da lista

            // Executa
            double resultadoAtual = CalcularArea.areaQuadrado(Integer.valueOf(txtAresta));

            // Valida
            assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
        } // final do teste

    }


    /*
    @ParameterizedTest
    @CsvFileSource(resources = "/unitCSV/volumeCilindro.csv", numLinesToSkip = 1, delimiter = ',')
    public void testeCalcularVolumeCilindroLendoArquivo(String txtRaio, String txtAltura, String resultadoEsperado) {

        //Executa
        double resultadoAtual = CalcularArea.volumeCilindro(Integer.valueOf(txtRaio), Integer.valueOf(txtAltura));

        //Valida
        assertEquals(Double.valueOf(resultadoEsperado), resultadoAtual);
    }
}

*/
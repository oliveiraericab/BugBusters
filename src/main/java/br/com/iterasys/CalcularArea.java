//1. Pacote: conjunto de classes
package br.com.iterasys;

//2. Bibliotecas

//3. Classe

public class CalcularArea {
//3.1 Atributos - Características - Campos

    //3.2 Funções e Métodos
    // se for método - vai escrever void (faz mas não dá retorno)
    // Para a função retornar texto - String; verd ou falso - boolean; retorna número - double
    public static double areaQuadrado(double aresta) {
        return aresta * aresta;
    }
    public static double volumeCubo(double aresta) {
        return Math.pow(aresta, 3);
    }

    public static double areaRetangulo(double base, double altura) {
        return base * altura;
    }

    public static double volumeParalelepipedo(double base, double altura, double comprimento) {
        return base * altura * comprimento;
    }

    public static double areaTriangulo(double base, double altura) {
        return (base * altura) / 2;
    }

    public static double volumePiramideTriangular(double base, double altura, double comprimento) {
        return (((base * altura) / 2) * comprimento) / 3;
    }

    public static double volumePiramideQuadrada(double base, double comprimento) {
        return (Math.pow(base, 2) * comprimento) / 3;
    }

    public static double volumePiramideRetangular(double base, double altura, double comprimento) {
        return (base * altura * comprimento) / 3;
    }

    public static double areaCirculo(double raio) {
        return Math.pow(raio, 2) * 3.14;
    }

    public static double volumeCilindro(double raio, double altura) {
        return Math.pow(raio, 2) * 3.14 * altura;
    }
}
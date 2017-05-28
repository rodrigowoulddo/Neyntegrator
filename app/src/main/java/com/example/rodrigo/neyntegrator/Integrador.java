package com.example.rodrigo.neyntegrator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Created by Rodrigo on 28/05/2017.
 */

public class Integrador {

    String funcao;

    String detalhes;

    float a;
    float b;

    double[] t = { 0.90617985,
            -0.90617985,
            0.53846931,
            -0.53846931,
            0};

    double A[] = {0.23692688,
            0.23692688,
            0.47862868,
            0.47862868,
            0.56888889};

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    //Calcula o valor de x que compõe a função F(x)
    private String getXComIntervalos() {
        //detalhes+=("x of intervals: " + "((((" + b + "-" + a + ")/2) * x) + ((" + b + "+" + a + ")/2))")+"\r\n";
        return "((((" + b + "-" + a + ")/2) * x ) + ((" + b + "+" + a + ")/2))";

    }

    private String getF() {
        String bkpFuncao = String.valueOf(funcao);
        detalhes+=("F(x): " + "((" + b + "-" + a + ")/2) *" + bkpFuncao.replaceAll("x", "(" + getXComIntervalos() + ")"))+"\r\n"+" \r\n";
        return "((" + b + "-" + a + ")/2) *" + bkpFuncao.replaceAll("x", "(" + getXComIntervalos() + ")");
    }


    //Substitue x em uma funcao
    private String substitueXnaFuncao(String funcao, double x) {
        funcao.replaceAll("x", String.valueOf(x));
        //detalhes+=("Replaced function: " + funcao);
        return funcao;
    }

    //Define a função  Ai * F(ti) a ser calculada em um ciclo
    private String defineFuncaoDoClico(String F, int n) {
        String funcaoSubstituida = F.replaceAll("x", String.valueOf(t[n]));
        //detalhes+=("Function of cicle " + n + " is: " + String.valueOf(A[n]) + " * (" + funcaoSubstituida + ")")+"\r\n";
        detalhes+= ("Cicle "+n)+"\r\n";
        return String.valueOf(A[n]) + " * (" + funcaoSubstituida + ")";
    }

    //Calcula o valor dada a funcao já substituída
    private double calculaValorDeFuncao(String funcao) {


        try {
            Expression expression = new ExpressionBuilder(funcao).build();
            double resultado = expression.evaluate();
            detalhes+=("Cicle Result: " + resultado)+"\r\n";
            return resultado;
        } catch (Exception e) {
            // TODO: handle exception
        }


        System.err.println("ERRO AO CALCULAR VALOR");
        return 0;

    }


    public double integrar() {

        double resultado = 0;
        String F = getF();

        for (int n = 0; n < 5; n++) {
            resultado += calculaValorDeFuncao(defineFuncaoDoClico(F, n));
        }
        detalhes+=("The integration result is: " + resultado)+"\r\n";
        return resultado;
    }
}

package br.com.hugotiyoda.calc.memory;

import java.util.ArrayList;
import java.util.List;

public class Memory {

    private enum tipoOperacao {
        ZERAR, NUMERO, DIVI, MULT, SOMA, SUB, DECIMAL, IGUAL, SINAL, PORCEM;

    }

    private static final Memory instancia = new Memory();
    private String textoAtual = ""; //mostrado na calc
    private String textBuffer = ""; //armazenado para operações
    private boolean troca; //padrao falso
    private tipoOperacao lastOperacao = null;
    private final List<MemoryListener> listeners = new ArrayList<>();

    private Memory() {

    }

    public static Memory getInstance() {
        return instancia;
    }

    public String getTextoAtual() {
        return textoAtual.isEmpty() ? "0" : textoAtual;
    }

    public void addListener(MemoryListener listener) {
        listeners.add(listener);
    }

    public void comando(String comando) {

        tipoOperacao operacao = detectarTipoOperacao(comando);
        System.out.println(operacao);

        if (operacao == null) {
            return;
        } else if (operacao == tipoOperacao.ZERAR) {
            textoAtual = "";
            textBuffer = "";
            troca = false;
            lastOperacao = null;
        } else if(operacao == tipoOperacao.SINAL && textoAtual.contains("-")){
            textoAtual = textoAtual.substring(1);
        } else if(operacao == tipoOperacao.SINAL && !textoAtual.contains("-")){
            textoAtual = "-" + textoAtual;
        }
        else if (operacao == tipoOperacao.NUMERO || operacao == tipoOperacao.DECIMAL) {
            textoAtual = troca ? comando : textoAtual + comando;
            troca = false;
        } else {
            troca = true;
            textoAtual = obterResultado();

            textBuffer = textoAtual;
            lastOperacao = operacao;
        }


        listeners.forEach(listeners -> listeners.valorAlterado(getTextoAtual()));

    }

    private String obterResultado() {
        if (lastOperacao == null || lastOperacao == tipoOperacao.IGUAL) {
            return textoAtual;
        }

        double numBuffer = Double.parseDouble(textBuffer.replace(",", "."));
        double numAtual = Double.parseDouble(textoAtual.replace(",", "."));
        double resultado = 0;

        if (lastOperacao == tipoOperacao.SOMA) {
            resultado = numBuffer + numAtual;
        } else if (lastOperacao == tipoOperacao.SUB) {
            resultado = numBuffer - numAtual;
        } else if (lastOperacao == tipoOperacao.DIVI) {
            resultado = numBuffer / numAtual;
        } else if (lastOperacao == tipoOperacao.MULT) {
            resultado = numBuffer * numAtual;
        } else if (lastOperacao == tipoOperacao.PORCEM) {
            resultado = numBuffer /100;
        }

        String resultadoString = Double.toString(resultado).replace(".", ",");
        boolean inteiro = resultadoString.endsWith(",0");

        return inteiro ? resultadoString.replace(",0", "") : resultadoString;
    }

    private tipoOperacao detectarTipoOperacao(String comando) {

        if (textoAtual.isEmpty() && comando == "0") {
            return null;
        }
        try {
            Integer.parseInt(comando);
            return tipoOperacao.NUMERO;
        } catch (NumberFormatException e) {
            if ("AC".equals(comando)) {
                return tipoOperacao.ZERAR;
            } else if ("/".equals(comando)) {
                return tipoOperacao.DIVI;
            } else if ("*".equals(comando)) {
                return tipoOperacao.MULT;
            } else if ("+".equals(comando)) {
                return tipoOperacao.SOMA;
            } else if ("-".equals(comando)) {
                return tipoOperacao.SUB;
            } else if ("±".equals(comando)) {
                return tipoOperacao.SINAL;
            }else if ("%".equals(comando)) {
                return tipoOperacao.PORCEM;
            } else if ("=".equals(comando)) {
                return tipoOperacao.IGUAL;
            } else if (",".equals(comando) && !textoAtual.contains(",")) {
                return tipoOperacao.DECIMAL;
            }
        }

        return null;
    }
}

package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Transacao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TransacaoDao {

    private final String path = "db/transacoes/";

    public TransacaoDao() {


    }

    public void create(Transacao transacao, String num){
        ArrayList<Transacao> listaTransacoes = getTransacoes(num);
        listaTransacoes.add(transacao);


        Arquivo a = new Arquivo(path+num+".dat");
        if (a.abrirEscrita())
        {
            for (Transacao tra: listaTransacoes)
            {
                a.escreverLinha( tra.toString() );
            }
        }
        else {

        }
        a.fecharArquivo();
    }

    public ArrayList<Transacao> getTransacoes(String numero){
        ArrayList<Transacao> listaTransacoes = new ArrayList<Transacao>();
        Arquivo a = new Arquivo(path+numero+".dat");
        DateTimeFormatter dateFormatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        a.abrirLeitura();
        String linha = a.lerLinha();
        while (linha != null) {
            if (!linha.isEmpty()) {
                String[] splis = linha.split(";");
                LocalDate data = LocalDate.parse(splis[0], dateFormatador);
                String descricao = splis[1];
                double valor = Double.parseDouble(splis[2]);
                char tipoOperacao = splis[3].charAt(0);
                double saldo = Double.parseDouble(splis[4]);

                Transacao trs = new Transacao(data, descricao, valor, tipoOperacao, saldo);
                listaTransacoes.add(trs);
            }
            linha = a.lerLinha();
        }

        a.fecharArquivo();
        return listaTransacoes;
    }



}

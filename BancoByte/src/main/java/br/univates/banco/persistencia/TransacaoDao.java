package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Transacao;

import java.util.ArrayList;

public class TransacaoDao {

    private final String path = "db\\transacoes\\";

    public TransacaoDao(int numero) {


    }

    public ArrayList<Transacao> getTransacoes(int numero){
        ArrayList<Transacao> listaTransacoes = new ArrayList<Transacao>();
        Arquivo a = new Arquivo(path+numero+".dat");
        a.abrirLeitura();
        String linha = a.lerLinha();
        while (linha != null) {
            if (!linha.isEmpty()) {
                String[] splis = linha.split(";");
                Arquivo t = new Arquivo();
                listaContas.add(new ContaBancaria());
            }
            linha = a.lerLinha();
        }

        a.fecharArquivo();
    }
}

package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.ContaBancariaEspecial;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.ContaBancariaDao;
import br.univates.banco.persistencia.CorrentistaDao;

import java.util.ArrayList;

public class TelaCadastro {

    public void exibir(){
        boolean repetir = true;
        while (repetir){
        try {
            CorrentistaDao dao = new CorrentistaDao();
            ContaBancariaDao contaDao = new ContaBancariaDao();
            ArrayList<Correntista> listaCorrentisas = dao.readAll();
            String cpfCorre = "";



            System.out.println("Correntistas Disponiveis:");

            for (Correntista corre: listaCorrentisas) {
                if (!contaDao.checkCorrentista(corre.getCpf())) {
                    System.out.println(corre.toString());
                }
            }

            cpfCorre = Entrada.leiaString("Digite o CPF do correntista a ser cadastrado");
            Cpf cpfo = new Cpf(cpfCorre);
            if (!contaDao.checkCorrentista(cpfo)){
                int tipoConta = Entrada.leiaInt("Escolha o tipo de conta [1] Normal | [2] Especial");
                if (tipoConta != 1 && tipoConta != 2){
                    throw new TipoInvalidoException();
                }
                if(tipoConta == 1){
                    ContaBancaria contaAdd = new ContaBancaria(dao.read(new Cpf(cpfCorre)));
                    contaDao.create(contaAdd);
                    repetir = false;
                }
                if(tipoConta == 2){
                    ContaBancaria contaAdd = new ContaBancariaEspecial(dao.read(new Cpf(cpfCorre)));
                    contaDao.create(contaAdd);
                    repetir = false;
                }
            }


        } catch (TipoInvalidoException e) {
            System.out.println("Opção invalida");
        } catch (InvalidEntryException e) {
            throw new RuntimeException(e);
        }

        }


    }

    private class TipoInvalidoException extends Throwable {
    }
}

package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.DataBaseConnectionManager;
import br.univates.alexandria.persistence.DataBaseException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class CorrentistaDao {


    public CorrentistaDao() {

    }

    // Create Read Update Delete
    public void create(Correntista correntista) {
        DataBaseConnectionManager db;
        try {
            db = new DataBaseConnectionManager(
                    DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte",
                    "vinicius",
                    "123456");

            db.connectDataBase();
            db.executePreparedSQL("INSERT INTO correntista (cpf_correntista, nome, municipio) VALUES (?, ?, ?)", correntista.getCpf().getNumeroCpf(), correntista.getNome(), correntista.getMunicipio());
            db.connectDataBase();
            db.closeConnection();

        } catch (DataBaseException e) {
            System.out.println("Erro: " + e);
            throw new RuntimeException(e);
        }


    }




    public Correntista read(String cpf) {
        Correntista correntistaAchado = null;
        DataBaseConnectionManager db;
        try {
            db = new DataBaseConnectionManager(
                    DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte",
                    "vinicius",
                    "123456");


            db.connectDataBase();
            ResultSet rs = db.runPreparedQuerySQL("SELECT * FROM correntista WHERE cpf_correntista = ?;", cpf);
            db.closeConnection();

            if (rs.isBeforeFirst()){
                rs.next();
                String cpfC = rs.getString("cpf_correntista");
                String nome = rs.getString("nome");
                String municipio = rs.getString("municipio");
                correntistaAchado = new Correntista(new Cpf(cpfC), nome, municipio);
            }

        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntryException e) {
            throw new RuntimeException(e);
        }


        return correntistaAchado;
    }
    public void update(Correntista correntista){
        DataBaseConnectionManager db;

        try {
            db = new DataBaseConnectionManager(
                    DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte",
                    "vinicius",
                    "123456");

            db.connectDataBase();
            db.runPreparedQuerySQL("UPDATE correntista SET nome = ?, municipio = ? WHERE cpf_correntista = ?",
                    correntista.getNome(),
                    correntista.getMunicipio(),
                    correntista.getCpf().getNumeroCpf());
            db.closeConnection();

        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        }

    };
    /*public void update(Correntista correntista) {
        // verificação de integridade contralada diretamente pelo sistema
        ArrayList<Correntista> listaCorrentistas = readAll();
        int index = listaCorrentistas.indexOf(correntista);
        if (index >= 0) {
            listaCorrentistas.set(index, correntista);
            salvarArquivo(listaCorrentistas);
        }
    }*/

    public void delete(Correntista correntista){
        DataBaseConnectionManager db;

        try {
            db = new DataBaseConnectionManager(
                    DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte",
                    "vinicius",
                    "123456");
            db.connectDataBase();
            db.runPreparedQuerySQL("DELETE FROM correntista WHERE cpf_correntista = ?;",
                    correntista.getCpf().getNumeroCpf());
            db.closeConnection();

        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        }

    }
    /*public void delete(Correntista correntista) {
        // verificação de integridade contralada diretamente pelo sistema
        ContaBancariaDao dao = new ContaBancariaDao();
        ArrayList<ContaBancaria> listaContas = dao.readAll(correntista);

        if (listaContas.size() == 0) // só pode deletar se não tem conta bancária
        {
            ArrayList<Correntista> listaCorrentistas = readAll();
            int index = listaCorrentistas.indexOf(correntista);
            if (index >= 0) {
                listaCorrentistas.remove(index);
            }
            salvarArquivo(listaCorrentistas);
        }
    }*/

    public Correntista read(Cpf cpf) {
        return read(cpf.getNumeroCpf());
    }

    public ArrayList<Correntista> readAll() {
        DataBaseConnectionManager db;
        ArrayList<Correntista> listaCorrentistas = new ArrayList<Correntista>();

        try {
            db = new DataBaseConnectionManager(
                    DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte",
                    "vinicius",
                    "123456");

            db.connectDataBase();
            ResultSet rs = db.runQuerySQL("SELECT * FROM correntista");
            db.closeConnection();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String cpf = rs.getString("cpf_correntista");
                    String nome = rs.getString("nome");
                    String municipio = rs.getString("municipio");

                    Correntista corre = new Correntista(new Cpf(cpf), nome, municipio);
                    listaCorrentistas.add(corre);

                }
            }


        } catch (DataBaseException e) {
            System.out.println("Erro" + e);
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntryException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(listaCorrentistas);
        return listaCorrentistas;
    }


}

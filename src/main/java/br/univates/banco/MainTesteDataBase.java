package br.univates.banco;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.persistence.DataBaseConnectionManager;
import br.univates.alexandria.persistence.DataBaseException;
import br.univates.banco.negocio.Correntista;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainTesteDataBase {
    public static void main(String[] args) {

        DataBaseConnectionManager db;

        try {
            db = new DataBaseConnectionManager(
                    DataBaseConnectionManager.POSTGRESQL,
                    "bancobyte",
                    "vinicius",
                    "123456");


            db.connectionTest();
            db.connectDataBase();

            ResultSet rs = db.runQuerySQL("SELECT * FROM correntista");

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String cpf = rs.getString("cpf");
                    String nome = rs.getString("nome");
                    String municipio = rs.getString("municipio");

                    Correntista corre = new Correntista(new Cpf(cpf), nome, municipio);
                    System.out.println("Correntista: " + nome + " (" + cpf + "), " + municipio);
                }
            }
            db.closeConnection();

        } catch (DataBaseException e) {
            System.out.println("Deu pau");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntryException e) {
            throw new RuntimeException(e);
        }


    }
}

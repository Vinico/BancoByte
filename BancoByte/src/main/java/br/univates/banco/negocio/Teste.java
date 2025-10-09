/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.univates.banco.negocio;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author mouriac
 */
public class Teste
{
    public static void main(String[] args)
    {
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int mes = Calendar.getInstance().get(Calendar.MONTH )+1;
        int ano = Calendar.getInstance().get(Calendar.YEAR );
        
        System.out.println(dia);
        System.out.println(mes);
        System.out.println(ano);
        
        Date dt1 = new Date( ano-1900, mes-1, dia );
        
        System.out.println( dt1.getDate() );
        System.out.println( dt1.getMonth()+1 );
        System.out.println( dt1.getYear()+1900 );

        
        LocalDate dt2 = LocalDate.of(ano, mes, dia);
        
        System.out.println( dt2.getDayOfMonth() );
        System.out.println( dt2.getMonthValue() );
        System.out.println( dt2.getYear() );
        
        
        
    }
}

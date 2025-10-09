package br.univates.alexandria;

public class Reservatorio
{
    private double nivel;
    private double capacidade;

    public Reservatorio( double capacidade_ )
    {
        nivel = 0;
        capacidade = capacidade_ > 0 ? capacidade_ : 500;
    }

    
    public double getNivel()
    {
        return nivel;
    }

    public double getCapacidade()
    {
        return capacidade;
    }
   
    public boolean encher(double quant)
    {
        boolean ok = false;
        if (quant > 0 && nivel + quant <= capacidade)
        {
            nivel += quant;
            ok = true;
        }
        return ok;
    }
    
    public void consumir(double quant)
    {
        if (quant > 0 && nivel >= quant)
        {
            nivel -= quant;
        }
    }
    
    public double getPercentualOcupado()
    {
        return (nivel / capacidade)*100;
    }
}

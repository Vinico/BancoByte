package br.univates.alexandria.interfaces;

public class Circulo extends Forma
{
    private double raio;

    public Circulo(double raio)
    {
        this.raio = raio;
    }
    
    
    
    @Override
    public double getArea()
    {
        return Math.pow(raio, 2)*Math.PI;
    }

    @Override
    public double getPerimetro()
    {
        return 2*Math.PI*raio;
    }
    
}

package br.univates.alexandria;

public class Util
{
    public static boolean verificarTriangulo(double a, double b, double c)
    {
        double maior = Math.max(a, Math.max(b,c) );
        double soma = a+b+c-maior;
        return (soma > maior);
    }
    
    public static double calcularAreaTriangulo(double a, double b, double c)
    {
        double area = -1;
        if (verificarTriangulo(a,b,c))
        {
            double sp = (a+b+c)/2;
            area = Math.sqrt( sp * (sp-a) * (sp-b) * (sp-c) );
        }
        return area;
    }
    
    
    public static int contarDivisores(int num)
    {
        int conta = 1;
        for (int i = 1; i <= num/2; i++)
        {
            if (num % i == 0)
            {
                conta ++;
            }
        }
        return conta;
    }
    
    public static boolean verificarPrimo(int num)
    {
        return (contarDivisores(num) == 2);
    }
    
    public static boolean verificarPrimoRelativo(int n1, int n2)
    {
        boolean primosRelativos = true;
        int menor = Math.min(n1, n2);
        for (int i = 2; i <= menor && primosRelativos; i++)
        {
            if (n1 % i == 0 && n2 % i == 0)
            {
                primosRelativos = false;
            }
        }
        return primosRelativos;
    }
    /*
    public static boolean verificarPar(int num)
    {
        boolean ok = false;
        if (num % 2 == 0) // par
        {
            ok = true;
        }
        return ok;
    }
    */
    
    public static boolean verificarPar(int num)
    {
        return(num % 2 == 0); // par
    }
    
    /*
    public static boolean verificarImpar(int num)
    {
        return(num % 2 != 0); // ímpar
    }
    */
    
    public static boolean verificarImpar(int num)
    {
        return !verificarPar(num);
    }
    
    public static double somarNumeros( double[] vetor )
    {
        // calcular a soma dos números do vetor
        double soma = 0;
        for (int i = 0; i < vetor.length; i++)
        {
            soma += vetor[i];
        }
        return soma;
    }
    
    public static double calcularMedia( double[] vetor )
    {
        // calcular a média dos números do vetor
        return somarNumeros(vetor) / vetor.length;
    }
    
    public static double extrairMaior( double[] vetor )
    {
        double maior = vetor[0];
        for (int i = 1; i < 10; i++)
        {
            maior = Math.max(maior, vetor[i]);
        }
        return maior;
    }
    
    public static double extrairMenor( double[] vetor )
    {
        double menor = vetor[0];
        for (int i = 1; i < 10; i++)
        {
            menor = Math.min(menor, vetor[i]);
        }
        return menor;
    }
    
    public static double calcularDesvioMedio( double[] vetor )
    {
        double media = calcularMedia(vetor);
        double somaDesvios = 0;
        for (int i = 0; i < 10; i++)
        {
            somaDesvios += Math.abs(media - vetor[i]);
        }
        double dm = somaDesvios / vetor.length;
        return dm;
    }
    
    public static double calcularVariancia( double[] vetor )
    {
        double media = calcularMedia(vetor);
        double somaQuadrados = 0;
        for (int i = 0; i < 10; i++)
        {
            somaQuadrados += Math.pow(media - vetor[i],2);
        }
        double variancia = somaQuadrados / vetor.length;
        return variancia;
    }
    
    
    public static double calcularDesvioPadrao( double[] vetor )
    {
        return Math.sqrt( calcularVariancia(vetor) );
    }
    
    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]","");
        if ((cpf == null) || (cpf.length() != 11)) {
            return false;
        }
        int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    public static boolean validarCNPJ(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]","");
        if ((cnpj == null) || (cnpj.length() != 14)) {
            return false;
        }
        int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }
    
    
    
}

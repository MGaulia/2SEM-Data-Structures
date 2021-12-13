
public class Preke implements Comparable {

    private int kodas;
    private String pavadinimas;
	
    public Preke() {}
    
    public Preke(int pKodas, String pPav) {
        if(pKodas < 0 || pPav == null)
            throw new NullPointerException();
        kodas = pKodas;
        pavadinimas = pPav;
    }
        
    @Override
    public String toString() { 
        return getKodas() + ":" + getPavadinimas();
    }

    /**
     * Natural comparator
     * Compares the receiving object (this) with the specified object p1
     * @param p1
     * @return -1, 0, 1
     */
    @Override
    public int compareTo(Object p1) {
        Preke p = (Preke) p1;
        if(this.kodas > p.kodas) 
            return 1;
        else if(this.kodas < p.kodas)
            return -1;
        else 
            return 0;
    }
    
    /**
     * @return the kodas
     */
    public int getKodas() {
        return kodas;
    }

    /**
     * @param kodas the kodas to set
     */
    public void setKodas(int kodas) {
        if(kodas < 0) {
            System.out.println("Preke.setKodas(): bandoma irasyti bloga prekes kodo reiksme: kodas=" + kodas);
            throw new NullPointerException();
        }
        this.kodas = kodas;
    }

    /**
     * @return the pavadinimas
     */
    public String getPavadinimas() {
        
        return pavadinimas;
    }

    /**
     * @param pavadinimas the pavadinimas to set
     */
    public void setPavadinimas(String pavadinimas) {
        if(pavadinimas == null) {
            System.out.println("Preke.setPavadinimas(): Blogas prekes pavadinimas=" + pavadinimas);
            throw new NullPointerException();
        }
        this.pavadinimas = pavadinimas;
    }
}

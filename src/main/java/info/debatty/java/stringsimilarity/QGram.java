package info.debatty.java.stringsimilarity;

/**
 * @author Thibault Debatty
 */
public class QGram extends SetBasedStringSimilarity {
    
    public static void main(String[] args) {
        QGram dig = new QGram(2);
        
        // AB BC CD CE
        // 1  1  1  0
        // 1  1  0  1
        // Total: 2
        //System.out.println(dig.absoluteDistance("ABCD", "ABCE"));
        
        // 2 / (3 + 3) = 0.33333
        System.out.println(dig.distance("ABCD", "ABCE"));
        
        System.out.println(dig.similarity("", "QSDFGHJKLM"));
        
        System.out.println(dig.similarity(
                "High Qua1ityMedications   Discount On All Reorders = Best Deal Ever! Viagra50/100mg - $1.85 071",
                "High Qua1ityMedications   Discount On All Reorders = Best Deal Ever! Viagra50/100mg - $1.85 7z3"));
    }
    
    
    /**
     * Q-gram similarity and distance.
     * Defined by Ukkonen in "Approximate string-matching with q-grams and maximal
     * matches", http://www.sciencedirect.com/science/article/pii/0304397592901434
     * The distance between two strings is defined as the L1 norm of the difference 
     * of their profiles (the number of occurences of each k-shingle).
     * Q-gram distance is a lower bound on Levenshtein distance, but can be computed
     * in O(|A| + |B|), where Levenshtein requires O(|A|.|B|)
     * 
     * @param n 
     */
    public QGram(int n) {
        super(n);
    }
    
    public QGram() {
        super(3);
    }

    @Override
    public double similarity(int[] profile1, int[] profile2) {
        int length = Math.max(profile1.length, profile2.length);
        profile1 = java.util.Arrays.copyOf(profile1, length);
        profile2 = java.util.Arrays.copyOf(profile2, length);
        
        int d = 0;
        for (int i = 0; i < length; i++) {
            d += Math.abs(profile1[i] - profile2[i]);
        }
        
        int sum = 0;
        for (int i : profile1) {
            sum += i;
        }
        for (int i : profile2) {
            sum += i;
        }
        
        return 1.0 - (double) d / sum;
    }
    
}

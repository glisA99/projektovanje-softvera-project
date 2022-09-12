package communication;

/**
 *
 * @author Ognjen Simic 2018/0093
 */
public class Operations {
    
    public static final int LOGIN = 1;
    
    // operations on client
    public static final int GET_ALL_CLIENTS = 2;
    public static final int GET_CLIENTS_CONDITIONAL = 3;
    public static final int CREATE_CLIENT = 4;
    public static final int SAVE_CLIENT = 5;
    
    // operations on artikl
    public static final int DELETE_ARTIKL = 6;
    public static final int CREATE_ARTIKL = 7;
    public static final int SAVE_ARTIKL = 8;
    public static final int FIND_ARTIKL = 9;
    public static final int SEARCH_ARTIKLS = 10;
    public static final int GET_ARTIKLS = 17;
    
    // operations on prodajna stavka
    public static final int DELETE_PRODAJNA_STAVKA = 11;
    public static final int CREATE_PRODAJNA_STAVKA = 12;
    public static final int SAVE_PRODAJNA_STAVKA = 13;
    public static final int SEARCH_PRODAJNE_STAVKE = 14;
    
    // operations on izvestaj
    public static final int CREATE_IZVESTAJ = 15;
    public static final int SAVE_IZVESTAJ = 16;
    
}

package prr.core;

import java.util.*;

public class SortId implements Comparator<Terminal>{
    public int compare(Terminal a, Terminal b) {
        //FIXME throw exception if both ID's are the same
        if ( a.getId() < b.getId() ) return 1;
        return -1;
        
    }
}
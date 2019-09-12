package src.filters;

import java.util.ArrayList;

public interface Filter {
    ArrayList filter(String filterType, String keyword, ArrayList list);

    ArrayList filter(String filterType, int keyword, ArrayList list);
}
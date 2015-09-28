/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faultlocalization;

/**
 *
 * @author Karini
 */
import java.util.Comparator;
import java.util.Map;

public class MapValueComparator implements Comparator<Long> {

    Map<Long, String> base;

    public MapValueComparator(Map<Long, String> base) {
        this.base = base;
    }

    public int compare(Long a, Long b) {
        return base.get(a).compareTo(base.get(b));
    }
}

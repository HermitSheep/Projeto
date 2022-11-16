package prr.core;

import java.util.Comparator;

public class ClientComparator  implements Comparator<Client> {
  public int compare(Client one, Client two) {
    return (int) ((two.getPayed() - two.getDebt()) - (one.getPayed() - one.getDebt()));
  }
}

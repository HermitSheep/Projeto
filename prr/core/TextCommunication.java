package core;

public class TextCommunication extends Communication{
  private String _message;

  public TextCommunication(String message) {
    _message = message;
  }

  protected double computeCost(TariffPlan plan) {
    //FIXME implement method
  }

  protected int getSize() {
    //FIXME implement method
  }
}

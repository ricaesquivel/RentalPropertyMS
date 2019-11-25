package client.controller;

public interface Subject {
	public void register(Observer o);
	public void notifyObserver();
}

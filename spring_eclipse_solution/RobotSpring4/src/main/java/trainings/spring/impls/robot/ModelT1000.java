package trainings.spring.impls.robot;

import trainings.spring.interfaces.IHand;
import trainings.spring.interfaces.IHead;
import trainings.spring.interfaces.ILeg;
import trainings.spring.interfaces.IRobot;

public class ModelT1000 implements IRobot {

	private IHead head;
	private IHand hand;
	private ILeg leg;

	private String color;
	private int year;
	private boolean soundEnabled;

	public ModelT1000(IHead head, IHand hand, ILeg leg) {
		super();
		this.head = head;
		this.hand = hand;
		this.leg = leg;
	}

	public ModelT1000(IHead head, IHand hand, ILeg leg, String color, int year, boolean soundEnabled) {
		super();
		this.head = head;
		this.hand = hand;
		this.leg = leg;
		this.color = color;
		this.year = year;
		this.soundEnabled = soundEnabled;
	}

	public ModelT1000(String color, int year, boolean soundEnabled) {
		super();
		this.color = color;
		this.year = year;
		this.soundEnabled = soundEnabled;
	}

	public void action() {
		head.thinking();
		hand.catching();
		leg.go();

		System.out.println("color: " + color);
		System.out.println("year: " + year);
		System.out.println("can play sound: " + soundEnabled);
	}

	public void dance() {
		System.out.println("T1000 is dancing!");
	}

	public ModelT1000() {
		super();
	}

	public IHead getHead() {
		return head;
	}

	public void setHead(IHead head) {
		this.head = head;
	}

	public IHand getHand() {
		return hand;
	}

	public void setHand(IHand hand) {
		this.hand = hand;
	}

	public ILeg getLeg() {
		return leg;
	}

	public void setLeg(ILeg leg) {
		this.leg = leg;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean isSoundEnabled() {
		return soundEnabled;
	}

	public void setSoundEnabled(boolean soundEnabled) {
		this.soundEnabled = soundEnabled;
	}

}

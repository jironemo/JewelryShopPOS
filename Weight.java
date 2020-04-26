
import java.util.Scanner;

/**
 * Weight
 */
public class Weight {

	private double Kyat, Pel, Yway;

	public Weight() {
		this.Kyat = 0.000;
		this.Pel = 0.000;
		this.Yway = 0.000;
	}

	public Weight(String c) {
		Scanner s = new Scanner(c);
		s.useDelimiter(",");
		this.Kyat = s.nextDouble();
		this.Pel = s.nextDouble();
		this.Yway = s.nextDouble();
		s.close(); 
	}

	public Weight(double Kyat, double Pel, double Yway) {
		this.Kyat = Kyat;
		this.Pel = Pel;
		this.Yway = Yway;
	}

	public double getKyat() {
		return (this.Kyat);
	}

	public double getPel() {
		return (this.Pel);
	}

	public double getYway() {
		return (this.Yway);
	}

	public String getString() {
		return (this.Kyat + "," + this.Pel + "," + this.Yway);
	}
}
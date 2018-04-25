import java.awt.Color;

// A colored segment in the plane
public class ColoredSegment2D {
	double x1, y1, x2, y2; // coordinates
	int f; // fatness
	Color color; // color
	
	ColoredSegment2D(double x1, double y1, double x2, double y2, int f, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.f = f;
		this.color = color;
	}
}

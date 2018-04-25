import java.awt.Color;

// A colored point in the plane
public class ColoredPoint2D {
	double x, y; // coordinates
	int r; // radius
	Color color; // color
	
	ColoredPoint2D(double x, double y, int r, Color color) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
	}
}

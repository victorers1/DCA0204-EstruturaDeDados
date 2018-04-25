import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.*;

// Fenetre pour visualisation 2D
public class Fenetre extends Canvas {

	private static final long serialVersionUID = 1L;
	private BufferedImage backgroudImage;
	protected Frame frame;

	// point and segment thickness
	public float pointThickness;
	public float segmentThickness;

	// points and segments to draw
	ConcurrentLinkedQueue<ColoredPoint2D> pointsToDraw = new ConcurrentLinkedQueue<ColoredPoint2D>();
	ConcurrentLinkedQueue<ColoredSegment2D> segmentsToDraw = new ConcurrentLinkedQueue<ColoredSegment2D>();

	// coordinates of the bounding box
	double xmin, xmax, ymin, ymax;

	// replace the graphics for the window to fit the bounding box
	protected void setTransform(Graphics2D g) {
		double sx = getWidth() / (xmax - xmin);
		double sy = getHeight() / (ymax - ymin);
		double s  = Math.min(sx, sy);
		g.scale(s,-s);
		g.translate(-(double)xmin, -(double)ymax);
		segmentThickness = (float) 0.3f/(float)s;
		pointThickness = (float) 1.f/(float)s;
		g.setStroke(new BasicStroke(segmentThickness));
	}

	// add a new point  
	public void addPoint(double x, double y, int r, Color color) {
		if(x < xmin || x > xmax || y < ymin || y > ymax) return;
		pointsToDraw.add(new ColoredPoint2D(x, y, r, color));
		repaint();
	}

	// add a new segment   
	public void addSegment(double x1, double y1, double x2, double y2, int f, Color color) {
		if((x1 < xmin || x1 > xmax || y1 < ymin || y1 > ymax) && (x2 < xmin || x2 > xmax || y2 < ymin || y2 > ymax)) return;
		segmentsToDraw.add(new ColoredSegment2D(x1, y1, x2, y2, f, color));
		repaint();
	}

	// draw a point on the graphics    
	void drawPoint(Graphics2D g, ColoredPoint2D p) {
		double radius = pointThickness * p.r;
		Ellipse2D e = new Ellipse2D.Double(p.x - radius, p.y - radius, 2*radius, 2*radius);
		g.setColor(p.color);
		g.fill(e);
		g.draw(e);
	}

	// draw a segment on the graphics    
	void drawSegment(Graphics2D g, ColoredSegment2D s) {
		g.setColor(s.color);
		g.setStroke(new BasicStroke(segmentThickness*s.f));
		g.draw(new Line2D.Double(s.x1, s.y1, s.x2, s.y2));
	}

	// draw points and segments waiting
	protected void drawContent(Graphics2D g) {
		// add the segments to draw
		while (!segmentsToDraw.isEmpty())
			drawSegment(g, segmentsToDraw.poll());
		// add the points to draw
		while (!pointsToDraw.isEmpty())
			drawPoint(g, pointsToDraw.poll());
	}

	// paint the graphics
	public void paint(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		// place the background
		if (this.backgroudImage != null) {
			displayImage(g, this.backgroudImage, 0, 0);
		}
		// replace the window on the desired bounding box
		setTransform(g);
		// draw the content
		drawContent(g);
	}

	// update window
	public void update(Graphics graphics) {
		Graphics2D g = (Graphics2D) graphics;
		// replace the window on the desired bounding box
		setTransform(g);
		// draw the content
		drawContent(g);
	}

	// Display image
	public void displayImage (Graphics2D g, BufferedImage bimg, int x, int y) {
		g.drawImage(bimg, null, x, y);
	}

	// set background image
	public static BufferedImage loadImage(String fileName) {
		System.out.print("Carregando imagem de background do arquivo " + fileName + " ... ");
		BufferedImage bimg = null;  
		try {bimg = ImageIO.read(new File(fileName));} catch (Exception e) {e.printStackTrace();}
		System.out.println(" done");
		return bimg;  
	}  

	// creates a new window
	public Fenetre() {
		frame = new Frame("Fenetre");
		frame.add(this);
		frame.setSize(400, 400);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	// creates a new window with a given title
	public Fenetre(String title) {
		frame = new Frame(title);
		frame.add(this);
		frame.setSize(400, 400);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	// Creates a new window with a given title, background image, and bounding box
	public Fenetre(String imageName, String title, double xmin, double xmax, double ymin, double ymax) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		BufferedImage bimg = loadImage(imageName);
		int w = bimg.getWidth();
		int h = bimg.getHeight();
		this.backgroudImage = bimg;
		frame = new Frame(title);
		frame.add(this);
		frame.setSize(w, h);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}

	// Creates a new window with a given title and bounding box
	public Fenetre(String title, double xmin, double xmax, double ymin, double ymax, int size) {
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		frame = new Frame(title);
		frame.add(this);
		frame.setSize(size, (int) (size*(ymax-ymin) / (xmax-xmin)));
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setVisible(true);
	}
}
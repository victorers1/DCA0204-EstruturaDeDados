
import java.awt.*;
import java.awt.event.*;

/**
 * @author C. Durr (2008), Luca Castelli Aleardi (INF421, 2013, Ecole Polytechnique) Provides methods for testing operations on ordered sets
 * (implementation based on binary search trees)
 */
class DArbre {

    static final int d = 6, rad = 1;

    int lw, rw;
    String v;
    Color c;
    DArbre l, r;

    DArbre(DArbre _l, String _v, Color _c, DArbre _r) {
        l = _l;
        c = _c;
        v = _v;
        r = _r;
        lw = (l == null) ? 0 : l.lw + l.rw + d / 2;
        rw = (r == null) ? 0 : r.lw + r.rw + d / 2;
    }

    static DArbre copyArbre(Aa a) {
        if (a == null) {
            return null;
        } else {
            return new DArbre(copyArbre(a.esq), "" + a.value, a.cor == Aa.R ? Color.red : Color.black, copyArbre(a.dir));
        }
    }

    void paint(Graphics g, int x, int y, int f, boolean showl) {
        int xx, yy;
        if (l != null) {
            xx = x - d / 2 - l.rw;
            yy = y + d;
            g.drawLine(x * f, y * f, xx * f, yy * f);
            l.paint(g, xx, yy, f, showl);
        }
        if (r != null) {
            xx = x + d / 2 + r.lw;
            yy = y + d;
            g.drawLine(x * f, y * f, xx * f, yy * f);
            r.paint(g, xx, yy, f, showl);
        }
        g.setColor(showl ? Color.white : c);
        g.fillOval((x - rad) * f, (y - rad) * f, 2 * rad * f, 2 * rad * f);
        if (showl) {
            g.setColor(c);
            FontMetrics m = g.getFontMetrics();
            String label = "" + v;
            g.drawOval((x - rad) * f, (y - rad) * f, 2 * rad * f, 2 * rad * f);
            g.drawString(label,
                    x * f - (int) (label.length() * m.charWidth('0') / 2.),
                    y * f + m.getAscent() / 2);
        }
        g.setColor(Color.black);
    }
}

@SuppressWarnings("serial")
class Fenetre extends Canvas {

    DArbre a;

    static int compteur = 1;

    Fenetre(Aa _a, String title) {
        a = DArbre.copyArbre(_a);
        Frame f = new Frame(title);
        f.add(this);
        f.setSize(600, 600);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        f.setVisible(true);
    }

    public void paint(Graphics g) {
        if (a != null) {
            int f = getWidth() / (a.lw + 2 * DArbre.d + a.rw);
            int x = a.lw + DArbre.d;
            int y = DArbre.d;
            a.paint(g, x, y, Math.max(f, 1), f > 6);
        }
    }
}

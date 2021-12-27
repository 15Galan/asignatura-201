import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * @author Pepe Gallardo
 *
 * Clases para pintar gr\u00E1ficas
 */

public class Grafica extends JFrame {
	
	static private List<Color> colores = 
		Arrays.asList(new Color[] {Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA, Color.ORANGE, Color.CYAN});

	private List<Linea> lineas;
	
	private double maxX=0, maxY=0, stepX=1, stepY=1;
	private String labelX, labelY, formatX, formatY;
	int ticksX0=10, ticksY0=10, ticksX, ticksY;
	
	public Grafica(String titulo, String labelX, String labelY, String formatX, String formatY) {
		super(titulo);
		this.labelX = labelX;
		this.labelY = labelY;
		this.formatX = formatX;
		this.formatY = formatY;
		lineas = new LinkedList<Linea>();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new GraficaPane());
		pack();
		setVisible(true);

	}

	
	public void repinta() {
		maxX = 0.0;
		maxY = 0.0;

		for(Linea l : lineas) {

			Vector<Double> vx = l.vx, vy = l.vy;

			if(vx.size()>0)
				maxX = Math.max(maxX, Collections.max(vx).doubleValue());
			if(vy.size()>0)	
				maxY = Math.max(maxY, Collections.max(vy).doubleValue());
		}
 
		if(maxX<ticksX0)
			ticksX = (int)maxX;
		else 
			ticksX = ticksX0;	
			 		
		ticksY = ticksY0;	

		stepX = maxX/ticksX;
		stepY = maxY/ticksY;
		
		repaint();
	}

	
	public class Linea {
		
		private Vector<Double> vx, vy;
		private String etiqueta;
		private Color color;
		private float ancho;
		
		public Linea(String etiqueta) {
			vx = new Vector<Double>();
			vy = new Vector<Double>();
			this.etiqueta = etiqueta;
			color = colores.get(lineas.size()%colores.size());
			lineas.add(this);
			ancho = 1.5f;
		}
		
		public void setColor(Color c) {
			color = c;
		}

		public void addData(double x, double y) {
			vx.add(x);
			vy.add(y);
			repinta();
		}
	
	}
	
	public class GraficaPane extends JPanel {
	
		int lX = 800, lY = 500;
		int borderX = 75, borderY = 50;
		
		public GraficaPane() {
			super();
			setBackground(Color.WHITE);
			setPreferredSize(new Dimension(lX,lY));
		}
	
		int coordX(int x) {
			return (x)+borderX;
		}
		
		int coordY(int y) {
			return (borderY+lY-y);
		}
		
	
		int pixelX(double x) {
			double fx = (double)lX / (double)maxX;
			return (int)(x*fx)+borderX;
		}
	
		int pixelY(double y) {
			double fy = (double)lY/(double)maxY;
			return  (borderY+lY) - (int)(y*fy);
		}


		int pixelX(int x) {
			return pixelX((double)x);
		}

		int pixelY(int y) {
			return pixelY((double)y);
		}
	
	
		public int anchoString(Graphics gIn, String s) {
			   Graphics2D g = (Graphics2D) gIn;	
				
			   Font font = g.getFont();
			   FontRenderContext frc = g.getFontRenderContext();
			   TextLayout layout = new TextLayout(s, font, frc);
			   Rectangle2D bounds = layout.getBounds();
			   return (int) bounds.getWidth();
			
		}
		
		public void drawStringTopCenter(Graphics gIn, String s, int x, int y) {
			Graphics2D g = (Graphics2D) gIn;	
	
			Font font = g.getFont();
			FontRenderContext frc = g.getFontRenderContext();
			TextLayout layout = new TextLayout(s, font, frc);
			Rectangle2D bounds = layout.getBounds();
			layout.draw(g, (float)(x-bounds.getCenterX()), (float)(y+bounds.getHeight()));
		}
	
		public void drawStringCenterRight(Graphics gIn, String s, int x, int y) {
			   Graphics2D g = (Graphics2D) gIn;	
	
			   Font font = g.getFont();
			   FontRenderContext frc = g.getFontRenderContext();
			   TextLayout layout = new TextLayout(s, font, frc);
			   Rectangle2D bounds = layout.getBounds();
			   layout.draw(g, (float)(x-bounds.getWidth()), (float)(y-bounds.getCenterY()));
			}

		
		private void pintaEtiquetas(Graphics gr, int posX, int posY0) {
			Graphics2D gr2 = (Graphics2D) gr;
			int longLinea = 15, sepY=20, sepX=10;
			int posY =posY0;
			int maxAnchoEtiquetas = 0;
			
			for(Linea l : lineas) {
				gr2.setColor(l.color);
			    gr2.setStroke(new BasicStroke(l.ancho));
				gr2.drawLine(coordX(posX), coordY(+sepY/3+posY), coordX(longLinea+posX), coordY(+sepY/3+posY));

				gr2.setColor(Color.BLACK);
				gr2.drawString(l.etiqueta, coordX(posX+longLinea+sepX), coordY(posY));
				maxAnchoEtiquetas = Math.max(maxAnchoEtiquetas, anchoString(gr2,l.etiqueta));
				
				
				posY -= sepY;
			}	
			
			gr2.setStroke(new BasicStroke(1.0f));
			gr2.setColor(Color.BLACK);
			gr.drawRect(coordX(posX-sepX), coordY(posY0+sepY), 3*sepX+longLinea+maxAnchoEtiquetas, sepY*(1+lineas.size()));

			
			
		}
		
		public void paintComponent(Graphics gr) {
			super.paintComponent(gr);
			Graphics2D gr2 = (Graphics2D) gr;
			double x,y;

	
			Dimension dim = getSize(null);
			lX = (int) dim.getWidth() - 4*borderX;
			lY = (int) dim.getHeight() - 2*borderY;
	
			gr2.setStroke(new BasicStroke(3.0f));
			gr2.setColor(Color.BLACK);
			gr2.drawLine(coordX(0),coordY(0),coordX(2+lX),coordY(0));
			gr2.setColor(Color.BLACK);
			gr2.drawLine(coordX(0),coordY(0),coordX(0),coordY(2+lY));
	
			gr2.setStroke(new BasicStroke(1.0f));
			gr2.drawString(labelX, coordX(15+lX), coordY(0));
			drawStringTopCenter(gr2, labelY, coordX(0), coordY(25+lY));
			
			for(int i=0; i<=ticksX; i++) {
				x = i*stepX;
				drawStringTopCenter(gr, String.format(formatX,x), pixelX(x), coordY(-10));
				gr.drawLine(pixelX(x),coordY(-3),pixelX(x),coordY(3));
			}	
			
			for(int i=0; i<=ticksY; i++) {
				y = i*stepY;
				drawStringCenterRight(gr, String.format(formatY,y), coordX(-10), pixelY(y));
				gr.drawLine(coordX(-3),pixelY(y),coordX(3),pixelY(y));
			}
	
			
			for(Linea l : lineas) {

				Vector<Double> vx = l.vx;
				Vector<Double> vy = l.vy;

				gr2.setColor(l.color);
			    gr2.setStroke(new BasicStroke(l.ancho));

				for(int i=0; i<vx.size(); i++) {
					int x0, y0, x1, y1;
				    x0 = pixelX(vx.get(i).doubleValue());
				    y0 = pixelY(vy.get(i).doubleValue());
				    
				    gr2.setStroke(new BasicStroke(1.0f));
					gr2.drawOval(x0-2,y0-2,4, 4);

					if(i<vx.size()-1) {
					    x1 = pixelX(vx.get(i+1).doubleValue());
					    y1 = pixelY(vy.get(i+1).doubleValue());
	
					    gr2.setStroke(new BasicStroke(l.ancho));
						gr2.drawLine(x0,y0,x1,y1);
					}	
				}	
			}
			
			pintaEtiquetas(gr2,25+lX,lY-20);
			
	
		}	
		
	}
}
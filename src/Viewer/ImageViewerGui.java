package Viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageViewerGui extends JFrame {

	private JPanel contentPane;
    private int position=1;
    private Loader loader;
    
    private JLabel lblNewLabel;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    
    private ArrayList<JLabel> picLabels;
    private ArrayList<JLabel> digitLabels;
    
	/**
	 * Create the frame.
	 */
	public ImageViewerGui(Loader l) {
		setResizable(false);
		loader=l;
		setTitle("Digits Browser");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        picLabels = new ArrayList<JLabel>();
        digitLabels = new ArrayList<JLabel>();
        for(int i=0;i<15;i++){
        	picLabels.add(new JLabel());
        	digitLabels.add(new JLabel());
        }
        
        for(JLabel j:picLabels){
        	contentPane.add(j);
        }
        for(JLabel j:digitLabels){
        	contentPane.add(j);
        	j.setHorizontalAlignment(SwingConstants.CENTER);
        }
        	
		JButton btnNewButton = new JButton("<<");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(position>=15){
					position-=15;
					refresh(position);
					lblNewLabel.setText(Integer.toString(position)+" / "+loader.getDataLength()/15);
					}
			}
		});
		btnNewButton.setBounds(86, 367, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(">>");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(position<loader.getDataLength()/15){
				position+=15;
				refresh(position);
				lblNewLabel.setText(Integer.toString(position)+" / "+loader.getDataLength()/15);
				}
			}
		});
		btnNewButton_1.setBounds(230, 367, 89, 23);
		contentPane.add(btnNewButton_1);
		
		lblNewLabel = new JLabel(Integer.toString(position)+" / "+loader.getDataLength()/15);
		lblNewLabel.setBounds(348, 371, 58, 19);
		contentPane.add(lblNewLabel);

		for(int i=0;i<5;i++){
			drawImage(i+1, 40+(70*i), 40);
		}
		for(int i=5;i<10;i++){
			drawImage(i+1, 40+(70*(i-5)), 140);
		}
		for(int i=10;i<15;i++){
			drawImage(i+1, 40+(70*(i-10)), 240);
		}
		
	}

	private void drawImage(int position, int x, int y){
		
	    BufferedImage img = new BufferedImage(28, 28, BufferedImage.TYPE_BYTE_INDEXED);
		img.setRGB(0, 0,28,28,loader.getImageWithIndex(position),0,28);
		BufferedImage img2 = new BufferedImage(60, 60, BufferedImage.TYPE_BYTE_INDEXED);
		Graphics2D g2d = img2.createGraphics();
        g2d.drawImage(img, 0, 0, 60, 60, null);
        g2d.dispose();
		picLabels.get(position%15).setBounds(x, y, 60, 60);
		picLabels.get(position%15).setIcon(new ImageIcon(img2));
		digitLabels.get(position%15).setBounds(x, y+70, 60, 14);
		digitLabels.get(position%15).setText(Integer.toString(loader.getImageLabelWithIndex(position)));

	}
	private void refresh(int position){

		for(int i=0;i<5;i++){
			drawImage(position+i, 40+(70*i), 40);
		}
		for(int i=5;i<10;i++){
			drawImage(position+i, 40+(70*(i-5)), 140);
		}
		for(int i=10;i<15;i++){
			drawImage(position+i, 40+(70*(i-10)), 240);
		}
		 
	}
}

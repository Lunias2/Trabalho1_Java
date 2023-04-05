package telas;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;


public class TelaInicial extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7189004984357543062L;
	private String rolagem = "Rolagens: \n\n";
	private int contador = 0;
	private JPanel contentPane;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
			}
	}

	/**
	 * Create the frame.
	 */
	public TelaInicial() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu Jogar = new JMenu("Jogar");
		menuBar.add(Jogar);
		
		JMenuItem serHost = new JMenuItem("Ser Host");
		serHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InetAddress enderecoHost = InetAddress.getLocalHost();
					JOptionPane.showMessageDialog(serHost, enderecoHost.getHostAddress());
				} catch (UnknownHostException hostErro) {
				}
			}
		});
		Jogar.add(serHost);
		
		JMenuItem conectar = new JMenuItem("Conectar");
		conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String ip = JOptionPane.showInputDialog(null, "Digite o endereço IP da máquina que se deseja conectar");
					InetAddress enderecoHost = InetAddress.getByName(ip);
					JOptionPane.showMessageDialog(conectar, "Conectado ao endereço" + enderecoHost.getHostAddress());
				}catch ( UnknownHostException host){
					JOptionPane.showMessageDialog(conectar, "Endereco invalido. \nErro: " + host.toString() );
					
				}
				
			}
		});
		Jogar.add(conectar);
		
		JMenu regra = new JMenu("Regras");
		menuBar.add(regra);
		
		JMenuItem MenuItemRegras = new JMenuItem("Visualizar Regras");
		MenuItemRegras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(conectar, "O Ludo é um jogo de tabuleiro para dois a quatro jogadores, que se movem em torno do tabuleiro tentando levar todas as suas peças do início até a base.\n As regras do Ludo são simples:\r\n"
						+ "\r\n"
						+ "1 - Cada jogador começa com quatro peças na área de início do tabuleiro.\r\n"
						+ "2 - O objetivo do jogo é mover todas as suas peças da área de início até a base, passando pelo caminho em volta do tabuleiro.\r\n"
						+ "3 - Os jogadores usam um dado para determinar quantos espaços podem ser movidos em cada jogada.\r\n"
						+ "4 - As peças só podem ser movidas para a frente, para um espaço vazio ou ocupado por uma peça adversária.\r\n"
						+ "5 - Se uma peça é movida para um espaço ocupado por uma peça adversária, a peça adversária volta para a área de início.\r\n"
						+ "6 - Se um jogador rola um seis no dado, ele pode mover uma peça para fora da área de início.\r\n"
						+ "7 - Um jogador só pode mover uma peça se tiver rolado um número correspondente no dado.\r\n"
						+ "8 - O primeiro jogador a levar todas as suas peças para a base vence o jogo.\r\n");
			}
		});
		regra.add(MenuItemRegras);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel painelJogo = new JPanel();
		painelJogo.setBounds(0, 0, 306, 214);
		contentPane.add(painelJogo, BorderLayout.WEST);
		painelJogo.setLayout(null);
		
		JPanel painelBotao = new JPanel();
		contentPane.add(painelBotao, BorderLayout.SOUTH);
		
		JScrollPane scrollTexto = new JScrollPane();
		contentPane.add(scrollTexto, BorderLayout.EAST);
		
		
		textArea = new JTextArea();
		textArea.setForeground(Color.yellow);
		textArea.setEditable(false);
		textArea.setEnabled(false);
		textArea.setBounds(0, 0, 5, 22);
		scrollTexto.setViewportView(textArea);
		scrollTexto.setVisible(true);
		
		textArea.setText(rolagem);
		
		textArea.setBackground(new Color(128, 128, 128));
		
		JButton rolarDadosButton = new JButton("Dado");
		rolarDadosButton.setBounds(432, 220, 109, 23);
		painelBotao.add(rolarDadosButton);
		rolarDadosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random dado = new Random();
				rolagem += "Jogador[" + (contador + 1) + "] rolou [" + (dado.nextInt(6) + 1) + "]\n";
				textArea.setText(rolagem);
				contador++;
				if(contador == 8)
					contador = 0;
			}
		});
		
		
	}
}
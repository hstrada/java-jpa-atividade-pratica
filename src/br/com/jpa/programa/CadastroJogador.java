package br.com.jpa.programa;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.jpa.entity.Jogador;
import br.com.jpa.entity.Time;
import br.com.jpa.helper.Helper;

public class CadastroJogador {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();

		Helper dao = new Helper(em);
		
		List<Time> times = null;
		try {
			times = dao.listarTimes();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		while (true) {

			try {
				String nome = showInputDialog("Informe a Nome: ");
				String posicao = showInputDialog("Informe a posição: ");
				Time time = (Time) showInputDialog(null, "Selecione o time: ", "Time",
						JOptionPane.DEFAULT_OPTION, null, times.toArray(), null);

				Jogador jogador = new Jogador();
				jogador.setNome(nome);
				jogador.setPosicao(posicao);
				jogador.setTime(time);
				time.getJogadores().add(jogador);
				
				dao.salvarJogador(jogador);
				showMessageDialog(null, "Jogador incluído com sucesso.");

			} catch (Exception e) {
				showMessageDialog(null, "ERRO: " + e.getMessage());
			}

			int opcao = showConfirmDialog(null, "Deseja continuar?", "Confirma", JOptionPane.YES_NO_OPTION);
			if (opcao != JOptionPane.YES_OPTION) {
				break;
			}
		}
	}
}

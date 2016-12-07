package br.com.jpa.programa;

import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import br.com.jpa.entity.Federacao;
import br.com.jpa.entity.Time;
import br.com.jpa.helper.Helper;

public class CadastroTime {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaPU");
		EntityManager em = emf.createEntityManager();

		Helper dao = new Helper(em);
		
		List<Federacao> federacoes = null;
		try {
			federacoes = dao.listarFederacao();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		while (true) {

			try {
				String nome = showInputDialog("Informe a Nome: ");
				Federacao federacao = (Federacao) showInputDialog(null, "Selecione a federação: ", "Federações",
						JOptionPane.DEFAULT_OPTION, null, federacoes.toArray(), null);

				Time time = new Time();
				time.setNome(nome);
				time.setFederacao(federacao);
				federacao.getTimes().add(time);
				
				dao.salvarTime(time);
				showMessageDialog(null, "Time incluído com sucesso.");

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

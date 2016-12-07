package br.com.jpa.helper;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jpa.entity.Federacao;
import br.com.jpa.entity.Jogador;
import br.com.jpa.entity.Time;

public class Helper {

	private EntityManager em;

	public Helper(EntityManager em) {
		this.em = em;
	}

	public void salvarFederacao(Federacao federacao) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(federacao);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Federacao> listarFederacao() throws Exception {
		Query query = em.createQuery("select f from Federacao f");
		return query.getResultList();
	}

	public void salvarTime(Time time) {
		try {
			em.getTransaction().begin();
			em.persist(time);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Time> listarTimes() throws Exception {
		Query query = em.createQuery("select t from Time t");
		return query.getResultList();
	}

	public void salvarJogador(Jogador jogador) {
		try {
			em.getTransaction().begin();
			em.persist(jogador);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw e;
		}
	}
}

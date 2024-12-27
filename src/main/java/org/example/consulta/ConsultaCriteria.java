package org.example.consulta;

import org.example.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import org.example.modelo.Carro;
import org.example.modelo.Marca;


public class ConsultaCriteria {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Carro> query = cb.createQuery(Carro.class);
        Root<Carro> carroRoot = query.from(Carro.class);
        Join<Carro, Marca> marcaJoin = carroRoot.join("marca");

        query.select(carroRoot).where(cb.equal(marcaJoin.get("nome"), "Toyota"));

        em.createQuery(query).getResultList().forEach(carro ->
                System.out.println(carro.getModelo())
        );

        em.getTransaction().commit();
        em.close();
    }
}

package org.example;

import jakarta.persistence.EntityManager;
import org.example.modelo.Marca;
import org.example.modelo.Acessorio;
import org.example.modelo.Carro;
import org.example.util.JPAUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Marca toyota = new Marca();
        toyota.setNome("Toyota");

        Carro corolla = new Carro();
        corolla.setModelo("Corolla");
        corolla.setMarca(toyota);

        Acessorio arCondicionado = new Acessorio();
        arCondicionado.setDescricao("Ar Condicionado");

        corolla.setAcessorios(List.of(arCondicionado));

        EntityManager em = JPAUtil.getEntityManager();
        em.getTransaction().begin();

        em.persist(toyota);
        em.persist(corolla);
        em.persist(arCondicionado);

        em.getTransaction().commit();
        em.close();
    }
}
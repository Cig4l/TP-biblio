package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        Livre livre = em.find(Livre.class, 1); // Remplace 1 par l'id à tester
        if (livre != null) {
            System.out.println("Livre trouvé : " + livre.getTitre() + " par " + livre.getAuteur() + "\n");
            Integer nbEmprunts = livre.getEmprunts().size();
            System.out.println("Emprunté " + nbEmprunts + " fois");
        } else {
            System.out.println("Aucun livre trouvé pour cet id.\n");
        }

        Emprunt emprunt = em.find(Emprunt.class, 1);
        if(emprunt != null){
            System.out.println("Emprunt trouvé");
            System.out.println("Nom du client : " + emprunt.getClient().getPrenom() + " " + emprunt.getClient().getNom());
            System.out.println();
            System.out.println("Début de l'emprunt : " + emprunt.getDateDebut());
            System.out.println("Fin de l'emprunt : " + emprunt.getDateFin());
            System.out.println("Délai : " + emprunt.getDelai() + " jours\n");
        } else {
            System.out.println("Aucun emprunt trouvé pour cet id.\n");
        }

        Client client = em.find(Client.class, 1);
        if(client != null){
            int empruntsSize = client.getEmprunts().size();
            System.out.println("Client trouvé : " + client.getPrenom() + " " + client.getNom());
            System.out.println("Nombre d'emprunts : " + empruntsSize);
            for(int i=0; i<empruntsSize; i++){
                System.out.println(client.getEmprunts().get(i).getLivres().get(i).getTitre() + " de " + client.getEmprunts().get(i).getLivres().get(i).getAuteur());
            }
        } else {
            System.out.println("Aucun client trouvé pour cet id.\n");
        }

        em.close();
        emf.close();
    }
}
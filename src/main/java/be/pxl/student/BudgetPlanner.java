package be.pxl.student;

import be.pxl.student.util.BudgetPlannerImporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Paths;

public class BudgetPlanner {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;

        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("budgetPlannerdb");
            entityManager = entityManagerFactory.createEntityManager();
            BudgetPlannerImporter budgetPlannerImporter = new BudgetPlannerImporter(entityManager);
            budgetPlannerImporter.importCsv(Paths.get("src/main/resources/account_payments.csv"));
        }
        finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
    }
}
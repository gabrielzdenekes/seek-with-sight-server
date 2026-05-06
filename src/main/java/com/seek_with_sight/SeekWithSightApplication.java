package com.seek_with_sight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

@SpringBootApplication
public class SeekWithSightApplication {
	public class prueba_sonarqube {

		// Vulnerabilidad: Campo estático público (debería ser final o privado)
		public static String connectionString = "jdbc:mysql://localhost:3306/db?user=admin&password=12345";

		// Code Smell: Constructor vacío innecesario o mala práctica de visibilidad
		public prueba_sonarqube() { }

		public void MetodoMuyLargoYMalNombrado(int a, int b, String dato) {

			// Bug: Posible NullPointerException (no se chequea si 'dato' es null antes de usarlo)
			if (dato.equals("test")) {
				System.out.println("Es un test");
			}

			// Code Smell: Variable local no utilizada
			int variableInutil = 42;

			// Bug/Code Smell: Uso de Random inadecuado para seguridad
			Random r = new Random();
			int randomVal = r.nextInt();

			// Vulnerabilidad: SQL Injection (Concatenación directa de strings)
			try {
				Connection conn = DriverManager.getConnection(connectionString);
				Statement stmt = conn.createStatement();
				String query = "SELECT * FROM users WHERE id = " + dato; // ¡Peligro!
				ResultSet rs = stmt.executeQuery(query);
			} catch (Exception e) {
				// Code Smell: Capturar Exception genérica
				// Code Smell: Empty catch block (no se hace nada con el error)
			}
		}

		public int divisionPorCero() {
			// Bug: Error aritmético garantizado
			return 10 / 0;
		}

		private void metodoNoUsado() {
			// Code Smell: Los métodos privados que no se usan deben eliminarse
			System.out.println("Nadie me llama");
		}

		public boolean comparacionInutil() {
			// Code Smell: Expresión lógica que siempre devuelve lo mismo
			if (true == true) {
				return true;
			}

			// Bug: Código inalcanzable (Dead Code)
			return false;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SeekWithSightApplication.class, args);
	}
}

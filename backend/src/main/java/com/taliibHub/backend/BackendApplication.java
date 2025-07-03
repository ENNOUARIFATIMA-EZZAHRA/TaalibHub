package com.taliibHub.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.taliibHub.backend.repository.UtilisateurRepository;
import com.taliibHub.backend.repository.EtudiantRepository;
import com.taliibHub.backend.model.Utilisateur;
import java.util.*;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Component
	class DuplicateCleaner implements CommandLineRunner {
		@Autowired
		private UtilisateurRepository utilisateurRepository;
		@Autowired
		private EtudiantRepository etudiantRepository;
		@Override
		public void run(String... args) {
			List<Utilisateur> allUsers = utilisateurRepository.findAll();
			Map<String, Utilisateur> uniqueByEmail = new HashMap<>();
			List<String> idsToDelete = new ArrayList<>();
			for (Utilisateur u : allUsers) {
				String email = u.getEmail();
				if (uniqueByEmail.containsKey(email)) {
					idsToDelete.add(u.getId());
				} else {
					uniqueByEmail.put(email, u);
				}
			}
			idsToDelete.forEach(id -> {
				try { etudiantRepository.deleteById(id); } catch (Exception ignored) {}
			});
			idsToDelete.forEach(id -> {
				try { utilisateurRepository.deleteById(id); } catch (Exception ignored) {}
			});
			if (!idsToDelete.isEmpty()) {
				System.out.println("Doublons supprimés (ids): " + idsToDelete);
			}
		}
	}

}

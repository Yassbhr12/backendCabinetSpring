# Tests Postman – backendCabinetSpring

## 1) Démarrer l’API
- Lancer l’application et la laisser tourner sur le port `8080`.
- Vérifier que MySQL tourne et que la DB est accessible.

## 2) Importer Postman
Importer les deux fichiers :
- [postman/backendCabinetSpring.postman_collection.json](postman/backendCabinetSpring.postman_collection.json)
- [postman/backendCabinetSpring.postman_environment.json](postman/backendCabinetSpring.postman_environment.json)

Puis sélectionner l’environment **backendCabinetSpring-local**.

### Identifiants de test (créés au démarrage si la table `utilisateur` est vide)
Ces users sont créés par [src/main/java/net/cabinet/backendspring/helper/DataInitializer.java](src/main/java/net/cabinet/backendspring/helper/DataInitializer.java) uniquement si `utilisateurRepository.count() == 0`.
- ADMIN: login `bahraahmedyassine@gmail.com` / password `Yassbhr12`
- MEDECIN: login `yassinebhr475@gmail.com` / password `AlamiDr12`
- SECRETAIRE: login `secretaire` / password `secretaire12`

## 3) Auth (obligatoire avant le reste)
Dans Postman → dossier **Auth** :
1. **Login (send validation code)**
   - Renseigner les variables `login` et `password` dans l’environment.
   - En prod, cette route envoie un code de validation par email (valable 3 minutes).
   - En mode test local (si `app.mail.enabled=false` et `app.auth.returnValidationCode=true`), la réponse contient directement `validationCode`.
2. Mettre le code dans `validationCode`.
3. **Validate code (get JWT)**
   - Le test Postman sauvegarde automatiquement le token dans `token`.
4. **Me**
   - Doit renvoyer l’utilisateur courant.

## 4) Rôles / accès (selon la sécurité)
- ADMIN: `/api/utilisateurs/**`, `/api/medicaments/**`
- ADMIN, MEDECIN, SECRETAIRE: `/api/patients/**`, `/api/rendez-vous/**`
- ADMIN, SECRETAIRE: `/api/factures/**`
- ADMIN, MEDECIN: `/api/dossier-medicaux/**`

Astuce: dans **Utilisateurs (ADMIN only)** → **Get all utilisateurs**, un script essaye de remplir `medecinId` automatiquement si un utilisateur `role=MEDECIN` existe.

## 5) Flow de test conseillé (du plus simple au plus complet)
1. Auth → obtenir `token`
2. Patients → **Create patient** (remplit `patientId`)
3. Utilisateurs → **Get all utilisateurs** (remplit `medecinId` si possible)
4. Rendez-vous → **Create rendez-vous** (remplit `rendezVousId`)
5. Consultations → **Create consultation** (remplit `consultationId`)
6. Factures → **Create facture** (remplit `factureId`)
7. Dossier medicaux → **Create dossier medical** (remplit `dossierMedicalId`)

## 6) Notes importantes
- Les routes `/api/cabinets/**` et `/api/notifications/**` sont référencées dans la config sécurité, mais aucun controller correspondant n’existe actuellement dans le projet (donc pas testable via API REST).
- Correction appliquée côté API : l’endpoint `POST /api/utilisateurs` accepte maintenant **un seul body** (wrapper JSON) pour pouvoir être testable dans Postman.

## 7) Dépannage rapide
### 401 + "Utilisateur Not Found" sur `/api/auth/login`
- Vérifie que tu as bien sélectionné l’environment Postman **backendCabinetSpring-local** (dans ta capture tu es en **No environment**).
   - Sinon Postman envoie littéralement `"login": "{{login}}"` et forcément l’utilisateur n’existe pas.
- Vérifie que `login` et `password` sont remplis dans l’environment.
- Si la base contient déjà des utilisateurs, `DataInitializer` ne crée pas les comptes de test. Dans ce cas:
   - utilise un login déjà présent dans ta DB, ou
   - vide la table utilisateurs (si c’est une DB de dev), puis redémarre l’API.

# Documentation UML - Cabinet Médical

## Vue d'ensemble

Ce document fournit un aperçu complet des diagrammes UML créés pour le système de gestion de cabinet médical. Tous les diagrammes sont disponibles dans le répertoire `/diagrams`.

## 📂 Contenu du répertoire diagrams/

### 1. Diagramme de Classe
**Fichier:** `diagrams/class-diagram.puml`

Représentation complète du modèle de données avec:
- 11 entités principales (Cabinet, Utilisateur, Patient, RendezVous, Consultation, DossierMedical, Facture, Medicament, Notification)
- 8 énumérations (Role, Statut, TypeConsultation, StatutFacture, ModePaiement, TypeMutuelle, Sexe, TypeNotification)
- Toutes les relations et cardinalités entre les entités

### 2. Diagrammes de Séquence (4 diagrammes)

#### a) Authentification
**Fichier:** `diagrams/sequence-authentication.puml`
- Processus de connexion en 2 étapes avec code de validation par email
- Génération de token JWT

#### b) Création de Rendez-vous
**Fichier:** `diagrams/sequence-appointment-creation.puml`
- Vérification de la disponibilité
- Validation patient et médecin
- Création avec statut EN_ATTENTE

#### c) Workflow de Consultation
**Fichier:** `diagrams/sequence-consultation-workflow.puml`
- Consultation médicale complète
- Mise à jour du dossier médical
- Changement de statut du rendez-vous

#### d) Génération de Facture
**Fichier:** `diagrams/sequence-invoice-generation.puml`
- Création de facture après consultation
- Calcul selon type de mutuelle
- Gestion du paiement

### 3. Diagrammes de Cas d'Utilisation (4 diagrammes)

#### a) Authentification et Utilisateurs
**Fichier:** `diagrams/usecase-authentication-users.puml`
- Gestion de l'authentification
- Gestion des utilisateurs (CRUD)
- Gestion des rôles (MEDECIN, SECRETAIRE, ADMIN)
- Configuration du cabinet

#### b) Gestion des Patients
**Fichier:** `diagrams/usecase-patient-management.puml`
- CRUD patients
- Gestion du dossier médical
- Antécédents et allergies
- Documents médicaux

#### c) Gestion des Rendez-vous
**Fichier:** `diagrams/usecase-appointment-management.puml`
- Création/modification/annulation de RDV
- Gestion de l'agenda
- Consultations
- Notifications

#### d) Dossier Médical et Facturation
**Fichier:** `diagrams/usecase-medical-records-billing.puml`
- Gestion du dossier médical
- Facturation et paiements
- Base de données médicaments
- Calcul selon mutuelle

### 4. Diagramme d'Activité
**Fichier:** `diagrams/activity-consultation-process.puml`

Processus complet d'une consultation:
1. Accueil et vérification du rendez-vous
2. Consultation médicale et examen
3. Prescription et traitement
4. Facturation et paiement
5. Planification du suivi

### 5. Diagramme d'État-Transition
**Fichier:** `diagrams/state-appointment-transitions.puml`

États d'un rendez-vous:
- **EN_ATTENTE** → création initiale
- **CONFIRME** → patient présent
- **TERMINE** → consultation effectuée
- **ANNULE** → rendez-vous annulé

## 🛠️ Utilisation des diagrammes

### Visualisation en ligne
1. **PlantText:** https://www.planttext.com/
2. **PlantUML Online:** http://www.plantuml.com/plantuml/uml/

### Visualisation avec VS Code
```bash
# Installer l'extension PlantUML
code --install-extension jebbs.plantuml

# Prévisualiser: Alt+D
```

### Génération d'images
```bash
# Installer PlantUML
# macOS
brew install plantuml

# Ubuntu/Debian
sudo apt-get install plantuml

# Générer toutes les images PNG
plantuml -tpng diagrams/*.puml

# Les images seront créées dans le même répertoire
```

## 📊 Statistiques

- **Nombre total de diagrammes:** 11 fichiers
- **Lignes de code PlantUML:** ~1,224 lignes
- **Types de diagrammes:**
  - 1 diagramme de classe
  - 4 diagrammes de séquence
  - 4 diagrammes de cas d'utilisation
  - 1 diagramme d'activité
  - 1 diagramme d'état-transition

## 📖 Documentation détaillée

Pour une documentation complète et détaillée de chaque diagramme, consultez:
**`diagrams/README.md`**

Ce fichier contient:
- Description détaillée de chaque diagramme
- Flux et processus
- Acteurs impliqués
- Points clés et notes importantes
- Instructions d'utilisation

## 🎯 Objectifs des diagrammes

Ces diagrammes servent à:
1. **Documenter l'architecture** du système
2. **Comprendre les flux métier** et les processus
3. **Faciliter la communication** entre les développeurs et les parties prenantes
4. **Servir de référence** pour le développement et la maintenance
5. **Former** les nouveaux membres de l'équipe

## 🔗 Liens avec le code source

Les diagrammes sont basés sur les entités et services suivants:
- **Entités:** `/src/main/java/net/cabinet/backendspring/entity/`
- **Contrôleurs:** `/src/main/java/net/cabinet/backendspring/controller/`
- **Services:** `/src/main/java/net/cabinet/backendspring/service/`
- **Énumérations:** `/src/main/java/net/cabinet/backendspring/helper/enums/`

## 📅 Maintenance

Les diagrammes doivent être mis à jour lors de:
- Ajout de nouvelles entités ou relations
- Modification des processus métier
- Ajout de nouveaux cas d'utilisation
- Changement de statuts ou d'états
- Évolution des rôles ou permissions

---

**Date de création:** 2026-01-08  
**Format:** PlantUML (.puml)  
**Langue:** Français

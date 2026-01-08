# Diagrammes UML - Système de Gestion de Cabinet Médical

Ce répertoire contient tous les diagrammes UML du projet Backend Cabinet Spring.

## 📋 Table des matières

1. [Diagramme de Classe](#diagramme-de-classe)
2. [Diagrammes de Séquence](#diagrammes-de-séquence)
3. [Diagrammes de Cas d'Utilisation](#diagrammes-de-cas-dutilisation)
4. [Diagramme d'Activité](#diagramme-dactivité)
5. [Diagramme d'État-Transition](#diagramme-détat-transition)

## 📊 Diagramme de Classe

**Fichier:** `class-diagram.puml`

### Description
Le diagramme de classe présente la structure complète du modèle de domaine du système, incluant:
- **Entités principales:** Cabinet, Utilisateur, Patient, RendezVous, Consultation, DossierMedical, Facture, Medicament, Notification
- **Énumérations:** Role, Statut, TypeConsultation, StatutFacture, ModePaiement, TypeMutuelle, Sexe, TypeNotification
- **Relations:** Associations, compositions, agrégations entre les entités

### Points clés
- Gestion multi-utilisateurs avec rôles (MEDECIN, SECRETAIRE, ADMIN)
- Relation patient-dossier médical (1:1)
- Historique complet des consultations
- Gestion de la facturation par consultation
- Système de notifications

## 🔄 Diagrammes de Séquence

### 1. Authentification (`sequence-authentication.puml`)
**Processus:** Connexion utilisateur avec validation par code

**Flux:**
1. L'utilisateur saisit login/mot de passe
2. Le système génère un code de validation à 6 chiffres
3. Le code est envoyé par email
4. L'utilisateur valide le code
5. Le système génère un token JWT

**Acteurs:** Utilisateur, AuthRequestController, UtilisateurService, EmailService

### 2. Création de Rendez-vous (`sequence-appointment-creation.puml`)
**Processus:** Création d'un nouveau rendez-vous patient

**Flux:**
1. Vérification de l'existence du patient
2. Vérification de l'existence du médecin
3. Vérification de la disponibilité du créneau
4. Création du rendez-vous avec statut EN_ATTENTE

**Acteurs:** Secrétaire/Médecin, RendezVousController, RendezVousService

### 3. Consultation Médicale (`sequence-consultation-workflow.puml`)
**Processus:** Déroulement d'une consultation complète

**Flux:**
1. Vérification du rendez-vous (doit être CONFIRME)
2. Récupération du dossier médical du patient
3. Enregistrement de la consultation (examens, diagnostic, traitement)
4. Mise à jour du statut du rendez-vous à TERMINE
5. Ajout à l'historique du dossier médical

**Acteurs:** Médecin, ConsultationController, ConsultationService

### 4. Génération de Facture (`sequence-invoice-generation.puml`)
**Processus:** Création et gestion de facture après consultation

**Flux:**
1. Vérification de la consultation
2. Calcul du montant selon le type de mutuelle
3. Création de la facture avec statut IMPAYEE
4. Mise à jour du paiement

**Acteurs:** Secrétaire, FactureController, FactureService

## 👥 Diagrammes de Cas d'Utilisation

### 1. Authentification et Utilisateurs (`usecase-authentication-users.puml`)
**Acteurs:** Utilisateur, Administrateur

**Fonctionnalités:**
- Connexion avec code de validation
- Gestion des utilisateurs (CRUD)
- Gestion des rôles
- Configuration du cabinet

### 2. Gestion des Patients (`usecase-patient-management.puml`)
**Acteurs:** Secrétaire, Médecin

**Fonctionnalités:**
- CRUD patients
- Gestion du dossier médical
- Historique des consultations
- Gestion des antécédents et allergies
- Documents médicaux

### 3. Gestion des Rendez-vous (`usecase-appointment-management.puml`)
**Acteurs:** Secrétaire, Médecin, Patient

**Fonctionnalités:**
- Création/modification/annulation de rendez-vous
- Vérification des disponibilités
- Gestion de l'agenda
- Réalisation de consultations
- Système de notifications

### 4. Dossier Médical et Facturation (`usecase-medical-records-billing.puml`)
**Acteurs:** Secrétaire, Médecin, Patient

**Fonctionnalités:**
- Gestion du dossier médical
- Génération et gestion des factures
- Calcul selon mutuelle (CNSS: -70%, CNOPS: -80%)
- Gestion des paiements
- Base de données médicaments

## 🔄 Diagramme d'Activité

**Fichier:** `activity-consultation-process.puml`

### Description
Représente le processus complet d'une consultation, depuis l'arrivée du patient jusqu'à son départ.

**Phases principales:**
1. **Accueil** - Vérification du rendez-vous
2. **Consultation** - Examen clinique et diagnostic
3. **Prescription** - Traitement et examens complémentaires
4. **Facturation** - Génération de facture et paiement
5. **Suivi** - Planification du prochain rendez-vous si nécessaire

## 🔀 Diagramme d'État-Transition

**Fichier:** `state-appointment-transitions.puml`

### Description
Modélise les différents états d'un rendez-vous et les transitions possibles.

**États:**
- **EN_ATTENTE** - État initial lors de la création
- **CONFIRME** - Patient présent, prêt pour consultation
- **TERMINE** - Consultation effectuée
- **ANNULE** - Rendez-vous annulé

**Transitions:**
- EN_ATTENTE → CONFIRME (confirmation de présence)
- EN_ATTENTE → ANNULE (annulation)
- CONFIRME → TERMINE (consultation effectuée)
- CONFIRME → ANNULE (annulation tardive)
- CONFIRME → EN_ATTENTE (patient non présenté)

## 🛠️ Utilisation

### Visualisation
Ces diagrammes sont écrits en PlantUML. Pour les visualiser:

1. **En ligne:** 
   - Utilisez [PlantText](https://www.planttext.com/)
   - Utilisez [PlantUML Online Server](http://www.plantuml.com/plantuml/uml/)

2. **VS Code:**
   - Installez l'extension "PlantUML"
   - Ouvrez un fichier `.puml`
   - Utilisez `Alt+D` pour prévisualiser

3. **Ligne de commande:**
   ```bash
   # Installer PlantUML
   brew install plantuml  # macOS
   # ou
   apt-get install plantuml  # Linux
   
   # Générer les images
   plantuml diagrams/*.puml
   ```

### Génération d'images
Pour générer des images PNG à partir des fichiers PlantUML:

```bash
# Générer tous les diagrammes
plantuml -tpng diagrams/*.puml

# Générer un diagramme spécifique
plantuml -tpng diagrams/class-diagram.puml
```

## 📝 Notes

- Tous les diagrammes sont en français pour correspondre au contexte du projet
- Les diagrammes reflètent l'état actuel de l'architecture du système
- Les relations et multiplicités sont basées sur les annotations JPA des entités

## 🔗 Liens utiles

- [Documentation PlantUML](https://plantuml.com/)
- [Guide PlantUML](https://plantuml.com/fr/guide)
- [Exemples PlantUML](https://real-world-plantuml.com/)

---

**Auteur:** Système de Gestion Cabinet Médical  
**Version:** 1.0  
**Date:** 2026-01-08

# TaalibHub

# 🎓 TaalibHub – Suivi académique intelligent

## 1. 📘 Contexte du Projet

Dans le contexte universitaire marocain, le suivi académique des étudiants est souvent **manuel, peu transparent**,
et difficilement accessible, tant pour les étudiants que pour les enseignants.  
Cette situation engendre :

- un **manque de visibilité** sur la performance des étudiants,  
- des **retards dans le traitement des résultats**,  
- et des **difficultés à détecter les étudiants en difficulté** à temps.

### 🎯 Objectif :
Développer une **application web intuitive et responsive** permettant :
- aux étudiants de suivre **en temps réel leurs performances** (notes, assiduité, feedbacks),
- et aux enseignants de **gérer plus facilement les évaluations**.

---

## 2. 🧠 Modalités Pédagogiques

Ce projet a également une **vocation pédagogique** car il :

- Permet l’apprentissage du **développement web full-stack** : `Spring Boot + Angular`
- Applique l’**architecture MVC** et les **bonnes pratiques en génie logiciel**
- Met en œuvre :
  - des **REST APIs**
  - une **authentification sécurisée**
  - la **gestion des rôles (étudiant, enseignant, admin)**
- Utilise une **base de données relationnelle** : `MySQL` ou `PostgreSQL`
- Fournit une **expérience pratique** sur un projet utile, concret et évolutif

---

## 3. 👥 User Stories

### 🚀 Étudiants

- En tant qu’étudiant, je veux **m’inscrire et me connecter** pour accéder à mon espace personnel.
- En tant qu’étudiant, je veux voir **mon tableau de bord** pour avoir une vue globale sur ma progression.
- En tant qu’étudiant, je veux **consulter la liste de mes cours** pour savoir les modules que je suis.
- En tant qu’étudiant, je veux **voir mes notes** par devoir et examen pour suivre mes résultats.
- En tant qu’étudiant, je veux **consulter mon taux de présence** pour savoir si je suis assidu.
- En tant qu’étudiant, je veux **lire les feedbacks** de mes professeurs pour m’améliorer.

> 🏅 **Bonus** :  
> En tant qu’étudiant, je veux **télécharger mon relevé de notes en PDF** pour l’imprimer ou l’envoyer à l’administration.

---

### 👨‍🏫 Enseignants

- En tant qu’enseignant, je veux **me connecter de manière sécurisée** pour accéder à mon espace.
- En tant qu’enseignant, je veux **créer et gérer mes cours** pour organiser mes enseignements.
- En tant qu’enseignant, je veux **ajouter les étudiants** à mes cours pour gérer mes classes.
- En tant qu’enseignant, je veux **saisir les notes et évaluations** pour chaque devoir/examen.
- En tant qu’enseignant, je veux **enregistrer les présences** par date pour suivre l’assiduité.
- En tant qu’enseignant, je veux **ajouter des feedbacks personnalisés** pour guider les étudiants.
- En tant qu’enseignant, je veux **générer des rapports de performance** pour chaque étudiant.

---

### 🏫 Administration

- En tant qu’administrateur, je veux **voir des statistiques globales** pour suivre l’évolution des étudiants par semestre.
- En tant qu’administrateur, je veux **détecter automatiquement les étudiants en difficulté** pour intervenir à temps.
- En tant qu’administrateur, je veux **exporter les données de performance** pour les rapports officiels.
- En tant qu’administrateur, je veux **gérer les utilisateurs et les modules** pour garder le système à jour.

---

## 🔧 Technologies utilisées

- **Backend** : Spring Boot (Java)
- **Frontend** : Angular 18/19
- **Base de données** : MySQL ou PostgreSQL
- **Sécurité** : Spring Security (JWT, rôles)
- **Outils de modélisation** : UML (cas d'utilisation, classes, séquences)

---

## 📂 Structure prévue

- `/backend` → API REST sécurisée (Spring Boot)
- `/frontend` → Application Angular responsive
- `/docs` → Diagrammes UML, maquettes, et documentation
- `README.md` → Présentation du projet (ce fichier)

---

> 💡 Ce projet s’inscrit dans une démarche d'amélioration continue de la gestion académique dans l'enseignement supérieur au Maroc.


### Diagramme uml ###
*diagramme de use case 

<img width="500" alt="image" src="https://github.com/user-attachments/assets/1b15428c-e2cc-483b-9879-b32ec6038eec" />


*diagramme de class*

<img width="437" alt="image" src="https://github.com/user-attachments/assets/33cc57b1-0037-4db7-80a6-8b425a295aa2" />



*Diagramme de Séquence - Étudiant consulte ses notes*

<img width="629" alt="image" src="https://github.com/user-attachments/assets/741e8f37-68bf-4315-975b-c264cd716106" />

*Diagramme de Séquence - Enseignant saisit les notes*


<img width="628" alt="image" src="https://github.com/user-attachments/assets/bbacf7d7-db2a-4a0b-8101-1ee587db2744" />


*Ajout d'un Feedback par un Étudiant

<img width="598" alt="image" src="https://github.com/user-attachments/assets/79d1a80e-50f1-45e0-a93e-3cc3c5ce1e80" />


*diagramme de séquence détaillé pour l'enregistrement des présences


<img width="522" alt="image" src="https://github.com/user-attachments/assets/97bb8113-4304-48f7-a5d4-706ccbc146ae" />






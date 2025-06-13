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

<img width="452" alt="image" src="https://github.com/user-attachments/assets/f0180390-52cc-46ef-8663-8d30b2aed80b" />




*Diagramme de Séquence - Étudiant consulte ses notes*

<img width="596" alt="image" src="https://github.com/user-attachments/assets/feb4758c-1fd6-40e9-8ff3-9694f375d8be" />



*Diagramme de Séquence - Enseignant saisit les notes*


<img width="605" alt="image" src="https://github.com/user-attachments/assets/e4c99d9a-e80a-4cc5-8b5f-fe078c9a2180" />



*diagramme de séquence détaillé pour l'enregistrement des présences


<img width="575" alt="image" src="https://github.com/user-attachments/assets/53b0beee-f06b-4f5d-8ab3-61297f16a8ae" />



*diagramme de séquence Etudiante consulter son taux de présence


![image](https://github.com/user-attachments/assets/d61053ff-160b-4263-9383-c5eb9fa0c233)







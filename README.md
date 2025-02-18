# TD5

## Mettre en place Hibernate avec une base de donnée H2 dans un projet Java
### Hibernate: Un ORM

Hibernate est un framework ORM (Object-Relational Mapping) pour Java, conçu pour simplifier l'interaction entre une application Java et une base de données relationnelle.
En utilisant Hibernate, les développeurs peuvent travailler avec les données en utilisant des objets Java sans avoir à écrire de requêtes SQL complexes.
Hibernate convertit automatiquement les objets Java en enregistrements de base de données et vice versa, facilitant la persistance des données.

Plus d'informations sur Hibernate sont disponibles sur le [site officiel](https://hibernate.org/orm/).

### H2: Une base de données en mémoire

H2 est une base de données relationnelle écrite en Java, conçue pour être rapide et facile à utiliser.

### 1. Implémenter LibraryRepositoryHibernate

Implémenter `LibraryRepositoryHibernate` permettant de faire des opérations de persistances avec Hibernate et la base de données H2.

Les méthodes à implémenter:
- `Library findByName(LibraryName name)`
- `void saveOrUpdate(Library library)`

Exécuter les tests pour valider votre implémentation.

Le [guide d'astuces pour hibernate et h2](astuce-hibernate-h2.md) peut vous aider dans votre implémentation.

_P.S. Le guide et les configurations présentes dans la base du TD vous sont aussi fournis pour le TP2!_

## 2. Visualiser la BD H2 avec Docker

Une base de données H2 en mémoire était utilisé par défault dans le dernier exercice.
Celle-ci est seulement activé durant le lancement de l'application et n'est pas visualisable.

Mettez en place la base de données H2 dans un conteneur Docker afin de la visualiser.

Le [guide d'astuces pour hibernate et h2](astuce-hibernate-h2.md) explique plus en détails les différentes méthodes 
de connexion à la base de données H2 en utilisant Hibernate. Les étapes pour mettre en place la base de données avec Docker y sont aussi décrites.
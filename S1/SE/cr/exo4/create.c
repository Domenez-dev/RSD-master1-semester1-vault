#include "semaphore.h"
#include <stdio.h>
#include <stdlib.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

// Définition de la structure des données partagées
typedef struct shared_data {
  int producer_index;
  int consumer_index;
  int buffer[10];
} shared_data;

int main() {
  // Générer une clé unique pour le segment de mémoire partagée
  key_t key = ftok(".", 'b');

  // Créer le segment de mémoire partagée
  int shmid = shmget(key, sizeof(shared_data), IPC_CREAT | IPC_EXCL | 0666);

  if (shmid < 0) {
    shmid = shmget(key, sizeof(shared_data), 0);
    printf("Le segment de mémoire partagée existe déjà avec l'ID : %d\n",
           shmid);
  } else {
    printf("Nouveau segment de mémoire partagée créé avec l'ID : %d\n", shmid);
  }

  // Attacher le segment de mémoire partagée à notre espace d'adressage
  shared_data *sd = shmat(shmid, NULL, 0);
  if (sd == (void *)-1) {
    perror("Échec de shmat");
    exit(EXIT_FAILURE);
  }

  // Initialiser le segment de mémoire partagée
  sd->producer_index = 0;
  sd->consumer_index = 0;

  printf("Indexes initialisés (Producteur : %d, Consommateur : %d)\n",
         sd->producer_index, sd->consumer_index);

  // Créer et initialiser les sémaphores
  int semid = create_sm(4, key);
  init_sm(semid, 0, 10);
  init_sm(semid, 1, 0);
  init_sm(semid, 2, 1);
  init_sm(semid, 3, 1);

  return 0;
}

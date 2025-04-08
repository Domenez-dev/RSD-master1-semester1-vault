#include "semaphore.h"
#include <stdlib.h>
#include <sys/shm.h>
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

  if (shmid < 0) { // Le segment existe déjà
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

  // Créer les sémaphores
  int semid = create_sm(4, key);
  int value;
  while (1) {
    // Attendre que l'emplacement soit vide
    if (sd->buffer[sd->producer_index] != 0) {
      continue;
    }
    // Produire une nouvelle valeur
    value = rand() % 10;
    printf("Valeur produite : %d\n", value);

    // Opérations sur les sémaphores
    p(semid, 0);
    p(semid, 2);

    // Placer la valeur produite dans le tampon
    sd->buffer[sd->producer_index] = value;
    sd->producer_index = (sd->producer_index + 1) % 10;

    v(semid, 2);
    v(semid, 1);

    // Afficher l'état actuel du tampon
    printf("Buffer = [%d, %d, %d, %d, %d, %d, %d, %d, %d, %d]\n", sd->buffer[0],
           sd->buffer[1], sd->buffer[2], sd->buffer[3], sd->buffer[4],
           sd->buffer[5], sd->buffer[6], sd->buffer[7], sd->buffer[8],
           sd->buffer[9]);

    // Attendre avant de produire la prochaine valeur
    sleep(5);
  }
  return 0;
}

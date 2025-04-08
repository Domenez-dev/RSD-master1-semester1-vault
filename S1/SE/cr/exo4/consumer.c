#include "semaphore.h"
#include <stdlib.h>
#include <sys/shm.h>
#include <unistd.h>

typedef struct shared_data {
  int producer_index;
  int consumer_index;
  int buffer[10];
} shared_data;

int main() {
  key_t key = ftok(".", 'b');
  int shmid = shmget(key, sizeof(shared_data), IPC_CREAT | IPC_EXCL | 0666);

  if (shmid < 0) {
    shmid = shmget(key, sizeof(shared_data), 0);
    printf("Le segment de mémoire partagée existe déjà avec l'ID : %d\n",
           shmid);
  } else {
    printf("Nouveau segment de mémoire partagée créé avec l'ID : %d\n", shmid);
  }

  shared_data *sd = shmat(shmid, NULL, 0);
  if (sd == (void *)-1) {
    perror("Échec de shmat");
    exit(EXIT_FAILURE);
  }

  int semid = create_sm(4, key);

  int value;
  while (1) {
    if (sd->buffer[sd->consumer_index] == 0) {
      continue;
    }

    p(semid, 1);
    p(semid, 3);

    value = sd->buffer[sd->consumer_index];
    sd->buffer[sd->consumer_index] = 0;
    sd->consumer_index = (sd->consumer_index + 1) % 10;

    v(semid, 3);
    v(semid, 0);

    printf("Consommation de la valeur : %d, à l'index %d\n", value,
           sd->consumer_index);
    printf("Tampon = [%d, %d, %d, %d, %d, %d, %d, %d, %d, %d]\n", sd->buffer[0],
           sd->buffer[1], sd->buffer[2], sd->buffer[3], sd->buffer[4],
           sd->buffer[5], sd->buffer[6], sd->buffer[7], sd->buffer[8],
           sd->buffer[9]);

    sleep(5);
  }
  return 0;
}

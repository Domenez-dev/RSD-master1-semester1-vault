#include "semaphore.h"
#include <stdio.h>
#include <sys/shm.h>

typedef struct data {
  int oxygen;
  int hydrogen;
  int cpt;
} sdata;

int main() {
  key_t key = ftok(".", 'a');
  int shmid = shmget(key, sizeof(sdata), IPC_CREAT | IPC_EXCL | 0666);

  if (shmid < 0) { // la zone existe deja
    shmid = shmget(key, sizeof(sdata), 0);
    printf("Memory segment already exists with ID (%d)\n", shmid);
  } else {
    printf("New memory segment created with ID (%d)\n", shmid);
  }

  sdata *sd = NULL;
  sd = shmat(shmid, sd, 0);

  sd->oxygen = 0;
  sd->hydrogen = 0;
  sd->cpt = 0;

  int semid = create_sm(4, key);
  init_sm(semid, 0, 1); // oxy hydro mutex
  init_sm(semid, 1, 2); // Hydro-Que
  init_sm(semid, 2, 1); // Oxy-Que
  init_sm(semid, 3, 0); // cpt mutex
  return 0;
}

#include "semaphore.h"
#include <stdio.h>
#include <stdlib.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

int main() {
  // Generate a unique key for the semaphore
  key_t key = ftok("./spool.c", 'b');
  if (key == -1) {
    perror("ftok failed");
    exit(EXIT_FAILURE);
  }

  // Create and initialize the semaphore
  int semid = create_sm(1, key);
  init_sm(semid, 0, 1);

  srand(time(NULL));

  int numProcesses;
  printf("Enter the number of child processes: ");
  scanf("%d", &numProcesses);

  int pids[numProcesses];

  for (int i = 0; i < numProcesses; i++) {
    pids[i] = fork();
    if (pids[i] < 0) {
      perror("fork failed");
    } else if (pids[i] == 0) {
      // Child process
      printf("Child %d with PID (%d): Waiting\n", i, getpid());
      p(semid, 0);

      printf("Child %d with PID (%d): Executing\n", i, getpid());
      time_t start = time(NULL);

      sleep(1 + (rand() % 3));

      time_t end = time(NULL);
      printf("Child %d with PID (%d): Completed in %ld seconds\n", i, getpid(),
             end - start);

      v(semid, 0);
      exit(EXIT_SUCCESS);
    }
  }

  for (int i = 0; i < numProcesses; i++) {
    wait(NULL);
  }

  printf("Parent process with PID (%d): All children have finished\n",
         getpid());
  sem_destroy(semid);

  return 0;
}

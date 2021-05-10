#include <stdlib.h>
#include <stdio.h>
#include <dirent.h>
#include <sys/stat.h>
#include <errno.h>
#include <string.h>

#define CURRENT_DIR "."

#define KNRM "\x1B[0m"
#define KYEL "\x1B[33m"
#define KMAG "\x1B[35m"

int is_hidden(const char *name)
{
  return name[0] == '.' && 
         strcmp(name, ".") != 0 &&
         strcmp(name, "..") != 0;
}

int is_current_and_previous_dir(const char *name){
    return strcmp(name, "..") == 0 || strcmp(name, ".") == 0;
}

int main(int argc, char *argv[])
{
    DIR *d;

    char path[500];

    int REG_FILES = 0,HIDDEN_FILES = 0,SYM_LNKS = 0,DIRS = 0,HIDDEN_DIRS = 0;

    struct dirent *dir;

    struct stat file_stat;

    if(argc>2){
        printf("Too manny arguments supplied !\n");
        exit(1);
    }
    else if(argc==2){
        strcpy(path,argv[1]);
    }else{
        strcpy(path, CURRENT_DIR);
    }


    d = opendir(path);

    if (d)

    {

        while ((dir = readdir(d)) != NULL)
        {
            stat(dir->d_name,&file_stat);

            //Type check
            if (S_ISREG(file_stat.st_mode))
            {
                REG_FILES++;
            }
            else if(S_ISLNK(file_stat.st_mode)){
                SYM_LNKS++;
            }
            else
            {
                if(is_current_and_previous_dir(dir->d_name) != 1){
                    DIRS++;
                }

                if ((is_current_and_previous_dir(dir->d_name) != 1) && is_hidden(dir->d_name)){
                    HIDDEN_DIRS++;
                }
            }

            if(is_hidden(dir->d_name)){
                HIDDEN_FILES++;
            }

        }
        closedir(d);
        printf("%sTotal Files :%s %d | %sHidden Files :%s %d | %sDirs :%s %d | %sHidden Dirs :%s %d | Sym Links : %d ", KMAG, KNRM, REG_FILES, KMAG, KNRM, HIDDEN_FILES, KYEL, KNRM, DIRS, KYEL, KNRM, HIDDEN_DIRS, SYM_LNKS);
        printf("\n");

    }else{
        switch (errno)
        {
        case ENOENT:
            printf("No such file or directory\n");
            break;

        case EACCES:
            printf("Permission denied\n");
            break;
        case ENAMETOOLONG:
            printf("File name too long\n");
            break;

        case ENOTDIR:
            printf("Not a directory\n");
            break;

        default:
            break;
        }
    }
    return 0;
}


